package momentxrt;

import momentxrt.events.EventBus;
import momentxrt.events.MomentEvent;
import momentxrt.triggers.Trigger;
import momentxrt.triggers.manual.ManualTrigger;
import momentxrt.triggers.audio.AudioSpikeTrigger;
import momentxrt.events.MarkerEmitter;
import momentxrt.clipper.ClipWindow;
import momentxrt.clipper.ClipWindowCalculator;
import momentxrt.ui.ConsoleVisualizer;
import momentxrt.timeline.Timeline;
import momentxrt.timeline.TimelineEntry;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Core engine for MomentX RT.
 * Independent from Ant Media lifecycle.
 */
public class MomentXCore {

    private final EventBus eventBus = new EventBus();
    private final ManualTrigger manualTrigger = new ManualTrigger();
    private final AudioSpikeTrigger audioTrigger = new AudioSpikeTrigger(0.7);
    private final PluginConfig config = PluginConfig.demoConfig();
    private final MarkerEmitter markerEmitter = new MarkerEmitter();
    private final ConsoleVisualizer visualizer = new ConsoleVisualizer(false);
    private final Timeline timeline = new Timeline();

    private final List<Trigger> triggers = List.of(
            manualTrigger,
            audioTrigger
    );

    private final ClipWindowCalculator clipCalculator =
            new ClipWindowCalculator(5000, 7000);

    private final AtomicBoolean running = new AtomicBoolean(false);
    private Thread pollingThread;
    private String activeStreamId;

    public void startStream(String streamId) {
        if (running.get()) {
            return;
        }

        this.activeStreamId = streamId;
        running.set(true);

        System.out.println("🚀 MomentX Core started for stream: " + streamId);

        eventBus.registerHandler(this::handleMoment);

        pollingThread = new Thread(() -> {
            while (running.get()) {
                pollTriggers();
                sleep(200);
            }
        });

        pollingThread.setDaemon(true);
        pollingThread.start();
    }

    public void stopStream(String streamId) {
        if (!running.get()) {
            return;
        }

        running.set(false);

        System.out.println("🛑 MomentX Core stopping for stream: " + streamId);

        try {
            if (pollingThread != null) {
                pollingThread.join(1000);
            }
        } catch (InterruptedException ignored) {
        }

        timeline.printSummary();
        timeline.printJson();
    }

    private void pollTriggers() {
        for (Trigger trigger : triggers) {

            if (!config.isTriggerEnabled(trigger.getName())) {
                continue;
            }

            MomentEvent event = trigger.check();

            if (event != null) {
                event.setStreamId(activeStreamId);
                eventBus.publish(event);
            }
        }
    }

    private void handleMoment(MomentEvent event) {
        ClipWindow window = clipCalculator.calculate(event);

        visualizer.showMoment(event, window);
        markerEmitter.emit(event);
        timeline.add(new TimelineEntry(event, window));
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }

    public ManualTrigger getManualTrigger() {
        return manualTrigger;
    }

    public AudioSpikeTrigger getAudioTrigger() {
        return audioTrigger;
    }
}
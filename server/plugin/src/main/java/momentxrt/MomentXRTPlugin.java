package momentxrt;

import momentxrt.events.EventBus;
import momentxrt.events.MomentEvent;
import momentxrt.triggers.Trigger;
import momentxrt.triggers.manual.ManualTrigger;

import java.util.List;

/**
 * Entry point for MomentX RT plugin.
 * Hackathon-safe minimal implementation.
 */
public class MomentXRTPlugin {

    // Core components
    private final EventBus eventBus = new EventBus();
    private final ManualTrigger manualTrigger = new ManualTrigger();
    private final List<Trigger> triggers = List.of(manualTrigger);

    public void start() {
        System.out.println("MomentX RT plugin started");

        eventBus.registerHandler(this::handleMoment);

        // Demo: run a simple trigger loop
        for (int i = 0; i < 5; i++) {
            pollTriggers();

            // Fire manual trigger once in the middle
            if (i == 2) {
                manualTrigger.fire();
            }

            sleep(500);
        }
    }

    public void stop() {
        System.out.println("MomentX RT plugin stopped");
    }

    private void pollTriggers() {
        for (Trigger trigger : triggers) {
            MomentEvent event = trigger.check();
            if (event != null) {
                eventBus.publish(event);
            }
        }
    }

    private void handleMoment(MomentEvent event) {
        System.out.println("Moment detected → " + event);
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }

    public static void main(String[] args) {
        new MomentXRTPlugin().start();
    }
}
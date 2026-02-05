package momentxrt.events;

import java.util.function.Consumer;

public class EventBus {

    private Consumer<MomentEvent> handler;

    public void registerHandler(Consumer<MomentEvent> handler) {
        this.handler = handler;
    }

    public void publish(MomentEvent event) {
        if (handler != null && event != null) {
            handler.accept(event);
        }
    }
}
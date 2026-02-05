package momentxrt.triggers.manual;

import momentxrt.events.MomentEvent;
import momentxrt.triggers.Trigger;

public class ManualTrigger implements Trigger {

    private volatile boolean triggered = false;

    public void fire() {
        this.triggered = true;
    }

    @Override
    public MomentEvent check() {
        if (!triggered) {
            return null;
        }
        triggered = false;
        return new MomentEvent(
                System.currentTimeMillis(),
                "manual",
                1.0
        );
    }

    @Override
    public String getName() {
        return "ManualTrigger";
    }
}
package momentxrt.triggers;

import momentxrt.events.MomentEvent;

public interface Trigger {
    MomentEvent check();
    String getName();
}
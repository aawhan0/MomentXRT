package momentxrt.events;

public class MomentEvent {

    private final long timestampMs;
    private final String triggerType;
    private final double confidence;

    public MomentEvent(long timestampMs, String triggerType, double confidence) {
        this.timestampMs = timestampMs;
        this.triggerType = triggerType;
        this.confidence = confidence;
    }

    public long getTimestampMs() {
        return timestampMs;
    }

    public String getTriggerType() {
        return triggerType;
    }

    public double getConfidence() {
        return confidence;
    }
    @Override
        public String toString() {
            return "MomentEvent{" +
                "timestampMs=" + timestampMs +
                ", triggerType='" + triggerType + '\'' +
                ", confidence=" + confidence +
                '}';
}
}
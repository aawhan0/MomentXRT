package momentxrt.antmedia;

import momentxrt.MomentXCore;

/**
 * Ant Media lifecycle adaptor for MomentX RT.
 * Hackathon-safe: minimal integration.
 */
public class MomentXRTAppAdaptor {

    private MomentXCore plugin;

    /**
     * Called when a live stream starts.
     */
    public void onPublishStarted(String streamId) {
        System.out.println("[AntMedia] Stream started: " + streamId);

        plugin = new MomentXCore();
        plugin.start();
    }

    /**
     * Called when a live stream ends.
     */
    public void onPublishFinished(String streamId) {
        System.out.println("[AntMedia] Stream finished: " + streamId);

        if (plugin != null) {
            plugin.stop();
        }
    }

    public static void main(String[] args) {
    MomentXRTAppAdaptor adaptor = new MomentXRTAppAdaptor();
    adaptor.onPublishStarted("demo-stream-001");

    try {
        Thread.sleep(4000);
    } catch (InterruptedException ignored) {}

    adaptor.onPublishFinished("demo-stream-001");
}
}
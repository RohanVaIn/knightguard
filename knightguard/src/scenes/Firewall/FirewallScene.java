package scenes.Firewall;

/*
 * Scene wrapper around FirewallGame.
 *
 * Frontend can call these methods
 * exactly like AccountScene and LogsScene.
 */
public class FirewallScene {

    private static final FirewallGame firewallGame =
            new FirewallGame();

    public static void tick() {

        /*
         * Frontend/input code can go here later.
         */
    }

    public static Traffic getCurrentTraffic() {
        return firewallGame.getCurrentTraffic();
    }

    public static FirewallResult submitAction(
            FirewallAction action) {

        return firewallGame.submitAction(action);
    }

    public static int getScore() {
        return firewallGame.getScore();
    }

    public static int getCorrectAnswers() {
        return firewallGame.getCorrectAnswers();
    }

    public static int getCurrentTrafficNumber() {
        return firewallGame.getCurrentTrafficNumber();
    }

    public static int getTotalTraffic() {
        return firewallGame.getTotalTraffic();
    }

    public static boolean isComplete() {
        return firewallGame.isComplete();
    }

    public static void reset() {
        firewallGame.reset();
    }
}
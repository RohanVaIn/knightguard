package scenes.firewall;

/*
 * Returned after the player makes a decision.
 */
public class FirewallResult {

    private final boolean correct;
    private final int points;
    private final String message;

    public FirewallResult(
            boolean correct,
            int points,
            String message) {

        this.correct = correct;
        this.points = points;
        this.message = message;
    }

    public boolean isCorrect() {
        return correct;
    }

    public int getPoints() {
        return points;
    }

    public String getMessage() {
        return message;
    }
}
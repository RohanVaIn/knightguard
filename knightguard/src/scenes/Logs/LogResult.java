package scenes.Logs;

/*
 * 
 * Result that is returned to the frontend after the player deices whether a log is normal or suspicous
 *
 * The frontend can use this to display:
 *
 * - Correct / incorrect
 * - Points earned
 * - Helpful explanation
 */
public class LogResult {

    private final boolean correct;
    private final int points;
    private final String message;


    public LogResult(
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
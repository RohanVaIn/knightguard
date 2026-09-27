package scenes.Account;

/*
 * Returned whenever the player makes a decision.
 *
 * The frontend can use this object to display:
 *
 * - whether the answer was correct
 * - how many points were earned
 * - an explanation
 */
public class AccountResult {

    private final boolean correct;
    private final int points;
    private final String message;


    public AccountResult(
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
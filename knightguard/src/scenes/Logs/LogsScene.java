package scenes.Logs;

/*
 * Connects the Security Logs backend to the
 * rest of the game's scene system.
 * 
 * This connects the Security Logs backend to the rest of the game's scene (frontend).
 *
 * FRONTEND NOTE:
 *
 * This class has no rendering or any kind of keyboard/mouse controls yet.
 *
 * These methods are used to work with LogGame.java
 * 
 */
public class LogsScene {

    /*
     * Backend instance of the logs game.
     */
    private static final LogGame logGame =
            new LogGame();


    /*
     * This is here so the Logs scene can follow
     * the same tick structure as the other scenes.
     *
     * Frontend/input logic can be added here later.
     */
    public static void tick() {

        // FRONTEND TODO:
        //
        // Detect input here if desired.
        //
        // Then call:
        //
        // submitDecision(LogDecision.NORMAL);
        //
        // or
        //
        // submitDecision(LogDecision.SUSPICIOUS);
    }


    /*
     * FRONTEND:
     *
     * Gets the log that should currently
     * be displayed.
     */
    public static LogEntry getCurrentLog() {

        return logGame.getCurrentLog();
    }


    /*
     * FRONTEND:
     *
     * Sends the player's choice to the backend.
     *
     * Example:
     *
     * LogResult result =
     *     LogsScene.submitDecision(
     *         LogDecision.SUSPICIOUS
     *     );
     */
    public static LogResult submitDecision(
            LogDecision decision) {

        return logGame.submitDecision(decision);
    }


    /*
     * FRONTEND:
     *
     * Current total score.
     */
    public static int getScore() {

        return logGame.getScore();
    }


    /*
     * How many logs the player has
     * correctly identified.
     */
    public static int getCorrectAnswers() {

        return logGame.getCorrectAnswers();
    }


    /*
     * Human-friendly number.
     *
     * First log = 1 instead of 0.
     */
    public static int getCurrentLogNumber() {

        return logGame.getCurrentIndex() + 1;
    }


    public static int getTotalLogs() {

        return logGame.getLogCount();
    }


    /*
     * True once every log has been reviewed.
     */
    public static boolean isComplete() {

        return logGame.isComplete();
    }


    /*
     * Restarts the minigame.
     */
    public static void reset() {

        logGame.reset();
    }
}

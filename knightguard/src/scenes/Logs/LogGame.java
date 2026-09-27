package scenes.Logs;

import java.util.ArrayList;
import java.util.List;

/*
 * This file is the backends logic for the Security Logs game.
 *
 * This file is responsible for:
 *
 * - Storing all the security logs
 * - Keeping track of the current log
 * - Checking normal / suspicious decisions
 * - Keeping track of score
 * - Moving through the logs
 * - Determining when the game is complete
 *
 * NO GUI or rendering stuff in this code
 */
public class LogGame {

    private final List<LogEntry> logs;

    private int currentIndex;
    private int score;
    private int correctAnswers;


    public LogGame() {

        logs = new ArrayList<>();

        currentIndex = 0;
        score = 0;
        correctAnswers = 0;

        loadLogs();
    }


    /*
     * Creates all of the logs used in the game.
     *
     * We mix normal and suspicious activity so the
     * player has to actually examine the information.
     */
    private void loadLogs() {

        /*
         * NORMAL:
         * A guard logs in from the expected place
         * during a normal time.
         */
        logs.add(new LogEntry(
                1,
                "8:05 AM",
                "GuardThomas",
                "LOGIN_SUCCESS",
                "North Gate",
                "GuardThomas successfully signed into the town watch system.",
                false,
                "This is normal activity. The guard logged in from an expected location during a normal shift."
        ));


        /*
         * NORMAL:
         * Routine merchant activity.
         */
        logs.add(new LogEntry(
                2,
                "10:32 AM",
                "MerchantRonaldo",
                "RECORD_ACCESS",
                "Market District",
                "MerchantRonaldo accessed his own market records.",
                false,
                "The merchant seems to be doing his normal responsibilities."
        ));


        /*
         * SUSPICIOUS:
         * Multiple failed login attempts.
         */
        logs.add(new LogEntry(
                3,
                "1:58 AM",
                "TownAdmin",
                "LOGIN_FAILED",
                "Unknown Location",
                "Three failed login attempts were recorded for the administrator account.",
                true,
                "Multiple failed attempts on an administrator account, especially from an unknown location, can indicate someone trying to gain access."
        ));


        /*
         * SUSPICIOUS:
         * Successful login shortly after the failures.
         */
        logs.add(new LogEntry(
                4,
                "2:03 AM",
                "TownAdmin",
                "LOGIN_SUCCESS",
                "Unknown Location",
                "The administrator account successfully logged in after several failed attempts.",
                true,
                "A successful login immediately after repeated failures from an unknown location should be investigated."
        ));


        /*
         * NORMAL:
         * Routine automated system activity.
         */
        logs.add(new LogEntry(
                5,
                "2:30 AM",
                "TownSystem",
                "SYSTEM_CHECK",
                "Town Server",
                "The scheduled nightly system check completed successfully.",
                false,
                "This was a normal scheduled system event."
        ));


        /*
         * SUSPICIOUS:
         * Administrator accesses information at an unusual time.
         */
        logs.add(new LogEntry(
                6,
                "2:07 AM",
                "TownAdmin",
                "RECORD_ACCESS",
                "Town Records",
                "The administrator account accessed protected town records.",
                true,
                "The account was already behaving suspiciously, and protected records were accessed shortly after the unusual login."
        ));


        /*
         * NORMAL:
         * Regular guard activity.
         */
        logs.add(new LogEntry(
                7,
                "6:45 AM",
                "GuardAndrew",
                "LOGIN_SUCCESS",
                "West Gate",
                "GuardAndrew successfully signed in for the morning shift.",
                false,
                "The time, location, and account behavior are all consistent with normal activity."
        ));


        /*
         * SUSPICIOUS:
         * Unexpected activity from the administrator account.
         */
        logs.add(new LogEntry(
                8,
                "2:09 AM",
                "TownAdmin",
                "UNUSUAL_ACTIVITY",
                "Unknown Location",
                "The administrator account performed several protected actions within a few seconds.",
                true,
                "Several sensitive actions happening rapidly after a suspicious login may indicate that the account was compromised."
        ));
    }


    /*
     * Returns the log the player should currently review.
     *
     * Returns null once all logs have been reviewed.
     */
    public LogEntry getCurrentLog() {

        if (isComplete()) {
            return null;
        }

        return logs.get(currentIndex);
    }


    /*
     * Main gameplay method.
     *
     * The frontend calls this when the player chooses:
     *
     * NORMAL
     *
     * or
     *
     * SUSPICIOUS
     */
    public LogResult submitDecision(LogDecision decision) {

        if (isComplete()) {

            return new LogResult(
                    false,
                    0,
                    "The log investigation is already complete."
            );
        }


        LogEntry currentLog = getCurrentLog();


        /*
         * Convert the player's choice into a boolean.
         *
         * SUSPICIOUS = true
         * NORMAL = false
         */
        boolean playerSaysSuspicious =
                decision == LogDecision.SUSPICIOUS;


        /*
         * Compare the player's decision to the
         * actual answer stored in the LogEntry.
         */
        boolean correct =
                playerSaysSuspicious ==
                currentLog.isSuspicious();


        int pointsEarned = 0;


        if (correct) {

            pointsEarned = 10;

            score += pointsEarned;

            correctAnswers++;
        }


        String message;


        if (correct) {

            message =
                    "Correct! " +
                    currentLog.getExplanation();

        } else {

            message =
                    "Not quite. " +
                    currentLog.getExplanation();
        }


        /*
         * Move to the next log after the player
         * makes a decision.
         */
        currentIndex++;


        return new LogResult(
                correct,
                pointsEarned,
                message
        );
    }


    /*
     * Returns true once the player has
     * reviewed every log.
     */
    public boolean isComplete() {

        return currentIndex >= logs.size();
    }


    public int getScore() {

        return score;
    }


    public int getCorrectAnswers() {

        return correctAnswers;
    }


    /*
     * Zero-based index.
     *
     * 0 = first log
     * 1 = second log
     * etc.
     */
    public int getCurrentIndex() {

        return currentIndex;
    }


    public int getLogCount() {

        return logs.size();
    }


    /*
     * Allows the game to be restarted.
     */
    public void reset() {

        currentIndex = 0;
        score = 0;
        correctAnswers = 0;
    }
}

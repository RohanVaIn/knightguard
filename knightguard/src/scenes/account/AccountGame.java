package scenes.account;

import java.util.ArrayList;
import java.util.List;

/*
 * Contains the actual logic for the Account Management minigame.
 *
 * Responsibilities:
 *
 * - Stores the accounts
 * - Keeps track of which account the player is reviewing
 * - Checks the player's answer
 * - Calculates score
 * - Determines when the game is complete
 *
 * This class should NOT contain GUI, rendering,
 * keyboard, or mouse code.
 */
public class AccountGame {

    private final List<Account> accounts;
    private int currentIndex;
    private int score;
    public AccountGame() {
        accounts = new ArrayList<>();
        currentIndex = 0;
        score = 0;
        loadAccounts();
    }
    /*
     * All accounts for the minigame are created here.
     *
     * More accounts can easily be added later.
     */
    private void loadAccounts() {

        // Correctly configured administrator.
        accounts.add(new Account(
                "Queen Amilia",
                "Queen of the Nearby Lands",
                true,
                true,
                "Strong",
                "Today",
                AccountAction.KEEP,
                "This administrator has the correct permissions and MFA enabled."
        ));

        // User has more privileges than necessary.
        accounts.add(new Account(
                "Flunkio",
                "Peasant / Local Weirdo",
                true,
                false,
                "Medium",
                "Today",
                AccountAction.REMOVE_ADMIN,
                "A lowly peasant should not need any admin privileges!"
        ));

        // Former employee/account should no longer have access.
        accounts.add(new Account(
                "Cedar the Exiled",
                "Former High General",
                true,
                false,
                "Strong",
                "8 months ago",
                AccountAction.DISABLE,
                "Former members of the kingdom should not have active accounts."
        ));

        // Important account that does not have MFA.
        accounts.add(new Account(
                "Mel",
                "Wizard (former NBA champion)",
                false,
                false,
                "Strong",
                "Yesterday",
                AccountAction.ENABLE_MFA,
                "Important accounts need to use multi-factor authentication."
        ));

        // Account has correct permissions but a weak password.
        accounts.add(new Account(
                "Logan E. Cuttard",
                "Log Cutter",
                false,
                true,
                "Medium",
                "Today",
                AccountAction.RESET_PASSWORD,
                "This account has proper permissions, but the password is weak, so it could easily be broken into."
        ));
    }

    /*
     * Gives the frontend the account that should
     * currently be displayed.
     *
     * Returns null once all accounts have been completed.
     */
    public Account getCurrentAccount() {

        if(isComplete()) {
            return null;
        }

        return accounts.get(currentIndex);
    }

    /*
     * Called when the player makes a decision.
     *
     * Example:
     *
     * submitAction(AccountAction.REMOVE_ADMIN);
     *
     * The game checks the answer, awards points,
     * moves to the next account, and returns the result.
     */
    public AccountResult submitAction(AccountAction action) {

        if(isComplete()) {
            return new AccountResult(
                    false,
                    0,
                    "The account audit is already complete."
            );
        }

        Account account = getCurrentAccount();
        boolean correct =
                action == account.getCorrectAction();

        int pointsEarned = 0;

        if(correct) {
            pointsEarned = 10;
            score += pointsEarned;
        }

        String message;
        if(correct) {
            message =
                    "Correct! " +
                    account.getExplanation();
        } else {
            message =
                    "Incorrect. " +
                    account.getExplanation();
        }

        /*
         * Move to the next account after the decision.
         */
        currentIndex++;

        return new AccountResult(
                correct,
                pointsEarned,
                message
        );
    }


    /*
     * True once every account has been reviewed.
     */
    public boolean isComplete() {
        return currentIndex >= accounts.size();
    }


    public int getScore() {
        return score;
    }


    /*
     * Zero-based index.
     *
     * 0 = first account
     * 1 = second account
     * etc.
     */
    public int getCurrentIndex() {
        return currentIndex;
    }


    public int getAccountCount() {
        return accounts.size();
    }


    /*
     * Allows the minigame to be restarted.
     */
    public void reset() {
        currentIndex = 0;
        score = 0;
    }
}
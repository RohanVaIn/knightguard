package scenes.Account;

/*
 * Connects AccountGame to the existing scene system.
 *
 * IMPORTANT FOR FRONTEND:
 *
 * Rendering and input can be added here later.
 *
 * game.Main already calls:
 *
 *     AccountManagementScene.tick();
 *
 * while ACCOUNT_MANAGEMENT is the current scene.
 *
 * The frontend can use the public methods below
 * to communicate with the AccountGame backend.
 */
public class AccountManagementScene {

    /*
     * The backend instance for this minigame.
     */
    private static final AccountGame accountGame =
            new AccountGame();


    /*
     * Existing Main.tick() expects every scene
     * to have a tick() method.
     *
     * Frontend/input code can be placed here later.
     */
    public static void tick() {

        // FRONTEND TODO:
        //
        // Detect buttons / keyboard / mouse input here
        // and call submitAction().
    }


    /*
     * FRONTEND:
     *
     * Call this to find out which account
     * should currently be displayed.
     */
    public static Account getCurrentAccount() {

        return accountGame.getCurrentAccount();
    }


    /*
     * FRONTEND:
     *
     * Call this when the player chooses an action.
     *
     * Example:
     *
     * AccountResult result =
     *     AccountManagementScene.submitAction(
     *         AccountAction.REMOVE_ADMIN
     *     );
     */
    public static AccountResult submitAction(
            AccountAction action) {

        return accountGame.submitAction(action);
    }


    /*
     * FRONTEND:
     *
     * Can be displayed on screen.
     */
    public static int getScore() {

        return accountGame.getScore();
    }


    /*
     * Returns which account the player is on.
     *
     * Adds 1 because the backend index begins at 0.
     */
    public static int getCurrentAccountNumber() {

        return accountGame.getCurrentIndex() + 1;
    }


    public static int getTotalAccounts() {

        return accountGame.getAccountCount();
    }


    public static boolean isComplete() {

        return accountGame.isComplete();
    }


    /*
     * Can be used if the player wants to replay
     * the minigame.
     */
    public static void reset() {

        accountGame.reset();
    }
}
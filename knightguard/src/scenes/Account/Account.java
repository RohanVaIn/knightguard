package scenes.Account;

/*
 * Represents one account that the player must review.
 *
 * This class only stores data.
 * It does NOT contain any rendering, keyboard, or mouse code.
 */
public class Account {

    private final String username;
    private final String role;

    private final boolean admin;
    private final boolean mfaEnabled;

    private final String passwordStrength;
    private final String lastLogin;

    // The action the player should choose for this account.
    private final AccountAction correctAction;

    // Explanation shown after the player answers.
    private final String explanation;


    public Account(
            String username,
            String role,
            boolean admin,
            boolean mfaEnabled,
            String passwordStrength,
            String lastLogin,
            AccountAction correctAction,
            String explanation) {

        this.username = username;
        this.role = role;
        this.admin = admin;
        this.mfaEnabled = mfaEnabled;
        this.passwordStrength = passwordStrength;
        this.lastLogin = lastLogin;
        this.correctAction = correctAction;
        this.explanation = explanation;
    }


    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean isMfaEnabled() {
        return mfaEnabled;
    }

    public String getPasswordStrength() {
        return passwordStrength;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public AccountAction getCorrectAction() {
        return correctAction;
    }

    public String getExplanation() {
        return explanation;
    }
}
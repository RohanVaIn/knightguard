package scenes.account;

/*
 * Every action the player is allowed to take
 * when reviewing an account.
 *
 * The frontend can connect buttons, keys, etc.
 * to these actions.
 */
public enum AccountAction {
    KEEP,
    DISABLE,
    REMOVE_ADMIN,
    ENABLE_MFA,
    RESET_PASSWORD,
}



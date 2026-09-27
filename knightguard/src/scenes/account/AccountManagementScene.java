package scenes.account;

import game.GameState;
import game.Main;
import game.Scene;
import scenes.TownScene;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

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
    public static final AccountGame accountGame =
            new AccountGame();

    /*
     * Existing Main.tick() expects every scene
     * to have a tick() method.
     *
     * Frontend/input code can be placed here later.
     */
    public static int characterCount = 0;
    public static double backgroundScrollAmount = 0, characterScrollAmount = 0;
    public static double backgroundScrollTarget = 0, characterScrollTarget = 0;
    public static int descriptionScroll = 0;
    public static int explanationScroll = 0;
    public static float descriptionOpacity = 0, descriptionTarget = 1;
    public static float explanationOpacity = 0, explanationTarget = 0;
    public static float buttonsOpacity = 0, buttonsTarget = 1;
    public static boolean showingAnswer = false;
    public static int answerCooldown;
    public static int selectedButton = -1;
    public static boolean isAnswerCorrect = false;
    public static void tick() {

        backgroundScrollAmount += (backgroundScrollTarget - backgroundScrollAmount) * 0.1;
        characterScrollAmount += (characterScrollTarget - characterScrollAmount) * 0.1;
        descriptionOpacity += (descriptionTarget - descriptionOpacity) * 0.1;
        buttonsOpacity += (buttonsTarget - buttonsOpacity) * 0.1;
        explanationOpacity += (explanationTarget - explanationOpacity) * 0.1;

        if(Main.isMouseTapped(1) && answerCooldown == 0) {
//            backgroundScrollTarget += 960;
//            characterScrollTarget += 1920;
////            accountGame.submitAction()
//            ++characterIndex;
            if(characterCount == 5) {
                GameState.flagAccountManagement = true;
                if(accountGame.getScore() > 40) GameState.completionistAccountManagement = true;
                Main.nextScene = Scene.TOWN;
            } else
            if(showingAnswer) {
                showingAnswer = false;
                descriptionOpacity = descriptionTarget = 0;
                explanationOpacity = explanationTarget = 0;
                backgroundScrollTarget += 960;
                characterScrollTarget += 1920;
                descriptionScroll += 1920;
                explanationScroll += 1920;
                answerCooldown = 50;
                ++characterCount;
            } else if(selectedButton != -1) {
                buttonsTarget = 0;
                answerCooldown = 40;

                AccountAction aa = switch(selectedButton) {
                    default -> null;
                    case 0 -> AccountAction.ENABLE_MFA;
                    case 1 -> AccountAction.REMOVE_ADMIN;
                    case 2 -> AccountAction.RESET_PASSWORD;
                    case 3 -> AccountAction.DISABLE;
                    case 4 -> AccountAction.KEEP;
                };
                AccountResult ar = submitAction(aa);
                isAnswerCorrect = ar.isCorrect();
                showingAnswer = true;
            }
        }

        if(answerCooldown > 0) {
            if(--answerCooldown == 0) {
                if(showingAnswer) {
                    explanationTarget = 1;
                } else {
                    descriptionTarget = 1;
                    buttonsTarget = 1;
                }
            }
        } else {
            selectedButton = -1;
            for(int i = 0, y = 514; i < 5; y += 106, ++i) {
                buttonsHighlighted[i] = 88 < Main.mouseX && Main.mouseX < 711 && y < Main.mouseY && Main.mouseY < y + 92;
                if(buttonsHighlighted[i]) selectedButton = i;
            }
        }

    }
    public static BufferedImage imageButtonTemplate, imageHighlightButton,
            imageCharacterScroll, imageBackgroundScroll, imageDescriptions, imageExplanations,
            imageCorrect, imageIncorrect;
    static {
        try {
            imageButtonTemplate = ImageIO.read(Objects.requireNonNull(AccountManagementScene.class.getResourceAsStream("/res/account-buttons.png")));
            imageCharacterScroll = ImageIO.read(Objects.requireNonNull(AccountManagementScene.class.getResourceAsStream("/res/account-characters.png")));
            imageBackgroundScroll = ImageIO.read(Objects.requireNonNull(AccountManagementScene.class.getResourceAsStream("/res/account-backgrounds.png")));
            imageDescriptions = ImageIO.read(Objects.requireNonNull(AccountManagementScene.class.getResourceAsStream("/res/account-descriptions.png")));
            imageExplanations = ImageIO.read(Objects.requireNonNull(AccountManagementScene.class.getResourceAsStream("/res/account-explanations.png")));
            imageHighlightButton = ImageIO.read(Objects.requireNonNull(AccountManagementScene.class.getResourceAsStream("/res/account-highlighted.png")));
            imageCorrect = ImageIO.read(Objects.requireNonNull(AccountManagementScene.class.getResourceAsStream("/res/account-correct.png")));
            imageIncorrect = ImageIO.read(Objects.requireNonNull(AccountManagementScene.class.getResourceAsStream("/res/account-incorrect.png")));
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean[] buttonsHighlighted = new boolean[5];
    /*
     * Render scheiße idk what to put here i don't usually do dev comments
     */
    public static void render(Graphics2D g2d) {
        g2d.drawImage(imageBackgroundScroll,
                0, 0, 1920, 1080,
                (int) backgroundScrollAmount, 0, (int) backgroundScrollAmount + 1920, 1080,
                null);
        g2d.drawImage(imageCharacterScroll,
                0, 0, 1920, 1080,
                (int) characterScrollAmount, 0, (int) characterScrollAmount + 1920, 1080,
                null);
        if(characterCount < 5) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, buttonsOpacity));
            g2d.drawImage(imageButtonTemplate, 0, 0, null);
//        for(int i = 0, y = 514; i < 5; y += 106, ++i) {
//            if(buttonsHighlighted[i]) g2d.drawImage(imageHighlightButton, 88, y, null);
//        }
            if(selectedButton != -1) g2d.drawImage(imageHighlightButton, 88, 514 + 106 * selectedButton, null);
        }
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, descriptionOpacity));
        g2d.drawImage(imageDescriptions,
                0, 0, 1920, 1080,
                descriptionScroll, 0, descriptionScroll + 1920, 1080,
                null);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, explanationOpacity));
        g2d.drawImage(isAnswerCorrect ? imageCorrect : imageIncorrect, 0, 0, null);
        g2d.drawImage(imageExplanations,
                0, 0, 1920, 1080,
                explanationScroll, 0, explanationScroll + 1920, 1080,
                null);
        g2d.setComposite(AlphaComposite.SrcOver);
        int offset = (int) Math.floor(Math.log10(accountGame.getScore()));
        g2d.setColor(new Color(63, 72, 204));
        g2d.setFont(new Font("Segoe UI", Font.PLAIN, 80));
        g2d.drawString("Points: " + Integer.toString(accountGame.getScore(), 10), 1600 - offset * 40, 1080 - 35);
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
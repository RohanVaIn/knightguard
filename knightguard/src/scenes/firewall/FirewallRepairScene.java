package scenes.firewall;

import game.GameState;
import game.Main;
import game.Scene;
import scenes.account.AccountAction;
import scenes.account.AccountManagementScene;
import scenes.account.AccountResult;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class FirewallRepairScene {
    public static int entryCount = 0;
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
            if(entryCount == 5) {
                GameState.flagFirewallRepair = true;
                if(firewallGame.getScore() > 40) GameState.completionistFirewallRepair = true;
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
                ++entryCount;
            } else if(selectedButton != -1) {
                buttonsTarget = 0;
                answerCooldown = 40;

                FirewallAction fa = switch(selectedButton) {
                    default -> null;
                    case 0 -> FirewallAction.ALLOW;
                    case 1 -> FirewallAction.DENY;
                };
                FirewallResult fr = submitAction(fa);
                isAnswerCorrect = fr.isCorrect();
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
            for(int i = 0, y = 833; i < 2; y += 106, ++i) {
                buttonsHighlighted[i] = 88 < Main.mouseX && Main.mouseX < 711 && y < Main.mouseY && Main.mouseY < y + 92;
                if(buttonsHighlighted[i]) selectedButton = i;
            }
        }
    }
    public static boolean[] buttonsHighlighted = new boolean[2];
    public static BufferedImage imageButtonTemplate, imageHighlightButton,
            imageCharacterScroll, imageBackgroundScroll, imageDescriptions, imageExplanations,
            imageCorrect, imageIncorrect;
    static {
        try {
            imageButtonTemplate = ImageIO.read(Objects.requireNonNull(AccountManagementScene.class.getResourceAsStream("/res/firewall-buttons.png")));
            imageCharacterScroll = ImageIO.read(Objects.requireNonNull(AccountManagementScene.class.getResourceAsStream("/res/firewall-characters.png")));
            imageBackgroundScroll = ImageIO.read(Objects.requireNonNull(AccountManagementScene.class.getResourceAsStream("/res/firewall-backgrounds.png")));
            imageDescriptions = ImageIO.read(Objects.requireNonNull(AccountManagementScene.class.getResourceAsStream("/res/firewall-descriptions.png")));
            imageExplanations = ImageIO.read(Objects.requireNonNull(AccountManagementScene.class.getResourceAsStream("/res/firewall-explanations.png")));
            imageHighlightButton = ImageIO.read(Objects.requireNonNull(AccountManagementScene.class.getResourceAsStream("/res/firewall-highlighted.png")));
            imageCorrect = ImageIO.read(Objects.requireNonNull(AccountManagementScene.class.getResourceAsStream("/res/firewall-correct.png")));
            imageIncorrect = ImageIO.read(Objects.requireNonNull(AccountManagementScene.class.getResourceAsStream("/res/firewall-incorrect.png")));
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void render(Graphics2D g2d) {
        g2d.drawImage(imageBackgroundScroll,
                0, 0, 1920, 1080,
                (int) backgroundScrollAmount, 0, (int) backgroundScrollAmount + 1920, 1080,
                null);
        g2d.drawImage(imageCharacterScroll,
                0, 0, 1920, 1080,
                (int) characterScrollAmount, 0, (int) characterScrollAmount + 1920, 1080,
                null);
        if(entryCount < 5) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, buttonsOpacity));
            g2d.drawImage(imageButtonTemplate, 0, 0, null);
            if(selectedButton != -1) g2d.drawImage(imageHighlightButton, 88, 833 + 106 * selectedButton, null);
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
        int offset = (int) Math.floor(Math.log10(firewallGame.getScore()));
        g2d.setColor(new Color(63, 72, 204));
        g2d.setFont(new Font("Segoe UI", Font.PLAIN, 80));
        g2d.drawString("Points: " + Integer.toString(firewallGame.getScore(), 10), 1600 - offset * 40, 1080 - 35);
    }

    public static final FirewallGame firewallGame =
            new FirewallGame();

    public static Traffic getCurrentTraffic() {
        return firewallGame.getCurrentTraffic();
    }

    public static FirewallResult submitAction(
            FirewallAction action) {

        return firewallGame.submitAction(action);
    }

    public static int getScore() {
        return firewallGame.getScore();
    }

    public static int getCorrectAnswers() {
        return firewallGame.getCorrectAnswers();
    }

    public static int getCurrentTrafficNumber() {
        return firewallGame.getCurrentTrafficNumber();
    }

    public static int getTotalTraffic() {
        return firewallGame.getTotalTraffic();
    }

    public static boolean isComplete() {
        return firewallGame.isComplete();
    }

    public static void reset() {
        firewallGame.reset();
    }
}

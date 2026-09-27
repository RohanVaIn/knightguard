package scenes;

import game.GameState;
import game.Main;
import game.Scene;
import scenes.account.AccountManagementScene;
import scenes.firewall.FirewallRepairScene;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class TownScene {
//    public static int tempx = 0, tempy = 0;
    public static BufferedImage mainBackground,
        castleIconRegular, castleIconHighlighted, castleIconComplete,
        wallIconRegular, wallIconHighlighted, wallIconComplete,
        towerIconRegular, towerIconHighlighted, towerIconComplete,
        housesIconRegular, housesIconHighlighted, housesIconComplete,
        centreIconRegular, centreIconHighlighted,
        treeIconRegular, treeIconHighlighted, treeIconComplete;
    public static boolean isCastleHovered,
            isWallHovered,
            isTowerHovered,
            isHousesHovered,
            isCentreHovered,
            isTreeHovered;
    static {
        try {
            mainBackground = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/townbackground.png")));
            castleIconRegular = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/towncastle-regular.png")));
            castleIconHighlighted = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/towncastle-highlighted.png")));
            castleIconComplete = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/towncastle-complete.png")));
            wallIconRegular = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/townwall-regular.png")));
            wallIconHighlighted = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/townwall-highlighted.png")));
            wallIconComplete = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/townwall-complete.png")));
            towerIconRegular = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/towntower-regular.png")));
            towerIconHighlighted = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/towntower-highlighted.png")));
            towerIconComplete = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/towntower-complete.png")));
            housesIconRegular = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/townhouses-regular.png")));
            housesIconHighlighted = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/townhouses-highlighted.png")));
            housesIconComplete = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/townhouses-complete.png")));
            centreIconRegular = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/towncentre-regular.png")));
            centreIconHighlighted = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/towncentre-highlighted.png")));
            treeIconRegular = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/towntree-regular.png")));
            treeIconHighlighted = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/towntree-highlighted.png")));
            treeIconComplete = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/towntree-complete.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void tick() {
//        if(Main.isKeyHeld(KeyEvent.VK_A)) tempx -= 6;
//        if(Main.isKeyHeld(KeyEvent.VK_D)) tempx += 6;
//        if(Main.isKeyHeld(KeyEvent.VK_W)) tempy -= 6;
//        if(Main.isKeyHeld(KeyEvent.VK_S)) tempy += 6;

        boolean leftClickTap = Main.isMouseTapped(1);

//        if(Main.isKeyTapped(KeyEvent.VK_Q)) Main.fadeTarget = 1 - Main.fadeTarget;

        isCastleHovered = 682 < Main.mouseX && Main.mouseX < 1269 && 25 < Main.mouseY && Main.mouseY < 360;
        isWallHovered = 1436 < Main.mouseX && Main.mouseX < 1920 && 0 < Main.mouseY && Main.mouseY < 487;
        isTowerHovered = 248 < Main.mouseX && Main.mouseX < 305 && 304 < Main.mouseY && Main.mouseY < 514;
        isHousesHovered = 1003 < Main.mouseX && Main.mouseX < 1920 && 708 < Main.mouseY && Main.mouseY < 1080;
        isCentreHovered = 896 < Main.mouseX && Main.mouseX < 1131 && 535 < Main.mouseY && Main.mouseY < 660;
        isTreeHovered = 65 < Main.mouseX && Main.mouseX < 160 && 43 < Main.mouseY && Main.mouseY < 167;

        if(leftClickTap) {
            if(isCastleHovered && !GameState.completionistAccountManagement) {
                Main.nextScene = Scene.ACCOUNT_MANAGEMENT;
                AccountManagementScene.accountGame.reset();
                AccountManagementScene.descriptionOpacity = AccountManagementScene.descriptionTarget = 1;
                AccountManagementScene.explanationOpacity = AccountManagementScene.explanationTarget = 0;
                AccountManagementScene.backgroundScrollTarget = AccountManagementScene.backgroundScrollAmount = 0;
                AccountManagementScene.characterScrollTarget = AccountManagementScene.characterScrollAmount = 0;
                AccountManagementScene.descriptionScroll = 0;
                AccountManagementScene.explanationScroll = 0;
                AccountManagementScene.characterCount = 0;
                AccountManagementScene.showingAnswer = false;
            } else if(isWallHovered && !GameState.completionistFirewallRepair) {
                Main.nextScene = Scene.FIREWALL_REPAIR;
                FirewallRepairScene.firewallGame.reset();
                FirewallRepairScene.descriptionOpacity = FirewallRepairScene.descriptionTarget = 1;
                FirewallRepairScene.explanationOpacity = FirewallRepairScene.explanationTarget = 0;
                FirewallRepairScene.backgroundScrollTarget = FirewallRepairScene.backgroundScrollAmount = 0;
                FirewallRepairScene.characterScrollTarget = FirewallRepairScene.characterScrollAmount = 0;
                FirewallRepairScene.descriptionScroll = 0;
                FirewallRepairScene.explanationScroll = 0;
                FirewallRepairScene.entryCount = 0;
                FirewallRepairScene.showingAnswer = false;
            } else if(isTowerHovered && !GameState.completionistMalwareHunter) {
//                Main.nextScene = Scene.MALWARE_HUNTERS;
            } else if(isHousesHovered && !GameState.completionistCaptureTheFlag) {
//                Main.nextScene = Scene.CAPTURE_THE_FLAG;
            } else if(isCentreHovered) {
                Main.nextScene = Scene.TOWN_CENTRE;
                TownCentreScene.eafyTalkIndex = 2;
            } else if(isTreeHovered && !GameState.completionistSecretMan) {
                Main.nextScene = Scene.SECRET_MAN;
            }
        }
    }
    public static void render(Graphics2D g2d) {
        g2d.drawImage(TownScene.mainBackground, 0, 0, null);

        g2d.drawImage(GameState.completionistFirewallRepair ? TownScene.wallIconComplete : TownScene.isWallHovered ? TownScene.wallIconHighlighted : TownScene.wallIconRegular, 1294, 0, null);
        g2d.drawImage(GameState.completionistAccountManagement ? TownScene.castleIconComplete : TownScene.isCastleHovered ? TownScene.castleIconHighlighted : TownScene.castleIconRegular, 659, 0, null);
        g2d.drawImage(GameState.completionistMalwareHunter ? TownScene.towerIconComplete : TownScene.isTowerHovered ? TownScene.towerIconHighlighted : TownScene.towerIconRegular, 205, 292, null);
        g2d.drawImage(GameState.completionistCaptureTheFlag ? TownScene.housesIconComplete : TownScene.isHousesHovered ? TownScene.housesIconHighlighted : TownScene.housesIconRegular, 929, 611, null);
        g2d.drawImage(TownScene.isCentreHovered ? TownScene.centreIconHighlighted : TownScene.centreIconRegular, 821, 476, null);
        g2d.drawImage(GameState.completionistSecretMan ? TownScene.treeIconComplete : TownScene.isTreeHovered ? TownScene.treeIconHighlighted : TownScene.treeIconRegular, 0, 0, null);

//                        g2d.drawImage(TownScene.phatbilly, TownScene.tempx, TownScene.tempy, null);
    }
}

package scenes;

import game.GameState;
import game.Main;
import game.Scene;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class TownCentreScene {
    public static void tick() {

        vertoff += (-vertoff) * 0.3;

        switch(eafyTalkIndex) {
            case 0, 2 -> {
                selectedButton = -1;
                for(int i = 0, y = 713; i < 2; y += 168, ++i) {
                    buttonsHighlighted[i] = 73 < Main.mouseX && Main.mouseX < 1153 && y < Main.mouseY && Main.mouseY < y + 142;
                    if(buttonsHighlighted[i]) selectedButton = i;
                }
                if(Main.isMouseTapped(1)) {
                    if(selectedButton == 0) {
                        vertoff = vertoffbump;
                        eafyTalkIndex = 3;
                    } else if(selectedButton == 1) {
                        vertoff = vertoffbump;
                        eafyTalkIndex = 1;
                    }
                }
            }
            case 1 -> {
                selectedButton = -1;
                if(73 < Main.mouseX && Main.mouseX < 1153 && 713 < Main.mouseY && Main.mouseY < 855) selectedButton = 0;

                if(Main.isMouseTapped(1)) {
                    if(selectedButton == 0) {
                        vertoff = vertoffbump;
                        eafyTalkIndex = 3;
                    }
                }
            }
            case 3 -> {
                if(Main.isMouseTapped(1)) {
                    vertoff = vertoffbump;
                    if(!GameState.flagAccountManagement) eafyTalkIndex = 7;
                    else if(!GameState.flagCaptureTheFlag) eafyTalkIndex = 5;
                    else if(!GameState.flagFirewallRepair) eafyTalkIndex = 6;
                    else if(!GameState.flagMalwareHunter) eafyTalkIndex = 4;
                }
            }
            case 4, 5, 6, 7 -> {
                if(Main.isMouseTapped(1)) {
                    vertoff = vertoffbump;
                    eafyTalkIndex = 8;
                }
            }
            case 8 -> {
                if(Main.isMouseTapped(1)) {
                    Main.nextScene = Scene.TOWN;
                }
            }
        }
    }
    static boolean[] buttonsHighlighted = new boolean[2];
    static int selectedButton = -1;
    static int vertoff = 0;
    static final int vertoffbump = 27;
    public static BufferedImage imageBackground, imageLoneTextbox, imageTextboxes, imageHighlighted;
    public static BufferedImage imageOpening,
            imageHappy,
            imageGreeting,
            imageWellLetsSee,
            imageScrolls,
            imageLogs,
            imageWall,
            imageCastle,
            imageRevisit,
            imageKing1,
            imageKing2,
            imageKing3;
    public static BufferedImage eafyHappy, eafyTalk, eafySurprise, eafySerious;
    static {
        try {
            imageOpening = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/eafy-opening.png")));
            imageHappy = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/eafy-happy.png")));
            imageGreeting = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/eafy-greeting.png")));
            imageWellLetsSee = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/eafy-wellletssee.png")));
            imageScrolls = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/eafy-scrolls.png")));
            imageLogs = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/eafy-logs.png")));
            imageWall = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/eafy-wall.png")));
            imageCastle = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/eafy-castle.png")));
            imageRevisit = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/eafy-revisit.png")));
//            imageKing1 = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/eafy-king1.png")));
//            imageKing2 = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/eafy-king2.png")));
//            imageKing3 = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/eafy-king3.png")));

            eafyHappy = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/centre-happy.png")));
            eafyTalk = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/centre-talk.png")));
            eafySurprise = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/centre-concern.png")));
            eafySerious = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/centre-panic.png")));

            imageBackground = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/centre-background.png")));

            imageTextboxes = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/centre-textboxes.png")));
            imageLoneTextbox = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/centre-lonetextbox.png")));
            imageHighlighted = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/centre-highlighted.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static int eafyTalkIndex = 0;
//    static int eafyOptionsIndex = 0;
    public static void render(Graphics2D g2d) {
        g2d.drawImage(imageBackground, 0, 0, null);

        switch(eafyTalkIndex) {
            default -> {}
            case 0 -> {
                g2d.drawImage(imageOpening, null, null);
            }
            case 1 -> {
                g2d.drawImage(imageHappy, null, null);
            }
            case 2 -> {
                g2d.drawImage(imageGreeting, null, null);
            }
            case 3 -> {
                g2d.drawImage(imageWellLetsSee, null, null);
            }
            case 4 -> {
                g2d.drawImage(imageScrolls, null, null);
            }
            case 5 -> {
                g2d.drawImage(imageLogs, null, null);
            }
            case 6 -> {
                g2d.drawImage(imageWall, null, null);
            }
            case 7 -> {
                g2d.drawImage(imageCastle, null, null);
            }
            case 8 -> {
                g2d.drawImage(imageRevisit, null, null);
            }
        }

        switch(eafyTalkIndex) {
            default -> {}
            case 0, 2, 3, 4, 5, 6, 7 -> {
                g2d.drawImage(eafyTalk, 0, vertoff, null);
            }
            case 1, 8 -> {
                g2d.drawImage(eafyHappy, 0, vertoff, null);
            }
        }

        switch(eafyTalkIndex) {
            default -> {}
            case 0, 2 -> {
                g2d.drawImage(imageTextboxes, 0, 0, null);
                if(selectedButton != -1) g2d.drawImage(imageHighlighted, 73, 713 + 168 * selectedButton, null);
            }
            case 1 -> {
                g2d.drawImage(imageLoneTextbox, 0, 0, null);
                if(selectedButton != -1) g2d.drawImage(imageHighlighted, 73, 713, null);
            }
        }
    }
}

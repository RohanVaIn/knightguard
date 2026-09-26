package scenes;

import game.Main;

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class TownScene {
    public static int tempx = 0, tempy = 0;
    public static BufferedImage phatbilly,
        mainBackground,
            castleIconRegular, castleIconHighlighted;
    public static boolean isCastleHovered;
    static {
        try {
            phatbilly = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/pb.png")));
            mainBackground = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/townbackground.png")));
            castleIconRegular = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/towncastle-regular.png")));
            castleIconHighlighted = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/towncastle-highlighted.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void tick() {
        if(Main.isKeyHeld(KeyEvent.VK_A)) tempx -= 6;
        if(Main.isKeyHeld(KeyEvent.VK_D)) tempx += 6;
        if(Main.isKeyHeld(KeyEvent.VK_W)) tempy -= 6;
        if(Main.isKeyHeld(KeyEvent.VK_S)) tempy += 6;

//        if(Main.isKeyTapped(KeyEvent.VK_Q)) isCastleHovered = !isCastleHovered;

    }
}

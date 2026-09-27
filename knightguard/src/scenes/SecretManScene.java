package scenes;

import game.GameState;
import game.Main;
import game.Scene;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class SecretManScene {
    public static BufferedImage wtiamh;
    static {
        try {
            wtiamh = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/secret-room.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void tick() {
        if(Main.isMouseTapped(1) || Main.isKeyTapped(KeyEvent.VK_ESCAPE)) {
            Main.nextScene = Scene.TOWN;
            GameState.completionistSecretMan = true;
        }
    }
    public static void render(Graphics2D g2d) {
        g2d.drawImage(SecretManScene.wtiamh, 0, 0, null);
    }
}

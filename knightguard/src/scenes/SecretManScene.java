package scenes;

import javax.imageio.ImageIO;
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

    }
}

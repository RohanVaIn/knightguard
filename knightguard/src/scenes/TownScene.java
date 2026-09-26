package scenes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class TownScene {
    public static int tempx = 0, tempy = 0;
    public static BufferedImage phatbilly,
        mainBackground;
    static {
        try {
            phatbilly = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/pb.png")));
            mainBackground = ImageIO.read(Objects.requireNonNull(TownScene.class.getResourceAsStream("/res/townbackground.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void tick() {

    }
}

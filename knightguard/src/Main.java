import entities.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Main();
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
    public static boolean running = true;
    JPanel render;
    Thread thread;
    public static Player playerEntity;
    public static GameState gameState = GameState.TITLE;
    public Main() throws IOException {
        super("KNightGuard");
        playerEntity = new Player();

        BufferedImage temp = ImageIO.read(new File("res/pb.png"));

        int screenWidth = 1920;
        int screenHeight = 1080;
        render = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int renderWidth = render.getWidth();
                int renderHeight = render.getHeight();
                g.setColor(Color.black);
                g.fillRect(0, 0, renderWidth, renderHeight);

                BufferedImage renderedImage = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = renderedImage.createGraphics();

                double c1 = (double) renderWidth / screenWidth;
                double c2 = (double) renderHeight / screenHeight;
                double mrx, mry, mrw, mrh;
                if (c1 < c2) {
                    mrw = renderWidth;
                    mrh = screenHeight * c1;
                    mrx = 0;
                    mry = (renderHeight - mrh) / 2;
                } else {
                    mrw = screenWidth * c2;
                    mrh = renderHeight;
                    mrx = (renderWidth - mrw) / 2;
                    mry = 0;
                }
                double cmin = Math.min(c1, c2);

                /** -------------------------------- */

                switch(gameState) {
                    case TITLE -> {

                        break;
                    }
                    case TOWN -> {

                    }
                    case MINIGAME_1 -> {
                    }
                    case MINIGAME_2 -> {
                    }
                    case MINIGAME_3 -> {
                    }
                    case MINIGAME_4 -> {
                    }
                    case MINIGAME_5 -> {
                    }
                }

                g2d.setColor(new Color(100,200,255,255));
                g2d.fillRect(0, 0, screenWidth, screenHeight);

                /** -------------------------------- */

                g.drawImage(renderedImage, (int) mrx, (int) mry, (int) mrw, (int) mrh, null);
                g2d.dispose();
                g.dispose();

            }
        };
        this.setContentPane(render);

        thread = new Thread(() -> {
            long oldTime = System.nanoTime();
            long newTime;
            long elapsed = 0;
            final long tickSecond = (long) (1e9 / 60);
//            final long renderSecond = (long) (1e9 / 60);
            while(running) {
                newTime = System.nanoTime();
                elapsed += newTime - oldTime;
                oldTime = newTime;

                while(elapsed >= tickSecond) {
                    elapsed -= tickSecond;
                    tick();
                }

//                if(running) {
//                    render.repaint();
//                }
            }
        });
        thread.start();


//        this.setLocationRelativeTo(null);
        this.setSize(new Dimension(screenWidth / 4, screenHeight / 4));
        this.setVisible(true);
    }
    public void tick() {

        playerEntity.entityTick();
    }
//    public void render() {
//        render.repaint();
//    }
}
import scenes.CaptureTheFlagScene;
import scenes.TownScene;
import scenes.Account.AccountManagementScene;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;

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
    public static volatile HashSet<Integer> keys = new HashSet<>();
    public static volatile HashSet<Integer> buttons = new HashSet<>();
//    public static Player playerEntity;
    public static GameState gameState = GameState.TOWN;
    public Main() throws IOException {
        super("KNightGuard");
//        playerEntity = new Player();

        BufferedImage temp = ImageIO.read(Objects.requireNonNull(Main.class.getResourceAsStream("/res/pb.png")));

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

                g2d.setColor(new Color(100,200,255,255));
                g2d.fillRect(0, 0, screenWidth, screenHeight);

                switch(gameState) {
                    case TITLE -> {

                    }
                    case TOWN -> {
                        g2d.drawImage(TownScene.mainBackground, 0, 0, null);
                        g2d.drawImage(TownScene.phatbilly, TownScene.tempx, TownScene.tempy, null);
                    }
                    case ACCOUNT_MANAGEMENT -> {
                    }
                    case CAPTURE_THE_FLAG -> {
                    }
                    case MINIGAME_3 -> {
                    }
                    case MINIGAME_4 -> {
                    }
                    case MINIGAME_5 -> {
                    }
                }


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

                if(running) {
                    render.repaint();
                }
            }
        });
        thread.start();


//        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("handle closing");
                running = false;
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keys.add(e.getKeyCode());
            }
            @Override
            public void keyReleased(KeyEvent e) {
                keys.remove(e.getKeyCode());
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                buttons.add(e.getButton());
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                buttons.remove(e.getButton());
            }
        });
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setIconImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/icon.png"))));
        this.setSize(new Dimension(screenWidth / 4, screenHeight / 4));
        this.setVisible(true);
    }
    public void tick() {

        switch(gameState) {
            case TITLE -> {
            }
            case TOWN -> {
                if(keys.contains(KeyEvent.VK_A)) TownScene.tempx -= 6;
                if(keys.contains(KeyEvent.VK_D)) TownScene.tempx += 6;
                if(keys.contains(KeyEvent.VK_W)) TownScene.tempy -= 6;
                if(keys.contains(KeyEvent.VK_S)) TownScene.tempy += 6;
            }
            case ACCOUNT_MANAGEMENT -> {
                AccountManagementScene.tick();
            }
            case CAPTURE_THE_FLAG -> {
                CaptureTheFlagScene.tick();
            }
            case MINIGAME_3 -> {
            }
            case MINIGAME_4 -> {
            }
            case MINIGAME_5 -> {
            }
        }
//        playerEntity.entityTick();

    }
//    public void render() {
//        render.repaint();
//    }
}
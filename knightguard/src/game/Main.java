package game;

import scenes.*;
import scenes.account.AccountManagementScene;
import scenes.firewall.FirewallRepairScene;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Main extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Class.forName("scenes.account.AccountManagementScene");
                new Main();
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
    public static boolean running = true;
    int renderWidth, renderHeight, screenWidth = 1920, screenHeight = 1080;
    JPanel render;
    Thread thread;
    public static final Set<Integer> keys = Collections.synchronizedSet(new HashSet<>()), buttons = Collections.synchronizedSet(new HashSet<>());
    public static Set<Integer> keysPrevious = new HashSet<>(), buttonsPrevious = new HashSet<>();
    public static boolean mouseOnScreen = false;
    Point mouseJFramePosition = new Point(-1, -1);
    public static int mouseX, mouseY;
    public static Scene currentScene = Scene.TOWN_CENTRE, nextScene = currentScene;
    public static int fadeTarget = 0;
    public static float fadeOpacity = 0;
    public static int fadeTimer = 0;
    public Main() throws IOException {
        render = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                renderWidth = render.getWidth();
                renderHeight = render.getHeight();
                g.setColor(Color.black);
                g.fillRect(0, 0, renderWidth, renderHeight);

                BufferedImage renderedImage = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = renderedImage.createGraphics();

                double c1 = (double) renderWidth / screenWidth;
                double c2 = (double) renderHeight / screenHeight;
                double mrx, mry, mrw, mrh;
                if(c1 < c2) {
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

                switch(currentScene) {
                    case TITLE -> TitleScene.render(g2d);
                    case TOWN -> TownScene.render(g2d);
                    case ACCOUNT_MANAGEMENT -> AccountManagementScene.render(g2d);
                    case CAPTURE_THE_FLAG -> CaptureTheFlagScene.render(g2d);
                    case MALWARE_HUNTERS -> MalwareHunterScene.render(g2d);
                    case FIREWALL_REPAIR -> FirewallRepairScene.render(g2d);
                    case MINIGAME_5 -> {}
                    case TOWN_CENTRE -> TownCentreScene.render(g2d);
                    case SECRET_MAN -> SecretManScene.render(g2d);
                }

                // fade to black
                g2d.setColor(new Color(0f,0f,0f,fadeOpacity));
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

                render.repaint();
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
        render.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                buttons.add(e.getButton());
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                buttons.remove(e.getButton());
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                mouseOnScreen = true;
            }
            @Override
            public void mouseExited(MouseEvent e) {
                mouseOnScreen = false;
            }
        });
        render.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseJFramePosition = e.getPoint();
            }
        });
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setTitle("KNightGuard");
        this.setIconImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/icon.png"))));
        this.setSize(new Dimension(screenWidth / 2, screenHeight / 2));
        this.setVisible(true);
    }
    public void tick() {

        double c1 = (double) renderWidth / screenWidth;
        double c2 = (double) renderHeight / screenHeight;
        double mrx = 0, mry = 0;
        if(c1 < c2) {
            mry = (renderHeight - screenHeight * c1) / 2;
        } else {
            mrx = (renderWidth - screenWidth * c2) / 2;
        }
        double cmin = Math.min(c1, c2);

        if(mouseOnScreen) {
            mouseX = (int) ((mouseJFramePosition.x - mrx) / cmin);
            mouseY = (int) ((mouseJFramePosition.y - mry) / cmin);
        } else {
            mouseX = -1;
            mouseY = -1;
        }

        fadeOpacity += (fadeTarget - fadeOpacity) * 0.06f;

        switch(currentScene) {
            case TITLE -> TitleScene.tick();
            case TOWN -> TownScene.tick();
            case ACCOUNT_MANAGEMENT -> AccountManagementScene.tick();
            case CAPTURE_THE_FLAG -> CaptureTheFlagScene.tick();
            case MALWARE_HUNTERS -> MalwareHunterScene.tick();
            case FIREWALL_REPAIR -> FirewallRepairScene.tick();
            case MINIGAME_5 -> {}
            case TOWN_CENTRE -> TownCentreScene.tick();
            case SECRET_MAN -> SecretManScene.tick();
        }

        if(currentScene != nextScene) {
            if(fadeTimer == 0) {
                fadeTimer = 50;
                fadeOpacity = 0f;
                fadeTarget = 1;
            } else if(--fadeTimer == 0) {
                currentScene = nextScene;
                fadeOpacity = 1f;
                fadeTarget = 0;
            }
        }

        synchronized(keys) {
            keysPrevious = new HashSet<>(keys);
        }
        synchronized(buttons) {
            buttonsPrevious = new HashSet<>(buttons);
        }

    }
    public static boolean isKeyHeld(int keyCode) {
        return keys.contains(keyCode);
    }
    public static boolean isKeyTapped(int keyCode) {
        return keys.contains(keyCode) && !keysPrevious.contains(keyCode);
    }
    public static boolean isMouseHeld(int keyCode) {
        return buttons.contains(keyCode);
    }
    public static boolean isMouseTapped(int keyCode) {
        return buttons.contains(keyCode) && !buttonsPrevious.contains(keyCode);
    }
}
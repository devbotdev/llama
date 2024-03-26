package Va;

import Game.GamePanel;
import Panels.resource.GUI;

import javax.swing.*;
import java.awt.*;

public class Vars {

    public Vars() {
        refresh();
    }
    private static final int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final double widthScale = (double) screenWidth / 1920;
    private static final double heightScale = (double) screenHeight / 1080;
    private static final double screenScale = (double) ((screenWidth + screenHeight) / 2) / 1500;
    public static int getScreenWidth() {
        return screenWidth;
    }
    public static int getScreenHeight() {
        return screenHeight;
    }

    public static double getScreenScale() {
        return screenScale;
    }

    public static double getWidthScale() {
        return widthScale;
    }

    public static double getHeightScale() {
        return heightScale;
    }

    public static final ImageIcon portrait = new ImageIcon(System.getProperty("user.dir") + "\\src\\images\\");
    public static ImageIcon home = new ImageIcon(System.getProperty("user.dir") + "\\src\\images\\Home.png");
    public static final Image image = home.getImage().getScaledInstance(getScreenWidth(), getScreenHeight(), Image.SCALE_SMOOTH);
    public static ImageIcon garage = new ImageIcon(System.getProperty("user.dir") + "\\src\\images\\garage.png");
    public static JLabel background, background1;
    public static JFrame frame;
    public static GUI buttons = new GUI();
    private static Dimension dimension;
    private static Dimension inGameButtonDimension;
    private static Font menuFont;
    public static int foodType = 1;
    public static boolean bossMode = false;
    public static boolean gameStarted = false;
    public static GamePanel gamePanel;

    public static void refresh() {
        menuFont = new Font("Serif", Font.BOLD, (int) (25 * getScreenScale()));
        dimension = new Dimension((int) (300 * getWidthScale()), (int) (50 * getHeightScale()));
        inGameButtonDimension = new Dimension((int) (700 * getWidthScale()), (int) (50 * getHeightScale()));
    }

    public static Dimension getDimension() {
        return dimension;
    }

    public static Dimension getInGameButtonDimension() {
        return inGameButtonDimension;
    }

    public static Font getMenuFont() {
        return menuFont;
    }

    public static void toggleMenu() {
        frame.setVisible(!gameStarted);
    }

    public static void toggleMenu(boolean b) {
        frame.setVisible(b);
    }

    public static void setPanelEnabled(JFrame panel, Boolean isEnabled) {
        panel.setEnabled(isEnabled);

        Component[] components = panel.getComponents();

        for (Component component : components) {
            if (component instanceof JFrame) {
                setPanelEnabled((JFrame) component, isEnabled);
            }
            component.setEnabled(isEnabled);
        }
    }
}

package Va;

import resource.GUI;

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
    public static JLabel background;
    public static JFrame frame;
    public static GUI buttons = new GUI();
    private static Dimension dimension;
    private static Font menuFont;
    public static int foodType = 1;
    public static boolean bossMode = false;

    public static void refresh() {
        menuFont = new Font("Serif", Font.BOLD, (int) (25 * getScreenScale()));
        dimension = new Dimension((int) (300 * getWidthScale()), (int) (50 * getHeightScale()));
    }

    public static Dimension getDimension() {
        return dimension;
    }

    public static Font getMenuFont() {
        return menuFont;
    }
}

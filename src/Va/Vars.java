package Va;

import resource.GUI;

import javax.swing.*;
import java.awt.*;

public class Vars {
    private static final int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final int screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
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

    public static final ImageIcon portrait = new ImageIcon("C:\\Users\\f\\IdeaProjects\\untitled\\src\\images\\file.png");
    public static ImageIcon home = new ImageIcon("C:\\Users\\f\\IdeaProjects\\untitled\\src\\images\\Home.png");
    public static final Image image = home.getImage().getScaledInstance(getScreenWidth(), getScreenHeight(), Image.SCALE_SMOOTH);
    public static JLabel background;
    public static JFrame frame;
    public static GUI buttons = new GUI();
    private static Dimension dimension = new Dimension(300, 50);
    private static Font menuFont = new Font("Serif", Font.BOLD, 25);
    public static int foodType = 1;
    public static boolean bossMode = false;

    public static void refresh() {
        menuFont = new Font("Serif", Font.BOLD, 25);
        dimension = new Dimension(300, 50);
    }

    public static Dimension getDimension() {
        return dimension;
    }

    public static Font getMenuFont() {
        return menuFont;
    }
}

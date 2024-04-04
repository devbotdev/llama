package Variables;

import Game.GamePanel;
import Panels.resource.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Vars {

    public static boolean javaIsShit;
    public static boolean isJavaGay;
    public Vars() {
        refresh();
    }
    public static final int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static final int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final double widthScale = (double) screenWidth / 1920;
    private static final double heightScale = (double) screenHeight / 1080;
    private static final double screenScale = (double) ((screenWidth + screenHeight) / 2) / 1500;
    public static int getScreenWidthScaled() {
        return (int) (screenWidth * getWidthScale());
    }
    public static int getScreenHeightScaled() {
        return (int) (screenHeight * getHeightScale());
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

    public final static String disabledBacon = "bacon1.png",
            disabledSteak = "steak2.png",
            disabledFrenchFries = "frenchfries3.png",
            disabledPizza = "pizza4.png",
            disabledHamburger = "hamburger0.png";


    public final static String enabledBacon = "baconO1.png",
            enabledSteak = "steakO2.png",
            enabledFrenchFries = "frenchfriesO3.png",
            enabledPizza = "pizzaO4.png",
            enabledHamburger = "hamburgerO0.png";
    private static String pauseMenuImageString;
    public static final Color blankColor = new Color(0,0,0,0);
    private static ImageIcon portrait = new ImageIcon(System.getProperty("user.dir") + "\\src\\images\\file.png");

    public static ImageIcon getPortrait(int i) {
        portrait = new ImageIcon(portrait.getImage().getScaledInstance((int) (i * widthScale), (int) (i * heightScale), Image.SCALE_SMOOTH));
        return portrait;
    }

    private static ImageIcon home = new ImageIcon(System.getProperty("user.dir") + "\\src\\images\\Home.png");;

    public static ImageIcon getHome() {
        home = new ImageIcon(home.getImage().getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH));
        return home;
    }
    public static ImageIcon pauseMenuImage;
    public static ImageIcon getPauseMenuImage() {
        pauseMenuImage = new ImageIcon(pauseMenuImage.getImage().getScaledInstance(getScreenWidthScaled(), getScreenHeightScaled(), Image.SCALE_SMOOTH));
        return pauseMenuImage;
    }
    public static JFrame frame;
    public final static MainMenu buttons = new MainMenu();
    private static Dimension dimension;
    private static Dimension inGameButtonDimension;
    private static Font menuFont;
    public static int foodType;
    public static boolean bossMode;
    public static boolean gameStarted;
    public static GamePanel gamePanel;
    public static boolean optionsPressed;
    public static boolean codeIsWritten;

    public static void refresh() {
        if (!bossMode) {
            setString(disabledHamburger, disabledBacon, disabledSteak, disabledFrenchFries, disabledPizza);
        } else {
            setString(enabledHamburger, enabledBacon, enabledSteak, enabledFrenchFries, enabledPizza);
        }

        menuFont = new Font("Serif", Font.BOLD, (int) (25 * getScreenScale()));
        dimension = new Dimension((int) (300 * getWidthScale()), (int) (50 * getHeightScale()));
        inGameButtonDimension = new Dimension((int) (700 * getWidthScale()), (int) (50 * getHeightScale()));

        pauseMenuImage = new ImageIcon(System.getProperty("user.dir") + "\\src\\images\\" + pauseMenuImageString);
    }

    private static void setString(String enabledHamburger, String enabledBacon, String enabledSteak, String enabledFrenchFries, String enabledPizza) {
        if (foodType == 0) {
            pauseMenuImageString = enabledHamburger;
        } else if (foodType == 1) {
            pauseMenuImageString = enabledBacon;
        } else if (Vars.foodType == 2) {
            pauseMenuImageString = enabledSteak;
        } else if (Vars.foodType == 3) {
            pauseMenuImageString = enabledFrenchFries;
        } else if (foodType == 4) {
            pauseMenuImageString = enabledPizza;
        }
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

    private static final File path;

    static {
        try {
            path = new File(new File(Vars.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    public static void restart() throws IOException {
        exec(path.getPath());
        System.exit(0);
    }
    private static void exec(String path) throws IOException {
        String javaHome = System.getProperty("java.home");
        String javaBin = javaHome +
                File.separator + "bin" +
                File.separator + "java";

        ProcessBuilder pb = new ProcessBuilder(javaBin, "-jar", path);
        pb.start();
    }
}

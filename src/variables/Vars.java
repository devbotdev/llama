package variables;

import panels.resource.MainMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class Vars {

    private static boolean firstStarted = false;

    public static boolean isFirstStarted() {
        return !firstStarted;
    }

    public static void classFirstCreated() {
        if (!firstStarted) firstStarted = true;
    }

    public static boolean debug;
    public static short tileSizeX, tileSizeY;
    public static boolean printFPS;
    public static final byte numberOfMaps = 2;

    public static double getScreenScale() {
        return screenScale;
    }

    public static double getWidthScale() {
        return widthScale;
    }

    public static double getHeightScale() {
        return heightScale;
    }

    public final static String directory = System.getProperty("user.dir") + "\\src";
    public final static String
            disabledBacon = "bacon1.png",
            disabledSteak = "steak2.png",
            disabledFrenchFries = "frenchfries3.png",
            disabledPizza = "pizza4.png",
            disabledHamburger = "hamburger0.png";


    public final static String
            enabledBacon = "baconO1.png",
            enabledSteak = "steakO2.png",
            enabledFrenchFries = "frenchfriesO3.png",
            enabledPizza = "pizzaO4.png",
            enabledHamburger = "hamburgerO0.png";

    private static String pauseMenuImageString;
    public static final Color blankColor = new Color(0,0,0,0);
    public final static BufferedImage home;


    static {
        refresh();

        try {
            home = ImageIO.read(new File(directory + "\\game_resources\\Home.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            path = new File(new File(Vars.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static BufferedImage pauseImage;

    public static JFrame frame;
    public static MainMenu buttons;
    private static Dimension dimension;
    private static Dimension inGameButtonDimension;
    private static Font menuFont, smallFont;
    public static int foodType;
    public static boolean bossMode;
    public static boolean gameStarted;
    public static boolean optionsPressed;
    public static boolean codeIsWritten;

    public static int screenWidth;
    public static int screenHeight;
    private static double widthScale;
    private static double heightScale;
    private static double screenScale;

    public static final Dimension fullscreenDimension =
            new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());

    public static void refresh() {
        screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        widthScale = (double) screenWidth / 1920;
        heightScale = (double) screenHeight / 1080;
        screenScale = (double) ((screenWidth + screenHeight) / 2) / 1500;
        tileSizeX = (short) (60 * getWidthScale());
        tileSizeY = (short) (60 * getHeightScale());


        if (!bossMode) {
            setString(disabledHamburger, disabledBacon, disabledSteak, disabledFrenchFries, disabledPizza);
        } else {
            setString(enabledHamburger, enabledBacon, enabledSteak, enabledFrenchFries, enabledPizza);
        }

        menuFont = null;
        dimension = null;
        inGameButtonDimension = null;

        menuFont = new Font("Serif", Font.BOLD, (int) (25 * getScreenScale()));
        smallFont = new Font("Serif", Font.BOLD, (int) (20 * getScreenScale()));
        dimension = new Dimension((int) (300 * getWidthScale()), (int) (50 * getHeightScale()));
        inGameButtonDimension = new Dimension((int) (700 * getWidthScale()), (int) (50 * getHeightScale()));

        try {
            pauseImage = ImageIO.read(new File(directory + "\\game_resources\\" + pauseMenuImageString));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setString(String enabledHamburger, String enabledBacon, String enabledSteak, String enabledFrenchFries, String enabledPizza) {
        if (foodType == 0) {
            pauseMenuImageString = enabledHamburger;
        } else if (foodType == 1) {
            pauseMenuImageString = enabledBacon;
        } else if (foodType == 2) {
            pauseMenuImageString = enabledSteak;
        } else if (foodType == 3) {
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
    public static void toggleMenu(boolean b) {
        frame.setVisible(b);

        if (b) {
            buttons.setActionListeners();
        }
    }

    private static final File path;

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

    public static Font getSmallFont() {
        return smallFont;
    }
}

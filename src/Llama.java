import panels.resource.MainMenu;

import javax.swing.*;
import java.awt.*;

import static variables.Vars.*;

public class Llama {

    public static void main(String[] args) {
        System.out.println(directory);

        setVariables();

        debug = true;

        if (debug) {
            System.out.println(getScreenScale());
            System.out.println(getHeightScale());
            System.out.println(getWidthScale());
            System.out.println(screenHeight + ", " + screenWidth);
        }

        createGUI("Orjel Lamaj Type Game");

        frame.add(buttons);

        frame.setVisible(true);
    }

    public static void createGUI(String title) {
        frame = new JFrame(title);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
    }

    public static void setVariables() {
        foodType = 0;
        bossMode = false;
        gameStarted = false;
        optionsPressed = false;
        codeIsWritten = false;

        printFPS = true;

        buttons = new MainMenu();
    }
}
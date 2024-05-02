import javax.swing.*;
import java.awt.*;

import static Variables.Vars.*;

public class Llama {
    public static void main(String[] args) {
        setVariables();

        System.out.println(getScreenScale());
        System.out.println(getHeightScale());
        System.out.println(getWidthScale());

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

    private static void setVariables() {
        foodType = 0;
        bossMode = false;
        gameStarted = false;
        javaIsShit = false;
        optionsPressed = false;
        isJavaGay = false;
        codeIsWritten = false;

        refresh();
    }
}
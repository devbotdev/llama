package Panels.Pause;

import javax.swing.*;
import java.awt.*;

import static Panels.resource.GUI.optionsPressed;
import static Va.Vars.*;

public class Pause {

    private static final JFrame pause = new JFrame("Pause Menu");
    private static final PausePanel panel = new PausePanel();
    public static void pause(boolean visible) {
        optionsPressed = visible;

        pause.setLayout(new BorderLayout());
        pause.setUndecorated(true);
        pause.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        pause.setExtendedState(JFrame.MAXIMIZED_BOTH);
        pause.setLocationRelativeTo(null);

        pause.setBackground(new Color(0, 0, 0, 0));

        pause.add(panel);

        pause.setVisible(visible);
        optionsPressed = !optionsPressed;
    }
}

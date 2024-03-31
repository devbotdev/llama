package Panels.Pause;

import javax.swing.*;
import java.awt.*;

import static Panels.resource.GUI.optionsPressed;
import static Variables.Vars.*;

public class Pause {

    private static final JFrame pause = new JFrame("Pause Menu");
    private static final PausePanel panel = new PausePanel();
    private static final JFrame pauseB = new JFrame("P");
    protected static final JLabel image = new JLabel(getPauseMenuImage());

    public static void pause(boolean visible) {
        optionsPressed = visible;

        if (visible) {
            setFrame(pauseB);
            setFrame(pause);

            pauseB.add(image);
            pause.add(panel);

            pauseB.setVisible(true);
            pause.setVisible(true);
        } else play();

        setPanelEnabled(gamePanel.getFrame(), !visible);
        setPanelEnabled(pauseB, !visible);
    }

    private static void play() {
        pauseB.setVisible(false);
        pauseB.setVisible(false);
    }

    private static void setFrame(JFrame frame) {
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setBackground(new Color(0, 0, 0, 0));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
    }
}

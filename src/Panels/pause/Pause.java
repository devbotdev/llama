package Panels.pause;

import Game.GamePanel;

import javax.swing.*;

import static Game.ControlsHandler.*;
import static Variables.Vars.*;

public class Pause {
    protected static JFrame pause = new JFrame("Pause Menu");
    private static PausePanel panel = new PausePanel();
    private static JFrame pauseB = new JFrame("P");
    protected static JLabel image = new JLabel(pauseMenuImage);
    protected GamePanel gp;
    public Pause(GamePanel gp) {
        this.gp = gp;
    }
    public static void pause(boolean visible) {
        optionsPressed = visible;

        if (visible) {
            gameRunning = false;

            setFrame(pauseB);
            setFrame(pause);

            setPanel();

            pauseB.add(image);
            pause.add(panel);

            pauseB.setVisible(true);
            pause.setVisible(true);
        } else play();
    }

    private static void play() {
        gameRunning = true;
        upPressed = false;
        downPressed = false;
        leftPressed = false;
        rightPressed = false;

        setPanel();

        pauseB.setVisible(false);
        pause.setVisible(false);

        pause.remove(panel);
        pauseB.remove(image);

        javaIsShit = false;
    }

    private static void setPanel() {
        setPanelEnabled(gamePanel.getFrame(), !optionsPressed);
        setPanelEnabled(pauseB, !optionsPressed);
        setPanelEnabled(pause, optionsPressed);
    }

    private static void setFrame(JFrame frame) {
        setDecoration(true, pauseB, pause);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setBackground(blankColor);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
    }

    private static void setDecoration(boolean g, JFrame a, JFrame b) {
        if (!isJavaGay) {
            a.setUndecorated(g);
            b.setUndecorated(g);
            isJavaGay = true;
        }
    }

    public static PausePanel getPausePanel() {
        return panel;
    }
}

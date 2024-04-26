package Panels.pause;

import Game.ControlsHandler;
import Game.GamePanel;

import javax.swing.*;

import static Variables.Vars.*;

public class Pause {

    protected JFrame pause = new JFrame("Pause Menu");
    private PausePanel panel;
    private JFrame pauseB = new JFrame("P");
    protected JLabel image = new JLabel(pauseMenuImage);
    protected GamePanel gp;

    public Pause(GamePanel gp) {
        this.gp = gp;
        panel = new PausePanel(this.gp);
    }

    public void pause(boolean visible) {
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

    private void play() {
        gameRunning = true;
        ControlsHandler.upPressed = false;
        ControlsHandler.downPressed = false;
        ControlsHandler.leftPressed = false;
        ControlsHandler.rightPressed = false;

        setPanel();

        pauseB.setVisible(false);
        pause.setVisible(false);

        pause.remove(panel);
        pauseB.remove(image);

        javaIsShit = false;
    }

    private void setPanel() {
        setPanelEnabled(gp.getFrame(), !optionsPressed);
        setPanelEnabled(gp.pause.pauseB, !optionsPressed);
        setPanelEnabled(pause, optionsPressed);
    }

    private void setFrame(JFrame frame) {
        setDecoration(true, pauseB, pause);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setBackground(blankColor);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
    }

    private void setDecoration(boolean g, JFrame a, JFrame b) {
        if (!isJavaGay) {
            a.setUndecorated(g);
            b.setUndecorated(g);
            isJavaGay = true;
        }
    }

    public PausePanel getPausePanel() {
        return gp.pause.panel;
    }
}

package Panels.pause;

import Game.GamePanel;

import javax.swing.*;

import java.awt.*;

import static Variables.Vars.*;

public class Pause {

    protected JFrame pause = new JFrame("Pause Menu");
    private final PausePanel panel;
    private final JFrame pauseB = new JFrame("P");
    protected JLabel image = new JLabel(pauseMenuImage);
    protected GamePanel gp;

    public Pause(GamePanel gp) {
        this.gp = gp;
        panel = new PausePanel(this.gp);
    }

    public void pause(boolean visible) {
        optionsPressed = visible;
        if (visible) {
            gp.gameRunning = false;

            gp.sound.pause();

            setFrame(pauseB);
            setFrame(pause);

            setPanel();

            pauseB.add(image);
            pause.add(panel);

            pauseB.setVisible(true);
            pause.setVisible(true);
        } else play();

        System.out.println(optionsPressed);
    }

    private void play() {
        gp.gameRunning = true;

        gp.sound.resume();

        gp.handler.upPressed = false;
        gp.handler.downPressed = false;
        gp.handler.leftPressed = false;
        gp.handler.rightPressed = false;

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

    public void setPanelEnabled(JFrame panel, Boolean isEnabled) {
        panel.setEnabled(isEnabled);

        Component[] components = panel.getComponents();

        for (Component component : components) {
            if (component instanceof JFrame) {
                setPanelEnabled((JFrame) component, isEnabled);
            }
            component.setEnabled(isEnabled);
        }
    }
}

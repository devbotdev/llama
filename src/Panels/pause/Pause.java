package Panels.pause;

import Game.GamePanel;
import Variables.SoundType.SoundType;

import javax.swing.*;

import static Variables.Vars.*;

public class Pause extends JWindow {

    private final PausePanel panel;
    protected GamePanel gp;

    public Pause(GamePanel gp) {
        super();
        this.gp = gp;
        panel = new PausePanel(this.gp);
    }

    public void pause(boolean visible) {
        optionsPressed = visible;

        if (visible) {
            setFocusable(true);
            requestFocus();

            setSize(fullscreenDimension);

            setBackground(blankColor);

            gp.gameRunning = false;

            buttons.sound.pause(SoundType.MUSIC);

            add(panel);

            setVisible(true);
        } else play();
    }

    private void play() {
        gp.gameRunning = true;

        buttons.o.optionsMenu(false);
        buttons.o.soundFrame.soundFrame(false);
        buttons.sound.resume(SoundType.MUSIC);

        gp.handler.upPressed = false;
        gp.handler.downPressed = false;
        gp.handler.leftPressed = false;
        gp.handler.rightPressed = false;

        setVisible(false);

        remove(panel);

        javaIsShit = false;
    }

    public PausePanel getPausePanel() {
        return gp.pause.panel;
    }
}

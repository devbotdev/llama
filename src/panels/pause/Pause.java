package panels.pause;

import game.GamePanel;
import variables.sound.SoundType;

import javax.swing.*;

import static variables.Vars.*;

public class Pause extends JWindow {

    private PausePanel panel;
    protected GamePanel gp;

    public Pause(GamePanel gp) {
        super();
        this.gp = gp;

        panel = new PausePanel(this.gp);
        setAlwaysOnTop(true);
    }

    public void selfDestruct() {
        panel.selfDestruct();

        panel = null;
        gp = null;

        removeAll();
        dispose();
    }

    public void pause(boolean visible, boolean gameRunning) {
        gp.pausePressed = visible;

        if (gp.gameOver) return;

        if (visible) {
            setFocusable(true);

            requestFocus();

            setSize(fullscreenDimension);

            setBackground(blankColor);

            gp.gameRunning = false;

            buttons.sound.pause(SoundType.MUSIC);

            add(panel);

            setVisible(true);
        } else play(gameRunning);
    }

    private void play(boolean gameRunning) {
        gp.gameRunning = gameRunning;

        buttons.o.optionsMenu(false);
        buttons.o.soundFrame.soundFrame(false);
        buttons.sound.resume(SoundType.MUSIC);

        gp.orjeli.upPressed = false;
        gp.orjeli.downPressed = false;
        gp.orjeli.leftPressed = false;
        gp.orjeli.rightPressed = false;

        remove(panel);

        setVisible(false);
    }

    public PausePanel getPausePanel() {
        return gp.pause.panel;
    }
}

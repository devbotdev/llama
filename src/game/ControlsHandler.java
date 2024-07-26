package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControlsHandler implements KeyListener {

    public static int a, b;
    private final GamePanel gp;

    public ControlsHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent event) {
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (!gp.gameRunning) return;

        a = event.getKeyCode();
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (!gp.gameRunning) return;

        b = event.getKeyCode();
    }
}
package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControlsHandler implements KeyListener {

    public static int a, b;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
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

        if (a == KeyEvent.VK_ESCAPE) {
            gp.pause.pause(true);
        }

        if (ControlsHandler.a == KeyEvent.VK_W || ControlsHandler.a == KeyEvent.VK_UP)
            upPressed = true;
        else if (ControlsHandler.a == KeyEvent.VK_S || ControlsHandler.a == KeyEvent.VK_DOWN)
            downPressed = true;
        else if (ControlsHandler.a == KeyEvent.VK_A || ControlsHandler.a == KeyEvent.VK_LEFT)
            leftPressed = true;
        else if (ControlsHandler.a == KeyEvent.VK_D || ControlsHandler.a == KeyEvent.VK_RIGHT)
            rightPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (!gp.gameRunning) return;

        b = event.getKeyCode();

        if (ControlsHandler.b == KeyEvent.VK_W || ControlsHandler.b == KeyEvent.VK_UP)
            upPressed = false;
        else if (ControlsHandler.b == KeyEvent.VK_S || ControlsHandler.b == KeyEvent.VK_DOWN)
            downPressed = false;
        else if (ControlsHandler.b == KeyEvent.VK_A || ControlsHandler.b == KeyEvent.VK_LEFT)
            leftPressed = false;
        else if (ControlsHandler.b == KeyEvent.VK_D || ControlsHandler.b == KeyEvent.VK_RIGHT)
            rightPressed = false;
    }
}
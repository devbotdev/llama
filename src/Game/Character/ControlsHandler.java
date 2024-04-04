package Game.Character;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Panels.pause.Pause.pause;

public class ControlsHandler implements KeyListener {

    public static int a, b;
    public static boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent event) {
    }

    @Override
    public void keyPressed(KeyEvent event) {
        a = event.getKeyCode();

        if (a == KeyEvent.VK_ESCAPE) {
            pause(true);
        }

        if (ControlsHandler.a == KeyEvent.VK_W || ControlsHandler.a == KeyEvent.VK_UP)
            ControlsHandler.upPressed = true;
        else if (ControlsHandler.a == KeyEvent.VK_S || ControlsHandler.a == KeyEvent.VK_DOWN)
            ControlsHandler.downPressed = true;
        else if (ControlsHandler.a == KeyEvent.VK_A || ControlsHandler.a == KeyEvent.VK_LEFT)
            ControlsHandler.leftPressed = true;
        else if (ControlsHandler.a == KeyEvent.VK_D || ControlsHandler.a == KeyEvent.VK_RIGHT)
            ControlsHandler.rightPressed = true;

    }

    @Override
    public void keyReleased(KeyEvent event) {
        b = event.getKeyCode();

        if (ControlsHandler.b == KeyEvent.VK_W || ControlsHandler.b == KeyEvent.VK_UP)
            ControlsHandler.upPressed = false;
        else if (ControlsHandler.b == KeyEvent.VK_S || ControlsHandler.b == KeyEvent.VK_DOWN)
            ControlsHandler.downPressed = false;
        else if (ControlsHandler.b == KeyEvent.VK_A || ControlsHandler.b == KeyEvent.VK_LEFT)
            ControlsHandler.leftPressed = false;
        else if (ControlsHandler.b == KeyEvent.VK_D || ControlsHandler.b == KeyEvent.VK_RIGHT)
            ControlsHandler.rightPressed = false;
    }
}

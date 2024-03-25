package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Panels.Pause.Pause.pause;
import static Va.Vars.gameStarted;
import static Va.Vars.toggleMenu;

public class GamePanel implements KeyListener {

    private int e;
    public static boolean pausePressed;
    private JFrame frame;

    public void startGame() {
        frame = new JFrame();

        pausePressed = false;

        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
        toggleMenu(false);

        frame.addKeyListener(this);

        gameStarted = true;
    }

    private void endGame() {
        toggleMenu(true);

        gameStarted = false;
    }

    @Override
    public void keyTyped(KeyEvent event) {
    }

    @Override
    public void keyPressed(KeyEvent event) {
        e = event.getKeyCode();
        if (e == KeyEvent.VK_ESCAPE) {
            pause(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        e = event.getKeyCode();

    }

    public JFrame getFrame() {
        return frame;
    }
}

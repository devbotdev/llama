package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import static Panels.pause.Pause.pause;
import static Variables.Vars.*;

public class GamePanel extends JPanel implements KeyListener {
    private Run run;
    private int e;
    public static boolean pausePressed;
    private JFrame frame;
    protected static Thread gameThread;
    protected Graphics2D g;
    protected final short tileSize = (short) (60 * getScreenScale());
    protected final byte maxScreenRow = 18, maxScreenCol = 32;

    public GamePanel() {
        super();
        setOpaque(true);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }

    public void startGame() {
        run = new Run();

        optionsPressed = false;
        frame = new JFrame();
        gameThread.start();

        pausePressed = false;

        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(getScreenWidthS(), getScreenHeightS());
        frame.setSize(getScreenWidthS(), getScreenHeightS());
        frame.setLocationRelativeTo(null);

        frame.getContentPane().setBackground(Color.BLACK);
        frame.setBackground(Color.BLACK);
        this.setBackground(Color.BLACK);

        frame.add(this);

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


    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        g = (Graphics2D) graphics;

        g.setColor(Color.WHITE);

        getPortraitI(tileSize).paintIcon(this, g, 20, 20);

        g.fillRect(100, 100, tileSize, tileSize);

        g.dispose();
    }
}

package Game;

import Game.Character.ControlsHandler;

import javax.swing.*;
import java.awt.*;

import static Game.Character.MainCharacter.*;
import static Variables.Vars.*;

public class GamePanel extends JPanel {
    private Run run;
    private int e;
    public static boolean pausePressed;
    private JFrame frame;
    protected static Thread gameThread;
    protected Graphics2D g;
    protected final short tileSize = (short) (60 * getScreenScale());
    protected final byte maxScreenRow = 18, maxScreenCol = 32;
    public final ControlsHandler controlsHandler = new ControlsHandler();

    public GamePanel() {
        super();
        addKeyListener(controlsHandler);
        setOpaque(true);
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
        setSize(getScreenWidthScaled(), getScreenHeightScaled());
        frame.setSize(getScreenWidthScaled(), getScreenHeightScaled());
        frame.setLocationRelativeTo(null);

        frame.getContentPane().setBackground(Color.BLACK);
        frame.setBackground(Color.BLACK);
        this.setBackground(Color.BLACK);

        frame.add(this);

        frame.setVisible(true);
        toggleMenu(false);

        frame.addKeyListener(controlsHandler);

        gameStarted = true;
    }

    private void endGame() {
        toggleMenu(true);

        gameStarted = false;
    }

    public JFrame getFrame() {
        return frame;
    }
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        g = (Graphics2D) graphics;

        getPortrait(tileSize).paintIcon(this, g, playerX, playerY);

        g.dispose();
    }
}

package Game;

import Game.Characters.Orjeli;
import Game.Tile.Tile;
import Game.Tile.TileManager;

import javax.swing.*;
import java.awt.*;

import static Variables.Vars.*;

public class GamePanel extends JPanel {
    public final int maxCol = 32, maxRow = 18;
    protected Orjeli orjeli;
    protected TileManager tileManager;
    private Run run;
    private int e;
    public boolean pausePressed;
    private JFrame frame;
    protected static Thread gameThread;
    private Graphics2D g;
    public final short tileSize = (short) (60 * getScreenScale());
    private final ControlsHandler controlsHandler = new ControlsHandler();

    public GamePanel() {
        super();
        addKeyListener(controlsHandler);
        setOpaque(true);
        setFocusable(true);
        requestFocusInWindow();

        orjeli = new Orjeli(this, controlsHandler);
        tileManager = new TileManager(this);
    }

    public void startGame() {
        gameRunning = true;
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

        tileManager.draw(g);

        orjeli.draw(g);

        g.dispose();
    }
}

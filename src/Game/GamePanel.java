package Game;

import Game.Characters.Orjeli;
import Game.Food.FoodObject;
import Game.Paint.AssetSetter;
import Game.Paint.TileManager;
import Panels.options.Options;
import Panels.pause.Pause;

import javax.swing.*;
import java.awt.*;

import static Variables.Vars.*;

public class GamePanel extends JPanel {

    public final int maxCol = 32, maxRow = 18;
    public Orjeli orjeli;
    public TileManager tileManager;
    private Run run;
    private int e;
    public boolean pausePressed;
    private JFrame frame;
    private Graphics2D g;
    public final short tileSize = (short) (60 * getScreenScale());
    public final Pause pause = new Pause(this);
    public FoodObject[] foodObject = new FoodObject[10];
    public AssetSetter setter = new AssetSetter(this);
    public final ControlsHandler handler;

    public GamePanel() {
        orjeli = new Orjeli(this);
        handler = new ControlsHandler(this);
        tileManager = new TileManager(this);
        addKeyListener(handler);
        setOpaque(true);
        setFocusable(true);
        requestFocusInWindow();
    }

    public void setupGame() {
        setter.set();
    }

    public void startGame(Options o) {
        o.panel.setGamePanel(this);

        gameRunning = true;
        run = new Run(this);

        setupGame();

        optionsPressed = false;
        frame = new JFrame();

        pausePressed = false;

        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(getScreenWidthScaled(), getScreenHeightScaled());
        frame.setSize(getScreenWidthScaled(), getScreenHeightScaled());
        frame.setLocationRelativeTo(null);

        frame.add(this);

        frame.setVisible(true);
        toggleMenu(false);

        frame.addKeyListener(handler);

        gameStarted = true;
        run.gameThread.start();
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

        // Map
        tileManager.draw(g);

        // Foods

        for (FoodObject object : foodObject) {
            if (object != null) {
                object.draw(g, this);
            }
        }

        orjeli.draw(g);

        g.dispose();
    }
}

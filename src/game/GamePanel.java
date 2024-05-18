package game;

import game.characters.Orjeli;
import game.object.management.Object;
import game.object.management.AssetSetter;
import game.paint.Level;
import game.paint.TileManager;
import panels.options.Options;
import panels.pause.Pause;
import variables.sound.SoundType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static variables.Vars.*;

public class GamePanel extends JPanel {

    public final int maxCol = 32, maxRow = 18;
    public final Orjeli orjeli;
    public final TileManager tileManager;
    private Run run;
    public boolean pausePressed;
    private JFrame frame;
    private Graphics2D g;
    public final Pause pause = new Pause(this);
    public ArrayList<Object> object;
    public AssetSetter setter;
    public final ControlsHandler handler;
    public boolean renderItems = true;
    public boolean gameRunning;
    public final Level level;

    public GamePanel() {
        level = new Level();
        orjeli = new Orjeli(this);
        handler = new ControlsHandler(this);
        tileManager = new TileManager(this);
        addKeyListener(handler);
        setOpaque(true);
        setFocusable(true);
        requestFocusInWindow();
    }

    public void setupGame() {
        setter = new AssetSetter(this);

        orjeli.keysGathered = 0;
    }

    public void startGame(Options o) {
        buttons.sound.play(SoundType.MUSIC);

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
        frame.setSize(screenWidth, screenHeight);
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
        if (renderItems) {
            try {
                for (Object object : object) {
                    if (object != null) {
                        object.draw(g);
                    }
                }
            } catch (Exception ignored) {}
        }

        orjeli.draw(g);

        g.dispose();
    }
}

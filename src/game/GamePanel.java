package game;

import game.characters.Orjeli;
import game.object.management.Object;
import game.object.management.AssetSetter;
import game.paint.Level;
import game.paint.TileManager;
import panels.options.Options;
import panels.pause.Pause;
import variables.Actions;
import variables.UtilityTool;
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
    public final UtilityTool ut;

    public GamePanel() {
        ut = new UtilityTool();
        level = new Level();
        orjeli = new Orjeli(this);
        handler = new ControlsHandler(this);
        tileManager = new TileManager(this);
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

        map();

        pausePressed = false;

        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(screenWidth, screenHeight);
        frame.setLocationRelativeTo(null);

        frame.add(this);

        frame.setVisible(true);
        toggleMenu(false);

        gameStarted = true;

        run.gameThread.start();
    }

    private void map() {
        mapActions(false, Actions.ESC_ACTION, false);

        mapD(false);
        mapD(true);
    }

    private void mapD(boolean b) {
        mapActions(b, Actions.UP_ACTION, false);
        mapActions(b, Actions.UP_ACTION, true);

        mapActions(b, Actions.DOWN_ACTION, false);
        mapActions(b, Actions.DOWN_ACTION, true);

        mapActions(b, Actions.LEFT_ACTION, false);
        mapActions(b, Actions.LEFT_ACTION, true);

        mapActions(b, Actions.RIGHT_ACTION, false);
        mapActions(b, Actions.RIGHT_ACTION, true);
    }

    private void mapActions(boolean released, byte b, boolean secondary) {
        getInputMap().put(Actions.getKeyStroke(buttons.actions.getAction(released, b), secondary), Actions.getName(buttons.actions.getAction(released, b)));
        getActionMap().put(Actions.getName(buttons.actions.getAction(released, b)), buttons.actions.getAction(released, b));
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

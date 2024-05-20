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
        getInputMap().put(Actions.getKeyStroke(buttons.actions.getEscAction()), Actions.getName(buttons.actions.getEscAction()));
        getActionMap().put(Actions.getName(buttons.actions.getEscAction()), buttons.actions.getEscAction());

        getInputMap().put(Actions.getKeyStroke(buttons.actions.getUpAction(false)), Actions.getName(buttons.actions.getUpAction(false)));
        getActionMap().put(Actions.getName(buttons.actions.getUpAction(false)), buttons.actions.getUpAction(false));

        getInputMap().put(Actions.getKeyStroke(buttons.actions.getDownAction(false)), Actions.getName(buttons.actions.getDownAction(false)));
        getActionMap().put(Actions.getName(buttons.actions.getDownAction(false)), buttons.actions.getDownAction(false));

        getInputMap().put(Actions.getKeyStroke(buttons.actions.getLeftAction(false)), Actions.getName(buttons.actions.getLeftAction(false)));
        getActionMap().put(Actions.getName(buttons.actions.getLeftAction(false)), buttons.actions.getLeftAction(false));

        getInputMap().put(Actions.getKeyStroke(buttons.actions.getRightAction(false)), Actions.getName(buttons.actions.getRightAction(false)));
        getActionMap().put(Actions.getName(buttons.actions.getRightAction(false)), buttons.actions.getRightAction(false));



        getInputMap().put(Actions.getKeyStroke(buttons.actions.getUpAction(true)), Actions.getName(buttons.actions.getUpAction(true)));
        getActionMap().put(Actions.getName(buttons.actions.getUpAction(true)), buttons.actions.getUpAction(true));

        getInputMap().put(Actions.getKeyStroke(buttons.actions.getDownAction(true)), Actions.getName(buttons.actions.getDownAction(true)));
        getActionMap().put(Actions.getName(buttons.actions.getDownAction(true)), buttons.actions.getDownAction(true));

        getInputMap().put(Actions.getKeyStroke(buttons.actions.getLeftAction(true)), Actions.getName(buttons.actions.getLeftAction(true)));
        getActionMap().put(Actions.getName(buttons.actions.getLeftAction(true)), buttons.actions.getLeftAction(true));

        getInputMap().put(Actions.getKeyStroke(buttons.actions.getRightAction(true)), Actions.getName(buttons.actions.getRightAction(true)));
        getActionMap().put(Actions.getName(buttons.actions.getRightAction(true)), buttons.actions.getRightAction(true));
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

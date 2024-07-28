package game;

import game.characters.Entity;
import game.characters.Orjeli;
import game.object.management.Object;
import game.object.management.AssetSetter;
import game.paint.Level;
import game.paint.TileManager;
import panels.dead.Death;
import panels.pause.Pause;
import variables.util.Actions;
import variables.util.UtilityTool;
import variables.sound.SoundType;

import javax.swing.*;
import java.awt.*;

import static variables.Vars.*;

public class GamePanel extends JPanel {

    public boolean gameOver;

    public Pause pause;
    public final Death deathScreen;

    public final int maxCol = 32, maxRow = 18;

    public final TileManager tileManager;
    private Run run;

    public boolean pausePressed;
    public boolean renderItems = true;
    public boolean gameRunning;

    public JFrame frame;
    private Graphics2D g;

    public Object[] object;
    public AssetSetter setter;

    public final ControlsHandler handler;
    public final Level level;
    public final UtilityTool ut;

    public final Orjeli orjeli;
    public Entity[] npc = new Entity[10];

    public GamePanel() {
        ut = new UtilityTool();
        level = new Level();
        orjeli = new Orjeli(this);
        handler = new ControlsHandler(this);
        tileManager = new TileManager(this);
        setOpaque(true);
        setFocusable(true);
        requestFocusInWindow();

        deathScreen = new Death(this);
    }

    public void setupGame() {
        setter = new AssetSetter(this);
        orjeli.keysGathered = 0;

        setter.setNPC();
    }

    public void endGame() {
        System.out.println("game ended");

        run.gameThread.interrupt();

        gameOver = true;
        gameRunning = false;
        gameStarted = false;
        optionsPressed = false;
        pausePressed = false;

        getInputMap().clear();
        getActionMap().clear();

        run.gameThread = null;

        run = null;
    }

    public void restart() {
        backToMenu(false);
        startGame();
    }

    public void backToMenu(boolean menu) {
        buttons.sound.stop(SoundType.MUSIC);
        buttons.sound.stop(SoundType.SOUND_FX);

        deathScreen.hideDeathScreen();

        buttons.o.optionsMenu(false);

        pause.pause(false, false);

        frame.remove(this);

        if (menu) {
            pause.selfDestruct();
            pause = null;
        }

        frame.dispose();

        frame = null;

        toggleMenu(menu);
    }

    public GamePanel startGame() {
        frame = new JFrame();

        orjeli.setDefaultValues();
        level.setLevel(0);

        buttons.sound.play(SoundType.MUSIC);

        frame.setFocusableWindowState(true);

        gameRunning = true;
        gameOver = false;
        orjeli.down = false;

        run = new Run(this);

        setupGame();

        optionsPressed = false;

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

        classFirstCreated();

        return this;
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

    protected void paintComponent(Graphics graphics) {
        if (gameRunning) {
            super.paintComponent(graphics);

            g = (Graphics2D) graphics;

            // Map
            tileManager.draw(g);

            // NPC
            for (Entity entity : npc) {
                if (entity != null) {
                    entity.draw(g);
                }
            }

            // Foods
            if (renderItems) {
                try {
                    for (Object object : object) {
                        if (object != null) {
                            object.draw(g);
                        }
                    }
                } catch (Exception ignored) {
                }
            }

            orjeli.draw(g);

            g.dispose();
        }
    }
}

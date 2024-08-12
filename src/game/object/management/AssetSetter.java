package game.object.management;

import game.characters.NPC0;
import game.object.*;
import game.GamePanel;
import game.paint.Level;

import java.lang.reflect.InvocationTargetException;

import static variables.Vars.*;

public class AssetSetter {

    public final Key key;
    public final Mosque mosque;
    public final Object food;

    private final GamePanel gp;
    private int i;
    public final Object[] object;
    private boolean load;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;

        setLoad(true);

        this.object = new Object[10];

        key = new Key();
        mosque = new Mosque();
        if (foodType == 0) {
            food = new Hamburger();
        } else if (foodType == 1) {
            food = new Bacon();
        } else if (foodType == 2) {
            food = new Steak();
        } else if (foodType == 3) {
            food = new FrenchFries();
        } else if (foodType == 4) {
            food = new Pizza();
        } else {
            food = null;
        }

        set();

        setArray();
    }

    public void newObjects() {
        setLoad(false);
        gp.renderItems = false;
        new Thread(this::set).start();
    }

    public void set() {
        i = 0;

        mapObjects();

        i = 0;
    }

    public void setNPC() {
        for (i = 0; i < gp.npc.length; i++) gp.npc[i] = null;

        gp.npc[0] = new NPC0(gp);
    }

    public void mapObjects() {
        if (Level.LEVEL() == 0) {
            setObject(Mosque.class, 10, 7);

            setObject(10, 10);

            setObject(11, 10);

            setObject(12, 10);

            setObject(13, 10);

            setObject(14, 10);

            setObject(15, 10);

            setObject(16, 11);

            setObject(Key.class, 5, 12);

        } else if (Level.LEVEL() == 1) {
            setObject(Mosque.class, 10, 7);

            setObject(10, 10);

            setObject(11, 10);

            setObject(12, 10);

            setObject(13, 10);

            setObject(14, 10);

            setObject(15, 10);

            setObject(16, 11);

            setObject(Key.class, 5, 12);

        } else if (Level.LEVEL() == 2) {
            setObject(Mosque.class, 10, 7);

            setObject(10, 10);

            setObject(11, 10);

            setObject(12, 10);

            setObject(13, 10);

            setObject(14, 10);

            setObject(15, 10);

            setObject(16, 11);

            setObject(Key.class, 5, 12);
        }
    }

    private void setObject(Class<?> cl, int objectX, int objectY) {
        try {
            this.object[i] = (Object) cl.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException ignored) {
        }
        object[i].objectX = objectX * tileSizeX;
        this.object[i].objectY = objectY * tileSizeY;

        i++;
    }

    public void setArray() {
        setLoad(true);
        gp.object = this.object;
        gp.renderItems = true;
    }

    private void setObject(int objectX, int objectY) {
        if (foodType == 0) {
            this.object[i] = new Hamburger();
        } else if (foodType == 1) {
            this.object[i] = new Bacon();
        } else if (foodType == 2) {
            this.object[i] = new Steak();
        } else if (foodType == 3) {
            this.object[i] = new FrenchFries();
        } else if (foodType == 4) {
            this.object[i] = new Pizza();
        }

        this.object[i].objectX = objectX * tileSizeX;
        this.object[i].objectY = objectY * tileSizeY;
        i++;
    }

    public void setLoad(boolean load) {
        this.load = load;
    }

    public boolean isLoad() {
        return load;
    }
}
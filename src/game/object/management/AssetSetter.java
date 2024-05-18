package game.object.management;

import game.object.*;
import game.GamePanel;
import game.paint.Level;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static variables.Vars.*;

public class AssetSetter {

    private final GamePanel gp;
    private int i;
    public final ArrayList<Object> object;
    private boolean load;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;

        setLoad(true);

        gp.object = new ArrayList<>();
        this.object = new ArrayList<>();

        i = 0;
        set();

        setArray();
    }

    public void newObjects() {
        setLoad(false);
        gp.renderItems = false;
        new Thread(this::set).start();
    }

    public void set() {
        this.object.clear();
        gp.object.clear();

        mapObjects();

        i = 0;
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
            this.object.add(i, (Object) cl.getDeclaredConstructor().newInstance());
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException ignored) {}
        this.object.get(i).objectX = objectX * tileSizeX;
        this.object.get(i).objectY = objectY * tileSizeY;
        i++;
    }

    public void setArray() {
        setLoad(true);
        gp.object = this.object;
        gp.renderItems = true;
    }

    private void setObject(int objectX, int objectY) {
        if (foodType == 0) {
            this.object.add(i, new Hamburger());
        } else if (foodType == 1) {
            this.object.add(i, new Bacon());
        } else if (foodType == 2) {
            this.object.add(i, new Steak());
        } else if (foodType == 3) {
            this.object.add(i, new FrenchFries());
        } else if (foodType == 4) {
            this.object.add(i, new Pizza());
        }
        this.object.get(i).objectX = objectX * tileSizeX;
        this.object.get(i).objectY = objectY * tileSizeY;
        i++;
    }

    public void setLoad(boolean load) {
        this.load = load;
    }

    public boolean isLoad() {
        return load;
    }
}
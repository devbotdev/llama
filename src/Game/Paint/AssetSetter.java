package Game.Paint;

import Game.Object.*;
import Game.Object.Management.Object;
import Game.GamePanel;

import java.util.ArrayList;

import static Variables.Vars.foodType;
import static Variables.Vars.tileSizeX;

public class AssetSetter {

    private final GamePanel gp;
    private int i;
    public ArrayList<Object> object;
    private boolean load;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;

        setLoad(true);

        gp.object = new java.util.ArrayList<>();
        this.object = new java.util.ArrayList<>();

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

        i = 0;

        setObject(10, 10);

        setObject(11, 10);

        setObject(12, 10);

        setObject(13, 10);

        setObject(14, 10);

        setObject(15, 10);

        setObject(16, 11);

        setKeys(5, 12);
    }

    private void setObject(Object o, int objectX, int objectY) {
        gp.object.add(i, o);
        gp.object.get(i).objectX = objectX * tileSizeX;
        gp.object.get(i).objectY = objectY * tileSizeX;
        i++;
    }

    public void setArray() {
        setLoad(true);
        gp.object = this.object;
        gp.renderItems = true;
    }

    private void setKeys(int objectX, int objectY) {
        this.object.add(i, new Key());
        this.object.get(i).objectX = objectX * tileSizeX;
        this.object.get(i).objectY = objectY * tileSizeX;
        i++;
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
        this.object.get(i).objectY = objectY * tileSizeX;
        i++;
    }

    private void setObject(int i, Object o, int objectX, int objectY) {
        gp.object.add(i, o);
        gp.object.get(i).objectX = objectX * tileSizeX;
        gp.object.get(i).objectY = objectY * tileSizeX;
    }

    public void setLoad(boolean load) {
        this.load = load;
    }

    public boolean isLoad() {
        return load;
    }
}
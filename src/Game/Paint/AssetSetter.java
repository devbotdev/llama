package Game.Paint;

import Game.Object.*;
import Game.Object.Management.Object;
import Game.GamePanel;

import static Variables.Vars.tileSize;

public class AssetSetter {

    private final GamePanel gp;

    private int i;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
        i = 0;
    }

    public void set() {
        setObject(new Hamburger(), 10, 10);

        setObject(new Bacon(), 11, 10);

        setObject(new Steak(), 12, 10);

        setObject(new FrenchFries(), 13, 10);

        setObject(new Pizza(), 14, 10);

        setObject(new Dhall(), 15, 10);

        setObject(new Key(), 16, 11);
    }

    private void setObject(int i, Object o, int objectX, int objectY) {
        gp.object[i] = o;
        gp.object[i].objectX = objectX * tileSize;
        gp.object[i].objectY = objectY * tileSize;
    }

    private void setObject(Object o, int objectX, int objectY) {
        gp.object[i] = o;
        gp.object[i].objectX = objectX * tileSize;
        gp.object[i].objectY = objectY * tileSize;
        i++;
    }
}
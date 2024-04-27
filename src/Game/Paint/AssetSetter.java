package Game.Paint;

import Game.Food.*;
import Game.GamePanel;

import static Variables.Vars.tileSize;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void set() {
        setFood(0, new Hamburger(), 10, 10);

        setFood(1, new Bacon(), 11, 10);

        setFood(2, new Steak(), 12, 10);

        setFood(3, new FrenchFries(), 13, 10);

        setFood(4, new Pizza(), 14, 10);

        setFood(5, new Dhall(), 15, 10);
    }

    private void setFood(int i, FoodObject o, int objectX, int objectY) {
        gp.foodObject[i] = o;
        gp.foodObject[i].objectX = objectX * tileSize;
        gp.foodObject[i].objectY = objectY * tileSize;
    }
}
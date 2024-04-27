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
        gp.foodObject[0] = new Hamburger();
        gp.foodObject[0].objectX = 10 * tileSize;
        gp.foodObject[0].objectY = 10 * tileSize;

        gp.foodObject[1] = new Bacon();
        gp.foodObject[1].objectX = 11 * tileSize;
        gp.foodObject[1].objectY = 10 * tileSize;

        gp.foodObject[2] = new Steak();
        gp.foodObject[2].objectX = 12 * tileSize;
        gp.foodObject[2].objectY = 10 * tileSize;

        gp.foodObject[3] = new FrenchFries();
        gp.foodObject[3].objectX = 13 * tileSize;
        gp.foodObject[3].objectY = 10 * tileSize;

        gp.foodObject[4] = new Pizza();
        gp.foodObject[4].objectX = 14 * tileSize;
        gp.foodObject[4].objectY = 10 * tileSize;

        gp.foodObject[5] = new Dhall();
        gp.foodObject[5].objectX = 15 * tileSize;
        gp.foodObject[5].objectY = 10 * tileSize;
    }
}
package Game.Object.Management;

import Game.Characters.Entity;
import Game.GamePanel;
import Game.Object.Key;

public class ObjectCollision {

    private final GamePanel gp;

    public ObjectCollision(GamePanel gp) {
        this.gp = gp;
    }

    public void checkObject(Entity e, boolean player) {
//        index = 999;
        if (!player) return;

        for (int i = 0; i < gp.object.length; i++) {
            if (gp.object[i] == null) continue;
            e.solidArea.x += e.entityX;
            e.solidArea.y += e.entityY;
            gp.object[i].solidArea.x += gp.object[i].objectX;
            gp.object[i].solidArea.y += gp.object[i].objectY;
            e.solidArea.y -= (int) e.entitySpeed;

            if (e.solidArea.intersects(gp.object[i].solidArea)) {
                pickUpObject(i);
            }
            e.solidArea.x = e.solidAreaDefaultX;
            e.solidArea.y = e.solidAreaDefaultY;
            if (gp.object[i].image == null) continue;
            gp.object[i].solidArea.x = gp.object[i].solidADX;
            gp.object[i].solidArea.y = gp.object[i].solidADY;
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            if (gp.object[i].getClass() == Key.class) {
                gp.orjeli.keysGathered++;
                System.out.println(gp.orjeli.keysGathered);
            }

            gp.object[i].image = null;
            if (gp.object[i].isFood) gp.orjeli.fatnessLevel += 0.015F;
        }
    }
}

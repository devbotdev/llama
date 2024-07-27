package game.object.management;

import game.characters.Entity;
import game.GamePanel;
import game.object.Key;
import game.object.Mosque;

public class CollisionManager {

    private final GamePanel gp;

    public CollisionManager(GamePanel gp) {
        this.gp = gp;
    }

    public void checkObject(Entity e, boolean player) {
        if (!player) return;

        for (int i = 0; i < gp.object.length; i++) {
            if (gp.object[i] == null) continue;

            //pickUpObject also has this check
            if (gp.object[i].image == null) continue;
            e.solidArea.x += gp.orjeli.entityX;
            e.solidArea.y += gp.orjeli.entityY;
            gp.object[i].solidArea.x += gp.object[i].objectX;
            gp.object[i].solidArea.y += gp.object[i].objectY;
            e.solidArea.y -= (int) e.entitySpeed;

            if (e.solidArea.intersects(gp.object[i].solidArea)) {
                pickUpObject(i);
            }

            e.solidArea.x = e.solidAreaDefaultX;
            e.solidArea.y = e.solidAreaDefaultY;
            gp.object[i].solidArea.x = gp.object[i].solidADX;
            gp.object[i].solidArea.y = gp.object[i].solidADY;
        }
    }

    public void pickUpObject(int i) {
        if (gp.object[i].image != null) {
            if (i != 999) {
                if (gp.object[i].getClass() == Key.class) {
                    gp.orjeli.keysGathered++;
                    System.out.println(gp.orjeli.keysGathered);
                }

                gp.object[i].image = null;
                if (gp.object[i].isFood) gp.orjeli.fatnessLevel += 0.015F;
                if (gp.object[i] instanceof Mosque) {
                    gp.orjeli.fatnessLevel += 0.3F;
                }
            }
        }
    }

    private int index;
    public int checkEntity(Entity e, Entity[] target) {
        index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] == null) continue;

            e.solidArea.x += e.entityX;
            e.solidArea.y += e.entityY;
            target[i].solidArea.x += target[i].entityX;
            target[i].solidArea.y += target[i].entityY;


            if (e.solidArea.intersects(target[i].solidArea)) {
                index = i;
            }

            e.solidArea.x = e.solidAreaDefaultX;
            e.solidArea.y = e.solidAreaDefaultY;
            target[i].solidArea.x = target[i].solidAreaDefaultX;
            target[i].solidArea.y = target[i].solidAreaDefaultY;
        }
        return index;
    }
}

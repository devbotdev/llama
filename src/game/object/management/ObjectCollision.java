package game.object.management;

import game.characters.Entity;
import game.GamePanel;
import game.object.Key;
import game.object.Mosque;

public class ObjectCollision {

    private final GamePanel gp;

    public ObjectCollision(GamePanel gp) {
        this.gp = gp;
    }

    public void checkObject(Entity e, boolean player) {
        if (!player) return;

        for (int i = 0; i < gp.object.size(); i++) {
            if (gp.object.get(i) == null) continue;

            //pickUpObject also has this check
            if (gp.object.get(i).image == null) continue;
            e.solidArea.x += Entity.entityX;
            e.solidArea.y += Entity.entityY;
            gp.object.get(i).solidArea.x += gp.object.get(i).objectX;
            gp.object.get(i).solidArea.y += gp.object.get(i).objectY;
            e.solidArea.y -= (int) e.entitySpeed;

            if (e.solidArea.intersects(gp.object.get(i).solidArea)) {
                pickUpObject(i);
            }
            e.solidArea.x = e.solidAreaDefaultX;
            e.solidArea.y = e.solidAreaDefaultY;
            gp.object.get(i).solidArea.x = gp.object.get(i).solidADX;
            gp.object.get(i).solidArea.y = gp.object.get(i).solidADY;
        }
    }

    public void pickUpObject(int i) {
        if (gp.object.get(i).image != null) {
            if (i != 999) {
                if (gp.object.get(i).getClass() == Key.class) {
                    gp.orjeli.keysGathered++;
                    System.out.println(gp.orjeli.keysGathered);
                }

                gp.object.get(i).image = null;
                if (gp.object.get(i).isFood) gp.orjeli.fatnessLevel += 0.015F;
                if (gp.object.get(i) instanceof Mosque) {
                    gp.orjeli.fatnessLevel += 0.3F;
                }
            }
        }
    }
}

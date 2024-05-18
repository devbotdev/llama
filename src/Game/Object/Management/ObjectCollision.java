package Game.Object.Management;

import Game.Characters.Entity;
import Game.GamePanel;
import Game.Object.Key;
import Game.Object.Mosque;

public class ObjectCollision {

    private final GamePanel gp;

    public ObjectCollision(GamePanel gp) {
        this.gp = gp;
    }

    public void checkObject(Entity e, boolean player) {
        if (!player) return;

        for (int i = 0; i < gp.object.size(); i++) {
            if (gp.object.get(i) == null) continue;
            e.solidArea.x += e.entityX;
            e.solidArea.y += e.entityY;
            gp.object.get(i).solidArea.x += gp.object.get(i).objectX;
            gp.object.get(i).solidArea.y += gp.object.get(i).objectY;
            e.solidArea.y -= (int) e.entitySpeed;

            if (e.solidArea.intersects(gp.object.get(i).solidArea)) {
                pickUpObject(i);
            }
            e.solidArea.x = e.solidAreaDefaultX;
            e.solidArea.y = e.solidAreaDefaultY;
            if (gp.object.get(i).image == null) continue;
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

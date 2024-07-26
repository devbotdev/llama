package game.characters;

import game.GamePanel;

import java.awt.*;

public class NPC0 extends Entity {

    private final NPCPath npcPath;
    private int point = 0;

    public NPC0(GamePanel gp) {
        super(gp);

        npcPath = new NPCPath();

        setPoints();

        entityX = npcPath.getPointX(0);
        entityY = npcPath.getPointY(0);

        entitySpeed = 4;

        this.solidArea = new Rectangle();
        this.solidArea.x = 40;
        this.solidArea.y = 40;
        solidArea.width = 40;
        solidArea.height = 40;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
    }

    private void setPoints() {
        npcPath.setPoint(1, 1, 0);
        npcPath.setPoint(5, 1, 1);
        npcPath.setPoint(5, 5, 2);
        npcPath.setPoint(1, 5, 3);
    }

    @Override
    public void update() {
        if (point == 4) point = 0;
        if (npcPath.arrivedAtPoint(point, this)) {
            point++;
        }
    }

    public void draw(Graphics2D g) {
        g.drawImage(getNPCImage(0, direction), entityX, entityY, null);
    }
}

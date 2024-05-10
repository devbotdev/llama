package Game.Characters;

import Game.GamePanel;
import Game.Run;

import javax.swing.*;
import java.awt.*;

import static Variables.Vars.*;

public class Orjeli extends Entity {

    private final GamePanel gp;
    public short keysGathered;
    public boolean down, up;

    public Orjeli(GamePanel gp) {
        this.gp = gp;
        this.solidArea = new Rectangle();
        this.solidArea.x = 8;
        this.solidArea.y = 16;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;

        this.setDefaultValues();
    }

    public void setDefaultValues() {
        fatnessLevel = 1.0F;
        fatnessLevel = (float) (fatnessLevel * getScreenScale());

        sizeI = (short) (60 * getScreenScale());
        sizeYI = (short) (60 * getHeightScale());
        sizeXI = (short) (60 * getWidthScale());

        entityY = screenHeight / 2 - tileSizeY / 2;

        setInitalPosition();

        updateFatness();

        entitySpeedF = 6 / (Run.FPS / 60);
    }

    public void setInitalPosition() {
        entityX = (int) (92 * getWidthScale());
    }

    public void updateFatness() {
        size = (short) (sizeI * fatnessLevel);

        this.solidArea.width = (int) (size / 1.5);
        this.solidArea.height = (int) (size / 1.5);

        setPortrait();
    }

    public void update() {
        updateFatness();
        entitySpeed = ((entitySpeedF - fatnessLevel) * getScreenScale());

        gp.orjeli.movingAllowed();

        gp.tileManager.om.checkObject(this, true);
    }

    private ImageIcon portrait;

    public void setPortrait() {
        portrait = new ImageIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\images\\file.png")
                .getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
    }

    public void draw(Graphics2D g) {
        portrait.paintIcon(gp, g, entityX, entityY);
    }

    public void movingAllowed() {
        if (gp.handler.upPressed) {
            direction = 0;
            if (gp.tileManager.movementAllowed(this, 0)) {
                entityY -= (int) entitySpeed;
            }
        }
        if (gp.handler.downPressed) {
            direction = 1;
            if (gp.tileManager.movementAllowed(this, 1)) {
                entityY += (int) entitySpeed;
            }
        }
        if (gp.handler.leftPressed) {
            direction = 2;
            if (gp.tileManager.movementAllowed(this, 2)) {
                entityX -= (int) entitySpeed;
            }
        }
        if (gp.handler.rightPressed) {
            direction = 3;
            if (gp.tileManager.movementAllowed(this, 3)) {
                entityX += (int) entitySpeed;
            }
        }
    }

    public void a() {
        gp.map++;

        gp.tileManager.loadMap("map" + gp.map + ".txt");
    }

    public void nextLevel() {
        if (down) {
            a();
            setInitalPosition();
            gp.tileManager.reDoor();
            gp.setter.setArray();
        }
        down = false;
    }
}

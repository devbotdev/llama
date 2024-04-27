package Game.Characters;

import Game.ControlsHandler;
import Game.GamePanel;
import Game.Run;

import javax.swing.*;
import java.awt.*;

import static Variables.Vars.*;

public class Orjeli extends Entity {

    private final GamePanel gp;

    public Orjeli(GamePanel gp) {
        this.gp = gp;
        this.setDefaultValues();

        this.solidArea = new Rectangle();
        this.solidArea.x = 8;
        this.solidArea.y = 16;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.solidArea.width = 32;
        this.solidArea.height = 32;
    }

    public void setDefaultValues() {
        fatnessLevel = 1.0F;
        fatnessLevel = (float) (fatnessLevel * getScreenScale());

        sizeI = (short) (60 * getScreenScale());
        sizeYI = (short) (60 * getHeightScale());
        sizeXI = (short) (60 * getWidthScale());

        updateFatness();

        entityY = (int) (100 * getHeightScale());
        entityX = (int) (100 * getWidthScale());
        entitySpeedF = 6 / (Run.FPS / 60);
    }

    public void updateFatness() {
        size = (short) (sizeI * fatnessLevel);

        setPortrait();
    }

    public void update() {
        updateFatness();
        entitySpeed = ((entitySpeedF - fatnessLevel) * getScreenScale());

        gp.orjeli.movingAllowed();

        gp.tileManager.checkObject(this, true);
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
        if (ControlsHandler.upPressed && gp.tileManager.movementAllowed(this, 0)) {
            direction = 0;
            entityY -= (int) entitySpeed;
        }
        if (ControlsHandler.downPressed && gp.tileManager.movementAllowed(this, 1)) {
            direction = 1;
            entityY += (int) entitySpeed;
        }
        if (ControlsHandler.leftPressed && gp.tileManager.movementAllowed(this, 2)) {
            direction = 2;
            entityX -= (int) entitySpeed;
        }
        if (ControlsHandler.rightPressed && gp.tileManager.movementAllowed(this, 3)) {
            direction = 3;
            entityX += (int) entitySpeed;
        }

        if (entityY <= (tileSize - 1)) entityY += 1;
        if (entityY >= (screenHeight - tileSize + 1)) entityY -= 1;
        if (entityX <= (tileSize - 1)) entityX += 1;
        if (entityY >= (screenWidth - tileSize + 1)) entityX -= 1;

        if (gp.tileManager.noMovementAllowed(this, 0)) entityY += 1;
        if (gp.tileManager.noMovementAllowed(this, 1)) entityY -= 1;
        if (gp.tileManager.noMovementAllowed(this, 2)) entityX += 1;
        if (gp.tileManager.noMovementAllowed(this, 3)) entityX -= 1;
    }
}

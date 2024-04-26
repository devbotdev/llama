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
    }

    public void setDefaultValues() {
        fatnessLevel = 1.0F;

        sizeI = (short) (60 * getScreenScale());
        sizeYI = (short) (60 * getHeightScale());
        sizeXI = (short) (60 * getWidthScale());

        updateFatness();

        playerY = (int) (100 * getHeightScale());
        playerX = (int) (100 * getWidthScale());
        playerSpeedF = 6 / (Run.FPS / 60);
    }

    public void updateFatness() {
        size = (short) (sizeI * fatnessLevel);
    }

    public void update() {
        updateFatness();
        playerSpeed = ((playerSpeedF - fatnessLevel) * getScreenScale());

        if (ControlsHandler.upPressed && gp.tileManager.movementAllowed(this, 0)) {
            direction = 0;
            playerY -= (int) playerSpeed;
        }
        if (ControlsHandler.downPressed && gp.tileManager.movementAllowed(this, 1)) {
            direction = 1;
            playerY += (int) playerSpeed;
        }
        if (ControlsHandler.leftPressed && gp.tileManager.movementAllowed(this, 2)) {
            direction = 2;
            playerX -= (int) playerSpeed;
        }
        if (ControlsHandler.rightPressed && gp.tileManager.movementAllowed(this, 3)) {
            direction = 3;
            playerX += (int) playerSpeed;
        }

        gp.orjeli.movingAllowed();
    }

    private final ImageIcon portrait = new ImageIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\images\\file.png").getImage().getScaledInstance((int) (60), (int) (60), Image.SCALE_SMOOTH));
    public void draw(Graphics2D g) {
        portrait.paintIcon(gp, g, playerX, playerY);
    }

    public void movingAllowed() {
        if (playerY <= (gp.tileSize - 1)) playerY += 1;
        if (playerY >= (screenHeight - gp.tileSize + 1)) playerY -= 1;
        if (playerX <= (gp.tileSize - 1)) playerX += 1;
        if (playerY >= (screenWidth - gp.tileSize + 1)) playerX -= 1;

        if (gp.tileManager.noMovementAllowed(this, 0)) playerY += 1;
        if (gp.tileManager.noMovementAllowed(this, 1)) playerY -= 1;
        if (gp.tileManager.noMovementAllowed(this, 2)) playerX += 1;
        if (gp.tileManager.noMovementAllowed(this, 3)) playerX -= 1;
    }
}

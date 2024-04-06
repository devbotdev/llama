package Game.Characters;

import Game.ControlsHandler;
import Game.GamePanel;

import java.awt.*;

import static Variables.Vars.*;

public class Orjeli extends Entity {
    private final GamePanel gp;
    private final ControlsHandler handler;

    public Orjeli(GamePanel gp, ControlsHandler handler) {
        this.gp = gp;
        this.handler = handler;
        this.setDefaultValues();
    }

    public void setDefaultValues() {
        fatnessLevel = 1.0F;
        playerY = (int) (100 * getScreenScale());
        playerX = (int) (100 * getScreenScale());
        playerSpeedF = 6;
    }

    public void update() {
        playerSpeed = (int) ((playerSpeedF - fatnessLevel) * getScreenScale());
        if (ControlsHandler.upPressed && !(playerY <= 48)) {
            direction = 0;
            playerY -= playerSpeed;
        }
        if (ControlsHandler.downPressed && !(playerY >= screenHeight - 48 - gp.tileSize)) {
            direction = 1;
            playerY += playerSpeed;
        }
        if (ControlsHandler.leftPressed && !(playerX <= 48)) {
            direction = 2;
            playerX -= playerSpeed;
        }
        if (ControlsHandler.rightPressed && !(playerX >= screenWidth - 48 - gp.tileSize)) {
            direction = 3;
            playerX += playerSpeed;
        }
    }

    public void draw(Graphics2D g) {
        getPortrait(gp.tileSize).paintIcon(gp, g, playerX, playerY);
    }
}

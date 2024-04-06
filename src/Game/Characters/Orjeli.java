package Game.Characters;

import Game.ControlsHandler;
import Game.GamePanel;

import java.awt.*;

import static Variables.Vars.*;

public class Orjeli {
    public float fatnessLevel;
    public static int playerY, playerX, playerSpeedF, playerSpeed;
    private final GamePanel gp;
    private final ControlsHandler handler;

    public Orjeli(GamePanel gp, ControlsHandler handler) {
        this.gp = gp;
        this.handler = handler;
        this.setDefaultValues();
    }

    private void setDefaultValues() {
        fatnessLevel = 1.0F;
        playerY = 100;
        playerX = 100;
        playerSpeedF = 6;
    }

    public void update() {
        playerSpeed = (int) (playerSpeedF - fatnessLevel);

        if (ControlsHandler.upPressed && !(playerY == 0)) playerY -= playerSpeed;
        if (ControlsHandler.downPressed && !(playerY == screenHeight)) playerY += playerSpeed;
        if (ControlsHandler.leftPressed && !(playerX == 0)) playerX -= playerSpeed;
        if (ControlsHandler.rightPressed && !(playerX == screenWidth)) playerX += playerSpeed;
    }

    public void draw(Graphics2D g) {
        getPortrait(gp.tileSize).paintIcon(gp, g, playerX, playerY);
    }
}

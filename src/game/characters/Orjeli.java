package game.characters;

import game.GamePanel;
import game.paint.Level;
import game.Run;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static variables.Vars.*;

public class Orjeli extends Entity {

    private final GamePanel gp;
    public short keysGathered;
    public boolean down;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    private final int fatnessCooldown = 60;
    public short timePassedForCooldown;

    public Orjeli(GamePanel gp) {
        this.gp = gp;
        this.solidArea = new Rectangle();
        this.solidArea.x = 8;
        this.solidArea.y = 16;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;

        portraitFile = new File(directory + "\\game_resources\\file.png");

        this.setDefaultValues();
    }

    private void setDefaultValues() {
        timePassedForCooldown = 0;

        fatnessLevel = 1.0F;
        fatnessLevel = (float) (fatnessLevel * getScreenScale());

        sizeI = (short) (60 * getScreenScale());
        sizeYI = (short) (60 * getHeightScale());
        sizeXI = (short) (60 * getWidthScale());

        entityY = screenHeight / 2 - tileSizeY / 2;

        setInitalPosition();

        updateFatness();

        entitySpeedF = 6;
    }

    public void setInitalPosition() {
        entityX = (int) (92 * getWidthScale());
    }

    public void updateFatness() {
        if (fatnessLevel == 0) {
        }

        if (timePassedForCooldown >= fatnessCooldown) {
            timePassedForCooldown = 0;
            fatnessLevel -= 0.015F;
        }

        size = (short) (sizeI * fatnessLevel);

        this.solidArea.width = (int) (size / 1.5);
        this.solidArea.height = (int) (size / 1.5);
    }

    public void update() {
        updateFatness();
        entitySpeed = ((entitySpeedF - fatnessLevel) * getScreenScale());

        gp.orjeli.movingAllowed();

        gp.tileManager.om.checkObject(this, true);
    }

    private BufferedImage portrait;
    private final File portraitFile;


    private BufferedImage getPortrait() {
        try {
            portrait = null;
            portrait = gp.ut.scaleImage(ImageIO.read(portraitFile), size, size);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return portrait;
    }

    public void draw(Graphics2D g) {
        g.drawImage(getPortrait(), entityX, entityY, null);
    }

    public void movingAllowed() {
        if (upPressed) {
            direction = 0;
            if (gp.tileManager.movementAllowed(this)) {
                entityY -= (int) entitySpeed;
            }
        }
        if (downPressed) {
            direction = 1;
            if (gp.tileManager.movementAllowed(this)) {
                entityY += (int) entitySpeed;
            }
        }
        if (leftPressed) {
            direction = 2;
            if (gp.tileManager.movementAllowed(this)) {
                entityX -= (int) entitySpeed;
            }
        }
        if (rightPressed) {
            direction = 3;
            if (gp.tileManager.movementAllowed(this)) {
                entityX += (int) entitySpeed;
            }
        }
    }

    private void loadMap() {
        gp.tileManager.loadMap("map" + Level.LEVEL() + ".txt");
    }

    public void nextLevel() {
        if (Level.LEVEL() < numberOfMaps) {
            Level.addLevel();
            loadMap();
            setInitalPosition();
            gp.tileManager.reDoor();
            gp.setter.setArray();
        }
        down = false;
    }
}

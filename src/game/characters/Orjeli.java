package game.characters;

import game.GamePanel;
import game.paint.Level;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static variables.Vars.*;

public class Orjeli extends Entity {

    public short keysGathered;
    public boolean down;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    private final int fatnessCooldown = 60;
    public short timePassedForCooldown;
    public BufferedImage healthBar;

    public final static String healthDirectory = directory + "\\game_resources\\health\\";

    public Orjeli(GamePanel gp) {
        super(gp);
        this.gp = gp;
        this.solidArea = new Rectangle();
        this.solidArea.x = 30;
        this.solidArea.y = 30;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;

        portraitFile = new File(directory + "\\game_resources\\file.png");

        this.setDefaultValues();
    }

    public void setDefaultValues() {
        timePassedForCooldown = 0;

        upPressed = false;
        downPressed = false;
        leftPressed = false;
        rightPressed = false;

        updateHealth(100);

        fatnessLevel = 1.0F;
        fatnessLevel = (float) (fatnessLevel * getScreenScale());

        sizeI = (short) (60 * getScreenScale());
        sizeYI = (short) (60 * getHeightScale());
        sizeXI = (short) (58 * getWidthScale());

        entityY = screenHeight / 2 - tileSizeY / 2;

        updateFatness();
        previousSize = size;

        setInitialPosition();

        entitySpeedF = 6;
    }

    public void setInitialPosition() {
        entityX = (int) (92 * getWidthScale());
    }

    public void updateFatness() {
        if (fatnessLevel == 0) {
        }

        if (timePassedForCooldown >= fatnessCooldown) {
            timePassedForCooldown = 0;
            fatnessLevel -= 0.015F;
        }

        previousSize = size;
        size = (short) (sizeI * fatnessLevel);

        if (previousSize != size) {
            updatePortrait(size);
        }

        this.solidArea.width = (int) (size / 1.5);
        this.solidArea.height = (int) (size / 1.5);
    }

    public void update() {
        updateFatness();
        entitySpeed = ((entitySpeedF - fatnessLevel) * getScreenScale());

        gp.orjeli.movingAllowed();

        gp.tileManager.om.checkObject(this, true);

        interactNPC(gp.tileManager.om.checkEntity(this, gp.npc));
    }

    @Override
    public void updateHealth(int i) {
        entityHealth = i;
        setHealth();
    }

    @Override
    @Deprecated
    @Ignore
    public void killEntity() {
    }

    private void interactNPC(int i) {
        if (i != 999) {
            System.out.println(gp.npc[i].direction);
            if (gp.npc[i].direction == DIRECTION_UP) {
                if (gp.npc[i].entityY < entityY) {
                    if (gp.npc[i].entityX > entityX - 45 || gp.npc[i].entityX > entityX - 45) {
                        gp.npc[i].killEntity();
                        return;
                    }
                }
            } else if (gp.npc[i].direction == DIRECTION_DOWN) {
                if (gp.npc[i].entityY > entityY) {
                    if (gp.npc[i].entityX > entityX - 45 || gp.npc[i].entityX > entityX - 45) {
                        gp.npc[i].killEntity();
                        return;
                    }
                }
            } else if (gp.npc[i].direction == DIRECTION_LEFT) {
                if (gp.npc[i].entityX < entityX) {
                    if (gp.npc[i].entityY > entityY - 45 || gp.npc[i].entityY > entityY - 45) {
                        gp.npc[i].killEntity();
                        return;
                    }
                }
            } else if (gp.npc[i].direction == DIRECTION_RIGHT) {
                if (gp.npc[i].entityX > entityX) {
                    if (gp.npc[i].entityY > entityY - 45 || gp.npc[i].entityY > entityY - 45) {
                        gp.npc[i].killEntity();
                        return;
                    }
                }
            }
            gp.deathScreen.showDeathScreen();
        }
    }

    private BufferedImage portrait;
    private final File portraitFile;

    private BufferedImage getPortrait() {
        return portrait;
    }

    private void updatePortrait(int size) {
        try {
            portrait = null;
            portrait = gp.ut.scaleImage(ImageIO.read(portraitFile), size, size);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int roundedHealth;

    private void setHealth() {
        roundedHealth = Math.round((float) entityHealth / 10);

        try {
            healthBar = null;
            healthBar = gp.ut.scaleImage(ImageIO.read(new File(healthDirectory + roundedHealth + ".png")), screenWidth, screenHeight);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g) {
        g.drawImage(healthBar, 0, 0, null);
        g.drawImage(getPortrait(), entityX, entityY, null);
    }

    public void movingAllowed() {
        if (upPressed) {
            direction = DIRECTION_UP;
            if (gp.tileManager.movementAllowed(this)) {
                entityY -= (int) entitySpeed;
            }
        }
        if (downPressed) {
            direction = DIRECTION_DOWN;
            if (gp.tileManager.movementAllowed(this)) {
                entityY += (int) entitySpeed;
            }
        }
        if (leftPressed) {
            direction = DIRECTION_LEFT;
            if (gp.tileManager.movementAllowed(this)) {
                entityX -= (int) entitySpeed;
            }
        }
        if (rightPressed) {
            direction = DIRECTION_RIGHT;
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
            setInitialPosition();
            gp.tileManager.reDoor();
            gp.setter.setArray();
        }
        down = false;
    }
}

package game.characters;

import game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static variables.Vars.*;

public abstract class Entity {

    public static final byte numberOfNPCS = 1;

    public boolean dead;

    protected GamePanel gp;

    public byte direction;
    public static byte DIRECTION_UP = 0;
    public static byte DIRECTION_DOWN = 1;
    public static byte DIRECTION_LEFT = 2;
    public static byte DIRECTION_RIGHT = 3;

    public int entityHealth;

    public float fatnessLevel;
    public int entityX, entityY;
    public double entitySpeed, entitySpeedF;

    public int entitySpeedI;

    public short size;
    protected short previousSize;

    public short sizeXI, sizeYI, sizeI;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);

    public int solidAreaDefaultX;
    public int solidAreaDefaultY;

    private final BufferedImage[] npcImagesLeft = new BufferedImage[numberOfNPCS];
    private final BufferedImage[] npcImagesRight = new BufferedImage[numberOfNPCS];

    private final File[] imageFilesLeft = new File[numberOfNPCS];
    private final File[] imageFilesRight = new File[numberOfNPCS];

    public Entity(GamePanel gp) {
        this.gp = gp;

        loadNPCFiles();

        for (int i = 0; i < numberOfNPCS; i++) {
            setNPCImages(i);
        }
    }

    private void loadNPCFiles() {
        imageFilesLeft[0] = new File(directory + "\\game_resources\\NPC1_LEFT.png");
        imageFilesRight[0] = new File(directory + "\\game_resources\\NPC1_RIGHT.png");
    }

    private void setNPCImages(int i) {
        try {
            npcImagesLeft[i] = gp.ut.scaleImage(ImageIO.read(imageFilesLeft[i]), tileSizeX, tileSizeY);
            npcImagesRight[i] = gp.ut.scaleImage(ImageIO.read(imageFilesRight[i]), tileSizeX, tileSizeY);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected BufferedImage getNPCImage(int i, int a) {
        if (a == Entity.DIRECTION_RIGHT) {
            return npcImagesRight[i];
        } else if (a == Entity.DIRECTION_LEFT) {
            return npcImagesLeft[i];
        } else if (a == Entity.DIRECTION_UP) {
            return npcImagesRight[i];
        } else if (a == Entity.DIRECTION_DOWN) {
            return npcImagesLeft[i];
        } else {
            return null;
        }
    }

    public abstract void draw(Graphics2D g);

    public abstract void update();

    public abstract void updateHealth(int i);

    public abstract void killEntity();
}

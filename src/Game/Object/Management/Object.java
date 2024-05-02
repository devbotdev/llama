package Game.Object.Management;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Variables.Vars.tileSizeX;
import static Variables.Vars.tileSizeY;

public class Object {

    public BufferedImage image;
    public String name;
    public int objectX, objectY;
    public Rectangle solidArea;
    public int solidADX, solidADY;
    public boolean isFood;

    public Object() {
        solidArea = new Rectangle(0, 0, tileSizeX, tileSizeY);
        this.solidADX = this.solidArea.x;
        this.solidADY = this.solidArea.y;

        isFood = true;
    }

    public void draw(Graphics2D g) {
        g.drawImage(image, objectX, objectY, tileSizeX, tileSizeY, null);
    }

    public void setImage() {
    }
}

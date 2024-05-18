package game.object.management;

import java.awt.*;
import java.awt.image.BufferedImage;

import static variables.Vars.tileSizeX;
import static variables.Vars.tileSizeY;

public class Object {

    public BufferedImage image;
    public String name;
    public int objectX, objectY;
    public final Rectangle solidArea;
    public final int solidADX, solidADY;
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

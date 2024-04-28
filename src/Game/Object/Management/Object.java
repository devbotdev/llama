package Game.Object.Management;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Variables.Vars.tileSize;

public class Object {

    public BufferedImage image;
    public String name;
    public int objectX, objectY;
    public Rectangle solidArea;
    public int solidADX, solidADY;
    public boolean isFood;

    public Object() {
        solidArea = new Rectangle(0, 0, tileSize, tileSize);
        this.solidADX = this.solidArea.x;
        this.solidADY = this.solidArea.y;

        isFood = true;
    }

    public void draw(Graphics2D g) {
        g.drawImage(image, objectX, objectY, tileSize, tileSize, null);
    }

    public void setImage() {
    }
}

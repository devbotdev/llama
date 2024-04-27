package Game.Food;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Variables.Vars.tileSize;

public class FoodObject {

    public BufferedImage image;
    public String name;
    public int objectX, objectY;
    public Rectangle solidArea;
    public int solidADX, solidADY;

    public FoodObject() {
        solidArea = new Rectangle(0, 0, tileSize, tileSize);
        this.solidADX = this.solidArea.x;
        this.solidADY = this.solidArea.y;
    }

    public void draw(Graphics2D g) {
        g.drawImage(image, objectX, objectY, tileSize, tileSize, null);
    }
}

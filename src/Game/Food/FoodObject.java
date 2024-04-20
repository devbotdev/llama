package Game.Food;

import Game.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FoodObject {

    public BufferedImage image;
    public String name;
    public int objectX, objectY;

    public void draw(Graphics2D g, GamePanel gp) {
        g.drawImage(image, objectX, objectY, gp.tileSize, gp.tileSize, null);
    }
}

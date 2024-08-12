package variables.util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {

    public static BufferedImage scaleImage(BufferedImage or, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, or.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(or, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }

    public static void drawSubWindow(int x, int y, int width, int height, Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 179));
        g2.fillRoundRect(x, y, width, height, 35, 35);
    }

    public static void drawRoundOutline(int x, int y, int width, int height, Graphics2D g2, Color c) {
        g2.setColor(c);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x, y, width, height, 35, 35);
    }
}

package variables;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {

    public BufferedImage scaleImage(BufferedImage or, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, or.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(or, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }
}

package Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Run extends GamePanel implements Runnable {

    public static boolean timeToUpdateCharacter = false;
    protected Run() {
        gameThread = new Thread(this);
    }
    @Override
    public void run() {
        while (gameThread != null){
            update();
            repaint();

            if (timeToUpdateCharacter) {
                updateCharacter();

            }
        }
    }

    public void update() {

    }

    public void updateCharacter() {
        timeToUpdateCharacter = false;
    }
}

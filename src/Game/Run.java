package Game;

import Game.Character.ControlsHandler;

import java.awt.*;
import java.awt.event.KeyEvent;

import static Game.Character.MainCharacter.*;
import static Variables.Vars.gamePanel;

public class Run extends GamePanel implements Runnable {

    public static boolean timeToUpdateCharacter = false;
    public static final byte FPS = 60;
    double drawInterval = (double) 1000000000 / FPS;
    double nextDrawTime = System.nanoTime() + drawInterval;
    double remainingTime;
    protected Run() {
        gameThread = new Thread(this);
    }
    @Override
    public void run() {
        while (gameThread != null){

            update();

            gamePanel.repaint();

            try {
                remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {



        if (ControlsHandler.upPressed) playerY -= playerSpeed;
        if (ControlsHandler.downPressed) playerY += playerSpeed;
        if (ControlsHandler.leftPressed) playerX -= playerSpeed;
        if (ControlsHandler.rightPressed) playerX += playerSpeed;
    }

    public void updateCharacter() {
        timeToUpdateCharacter = false;
    }
}

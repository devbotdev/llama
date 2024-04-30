package Game;

import static Variables.Vars.*;

public class Run implements Runnable {

    public static final int FPS = 60;
    public double drawInterval;
    public double delta;
    public long lastTime, currentTime, timer;
    public int drawCount;
    private final GamePanel gp;
    protected Thread gameThread;

    protected Run(GamePanel gp) {
        this.gp = gp;

        gameThread = new Thread(this);
    }

    @Override
    public void run() {
        drawInterval = (double) 1000000000 / FPS;
        delta = 0;
        lastTime = System.nanoTime();
        timer = 0L;
        drawCount = 0;
        while (gameThread != null) {
            if (!gameRunning || gp.orjeli.down) continue;
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                gp.repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                if (printFPS) System.out.println(drawCount);
                drawCount = 0;
                timer = 0L;
            }
        }
    }

    public void update() {
        gp.orjeli.update();

        if (gp.orjeli.entityX - gp.orjeli.solidArea.x + gp.orjeli.solidArea.width > 1832 * getWidthScale() &&
                gp.orjeli.entityX - gp.orjeli.solidArea.x + gp.orjeli.solidArea.width < (1832 + gp.orjeli.entitySpeed) * getWidthScale()) {
            if (gp.setter.isLoad()) {
                gp.setter.newObjects();
            }
        }

        if (gp.map >= numberOfMaps && gp.orjeli.entityX - gp.orjeli.solidArea.x + gp.orjeli.solidArea.width >= 1887 * getWidthScale()) {
            gp.orjeli.entityX -= (int) gp.orjeli.entitySpeed;
            return;
        }

        if (gp.orjeli.entityX - gp.orjeli.solidArea.x + gp.orjeli.solidArea.width >= 1888 * getWidthScale()) {
            gp.orjeli.down = true;
            gp.orjeli.nextLevel();
            gp.orjeli.up = false;
        }
    }
}

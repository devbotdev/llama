package Game;

import static Variables.Vars.*;

public class Run extends GamePanel implements Runnable {
    public static final byte FPS = 126;
    public double drawInterval = (double) 1000000000 / FPS;
    public double delta = 0;
    public long lastTime, currentTime, timer;
    public int drawCount = 0;
    private GamePanel gp;
    protected Run(GamePanel gp) {
        this.gp = gp;

        gameThread = new Thread(this);
    }
    @Override
    public void run() {
        lastTime = System.nanoTime();
        while (gameThread != null) {
            if (!gameRunning) continue;
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                gp.repaint();
                delta--;
                drawCount++;

                if (timer >= 1000000000) {
                    if (printFPS) System.out.println(drawCount);
                    drawCount = 0;
                    timer = 0;
                }
            }
        }
    }

    public void update() {
        orjeli.update();
    }
}

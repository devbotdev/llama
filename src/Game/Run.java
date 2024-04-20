package Game;

import static Variables.Vars.*;

public class Run extends GamePanel implements Runnable {
    public static final int FPS = 60;
    public double drawInterval;
    public double delta;
    public long lastTime, currentTime, timer;
    public int drawCount;
    private GamePanel gp;

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
            if (!gameRunning) continue;
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
        orjeli.update();
    }
}

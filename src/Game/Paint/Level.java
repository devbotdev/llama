package Game.Paint;

public class Level {

    public Level() {
        setLevel(0);
    }

    private static int level;

    public void setLevel(int b) {
        level = b;
    }

    public static void addLevel() {
        level++;
    }

    public static int LEVEL() {
        return level;
    }
}

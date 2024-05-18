package Game.Paint;

public class Level {

    private static int level;
    private static boolean doorOpened;

    public Level() {
        setLevel(0);
    }

    public void setLevel(int b) {
        doorOpened = false;
        level = b;
    }

    public static void addLevel() {
        doorOpened = false;
        level++;
    }

    public static int LEVEL() {
        return level;
    }

    public static void openDoors() {
        doorOpened = true;
    }

    public static boolean isDoorClosed() {
        return !doorOpened;
    }
}

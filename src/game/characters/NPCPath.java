package game.characters;

import static variables.Vars.tileSizeX;
import static variables.Vars.tileSizeY;

public class NPCPath {

    private static final class Point {
        public int x = 0;
        public int y = 0;
    }

    public NPCPath() {
        for (int i = 0; i < point.length; i++) {
            point[i] = new Point();
        }
    }

    private Point[] point = new Point[4];

    public void setPoint(int tileX, int tileY, int point) {
        this.point[point].x = tileX*tileSizeX;
        this.point[point].y = tileY*tileSizeY;
    }

    public int getPointX(int point) {
        return this.point[point].x;
    }

    public int getPointY(int point) {
        return this.point[point].y;
    }

    public boolean arrivedAtPoint(int point, Entity e) {
        System.out.println(e.direction);

        if (e.entityX == this.point[point].x) {

            if (e.entityY == this.point[point].y) {
                return true;
            } else {
                if (e.entityY < this.point[point].y) {
                    e.direction = Entity.DIRECTION_DOWN;
                    e.entityY += (int) e.entitySpeed;
                } else {
                    e.direction = Entity.DIRECTION_UP;
                    e.entityY -= (int) e.entitySpeed;
                }
            }

        } else {
            if (e.entityX < this.point[point].x) {
                e.direction = Entity.DIRECTION_RIGHT;
                e.entityX += (int) e.entitySpeed;
            } else {
                e.direction = Entity.DIRECTION_LEFT;
                e.entityX -= (int) e.entitySpeed;
            }
        }
        return false;
    }
}

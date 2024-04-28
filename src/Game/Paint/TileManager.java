package Game.Paint;

import Game.Characters.Entity;
import Game.Characters.Orjeli;
import Game.GamePanel;
import Game.Object.Management.ObjectCollision;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import static Variables.Vars.*;

public class TileManager {
    private int col, row, x, y;
    protected final int[][] mapTile;
    protected int num, tileNum;
    protected String string;
    protected String[] numbers;
    private final GamePanel gp;
    protected Tile[] tile;
    public final ObjectCollision om;
    private File map;
    private BufferedReader br;
    private Tile tile2;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[3];

        mapTile = new int[gp.maxCol][gp.maxRow];

        setTileImage();

        om = new ObjectCollision(this.gp);

        loadMap("map0.txt");
    }

    private int entityLeftWorldX, entityRightWorldX, entityTopWorldY, entityBottomWorldY,
            entityCol, entityRow, entityLeftCol, entityRightCol, entityTopRow, entityBottomRow,
            tileNum0;

    public boolean movementAllowed(Entity e, int b) {
        e.entitySpeedI = (int) e.entitySpeed;

        entityLeftWorldX = (int) (Entity.entityX - e.entitySpeedI + (1 * getScreenScale()));
        entityRightWorldX = (int) (Entity.entityX + e.size + e.entitySpeed - (2 * getScreenScale()));
        entityTopWorldY = Entity.entityY - e.entitySpeedI;
        entityBottomWorldY = (int) (Entity.entityY + e.size + e.entitySpeed - (4 * getScreenScale()));

        entityCol = ((entityLeftWorldX + entityRightWorldX) / 2) / tileSize;
        entityRow = ((entityBottomWorldY + entityTopWorldY) / 2) / tileSize;

        entityLeftCol = entityLeftWorldX / tileSize;
        entityRightCol = entityRightWorldX / tileSize;
        entityTopRow = entityTopWorldY / tileSize;
        entityBottomRow = entityBottomWorldY / tileSize;

        if (b == 0) {
            if (e.entityY <= 10) return false;

            tileNum0 = mapTile[entityCol][entityTopRow];
            removeDoor((Orjeli) e, tileNum0);
            return !tile[tileNum0].collision;
        } else if (b == 1) {
            if (e.entityY >= screenHeight - 10) return false;

            tileNum0 = mapTile[entityCol][entityBottomRow];
            removeDoor((Orjeli) e, tileNum0);
            return !tile[tileNum0].collision;
        } else if (b == 2) {
            if (e.entityX <= 10) return false;

            tileNum0 = mapTile[entityLeftCol][entityRow];
            removeDoor((Orjeli) e, tileNum0);
            return !tile[tileNum0].collision;
        } else if (b == 3) {
            if (e.entityX >= screenWidth - 10) return false;
            if (gp.orjeli.down) return false;

            tileNum0 = mapTile[entityRightCol][entityRow];
            removeDoor((Orjeli) e, tileNum0);
            return !tile[tileNum0].collision;
        }
        return true;
    }

    private void removeDoor(Orjeli e, int tileNum0) {
        if (tile[tileNum0] == tile[2]) {
            if (e.keysGathered > 0) {
                e.keysGathered--;
                tile[2] = tile[0];
            }
        }
    }

    public void reDoor() {
        tile[2] = tile2;
    }

    public boolean noMovementAllowed(Entity e, int b) {
        e.entitySpeedI = (int) e.entitySpeed;

        entityLeftWorldX = Entity.entityX;
        entityRightWorldX = (Entity.entityX + e.size - 2);
        entityTopWorldY = Entity.entityY;
        entityBottomWorldY = (Entity.entityY + e.size - 2);

        entityCol = ((entityLeftWorldX + entityRightWorldX) / 2) / tileSize;
        entityRow = ((entityBottomWorldY + entityTopWorldY) / 2) / tileSize;

        entityLeftCol = entityLeftWorldX / tileSize;
        entityRightCol = entityRightWorldX / tileSize;
        entityTopRow = entityTopWorldY / tileSize;
        entityBottomRow = entityBottomWorldY / tileSize;

        if (b == 0) {
            tileNum0 = mapTile[entityCol][entityTopRow];
            return tile[tileNum0].collision;
        } else if (b == 1) {
            tileNum0 = mapTile[entityCol][entityBottomRow];
            return tile[tileNum0].collision;
        } else if (b == 2) {
            tileNum0 = mapTile[entityLeftCol][entityRow];
            return tile[tileNum0].collision;
        } else if (b == 3) {
            tileNum0 = mapTile[entityRightCol][entityRow];
            return tile[tileNum0].collision;
        }
        return false;
    }

    public void loadMap(String s) {
        killMap();

        map = new File(directory + "\\images\\maps\\" + s);

        try {
            br = new BufferedReader(new FileReader(map));
        } catch (IOException ignored) {
        }

        col = 0;
        row = 0;
        x = 0;
        y = 0;

        while (col < gp.maxCol && row < gp.maxRow) {
            try {
                string = br.readLine();
            } catch (IOException ignored) {
            }

            while (col < gp.maxCol) {
                numbers = string.split(" ");
                num = Integer.parseInt(numbers[col]);

                mapTile[col][row] = num;
                col++;
            }

            if (col == gp.maxCol) {
                col = 0;
                row++;
            }
        }
        try {
            br.close();
        } catch (IOException ignored) {
        }
    }

    private void killMap() {
        map = null;
        br = null;
    }

    public void setTileImage() {
        tile[0] = new Tile();
        tile[1] = new Tile();
        tile[2] = new Tile();

        tile[1].collision = true;
        tile[2].collision = true;

        tile[2].transparentBackground = true;

        try {
            tile[0].image = getTile("tile.png");
            tile[1].image = getTile("tile_brown.jpg");
            tile[2].image = getTile("door.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        tile2 = tile[2];
    }

    private BufferedImage getTile(String s) throws IOException {
        return ImageIO.read(new File(directory + "\\images\\" + s));
    }

    public void draw(Graphics2D g) {
        col = 0;
        row = 0;
        x = 0;
        y = 0;

        while (col < gp.maxCol && row < gp.maxRow) {
            tileNum = mapTile[col][row];

            if (tile[tileNum].transparentBackground) g.drawImage(tile[0].image, x, y, tileSize, tileSize, null);
            g.drawImage(tile[tileNum].image, x, y, tileSize, tileSize, null);
            col++;
            x += tileSize;

            if (col == gp.maxCol) {
                col = 0;
                x = 0;
                row++;
                y += tileSize;
            }
        }
    }
}
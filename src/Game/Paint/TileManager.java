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

    public Rectangle rectangle;
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        rectangle = new Rectangle();
        rectangle.x = 8;
        rectangle.y = 16;
        this.solidAreaDefaultX = this.rectangle.x;
        this.solidAreaDefaultY = this.rectangle.y;

        tile = new Tile[3];

        mapTile = new int[gp.maxCol][gp.maxRow];

        setTileImage();

        om = new ObjectCollision(this.gp);

        loadMap("map0.txt");
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

    private int entityLeftWorldX, entityRightWorldX, entityTopWorldY, entityBottomWorldY,
            entityCol, entityRow, entityLeftCol, entityRightCol, entityTopRow, entityBottomRow,
            tileNum0;

    private byte b;

    public boolean movementAllowed(Entity e) {
        b = e.direction;

        if (b == 0 && e.entityY <= 10 * getHeightScale()) {
            return false;
        }
        if (b == 1 && e.entityY + e.size >= (screenHeight - 4 * getHeightScale())) {
            return false;
        }
        if (b == 2 && e.entityX <= 10 * getWidthScale()) {
            return false;
        }
        if (b == 3 && e.entityX + e.size >= (screenWidth - 4 * getWidthScale())) {
            return false;
        }

        if (gp.orjeli.entityX - (gp.orjeli.solidArea.x) <= 44 || gp.orjeli.entityX - (gp.orjeli.solidArea.x) + gp.orjeli.size >= 1859 * getWidthScale()) {
            if (b == 0 && gp.orjeli.entityY - (gp.orjeli.solidArea.y) <= 465 * getHeightScale()) {
                return false;
            }
            if (b == 1 && gp.orjeli.entityY - (gp.orjeli.solidArea.y) + gp.orjeli.size >= 584 * getHeightScale()) {
                return false;
            }
        }

        e.entitySpeedI = (int) e.entitySpeed;

        entityLeftWorldX = Entity.entityX - (int) (2 * getWidthScale());
        entityRightWorldX = (Entity.entityX + e.size);
        entityTopWorldY = Entity.entityY + (int) (-4 * getHeightScale());
        entityBottomWorldY = (Entity.entityY + e.size) + (int) (1 * getHeightScale());

        entityCol = ((entityLeftWorldX + entityRightWorldX) / 2) / tileSizeX;
        entityRow = ((entityBottomWorldY + entityTopWorldY) / 2) / tileSizeY;

        entityLeftCol = entityLeftWorldX / tileSizeX;
        entityRightCol = entityRightWorldX / tileSizeX;
        entityTopRow = entityTopWorldY / tileSizeY;
        entityBottomRow = entityBottomWorldY / tileSizeY;

        return singular((Orjeli) e, b);
    }

    private boolean singular(Orjeli e, int a) {
        if (a == 0) {
            if (gp.handler.upPressed) {
                tileNum0 = mapTile[entityCol][entityTopRow];

                removeDoor(e, tileNum0);

                return !tile[tileNum0].collision;
            }
        } else if (a == 1) {
            if (gp.handler.downPressed) {
                tileNum0 = mapTile[entityCol][entityBottomRow];

                removeDoor(e, tileNum0);

                return !tile[tileNum0].collision;
            }
        } else if (a == 2) {
            if (gp.handler.leftPressed) {
                tileNum0 = mapTile[entityLeftCol][entityRow];

                removeDoor(e, tileNum0);

                return !tile[tileNum0].collision;
            }
        } else if (a == 3) {
            if (gp.handler.rightPressed) {
                if (gp.orjeli.down) return true;

                tileNum0 = mapTile[entityRightCol][entityRow];

                removeDoor(e, tileNum0);

                return !tile[tileNum0].collision;
            }
        }
        return true;
    }

    public void loadMap(String s) {
        killMap();
        System.out.println(s);

        map = new File(directory + "\\game_resources\\maps\\" + s);

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
        return ImageIO.read(new File(directory + "\\game_resources\\" + s));
    }

    public void draw(Graphics2D g) {
        col = 0;
        row = 0;
        x = 0;
        y = 0;

        while (col < gp.maxCol && row < gp.maxRow) {
            tileNum = mapTile[col][row];

            if (tile[tileNum].transparentBackground) g.drawImage(tile[0].image, x, y, tileSizeX, tileSizeY, null);
            g.drawImage(tile[tileNum].image, x, y, tileSizeX, tileSizeY, null);
            col++;
            x += tileSizeX;

            if (col == gp.maxCol) {
                col = 0;
                x = 0;
                row++;
                y += tileSizeY;
            }
        }
    }
}
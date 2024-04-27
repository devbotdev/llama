package Game.Paint;

import Game.Characters.Entity;
import Game.GamePanel;
import com.sun.source.tree.IfTree;

import javax.imageio.ImageIO;

import java.awt.*;
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
    private int index;

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
            tileNum0 = mapTile[entityCol][entityTopRow];
            return tile[tileNum0] != tile[1];
        } else if (b == 1) {
            tileNum0 = mapTile[entityCol][entityBottomRow];
            return tile[tileNum0] != tile[1];
        } else if (b == 2) {
            tileNum0 = mapTile[entityLeftCol][entityRow];
            return tile[tileNum0] != tile[1];
        } else if (b == 3) {
            tileNum0 = mapTile[entityRightCol][entityRow];
            return tile[tileNum0] != tile[1];
        }
        return true;
    }

    public void checkObject(Entity e, boolean player) {
        index = 999;

        if (!player) return;

        for (int i = 0; i < gp.foodObject.length; i++) {
            if (gp.foodObject[i] == null) continue;
            e.solidArea.x += e.entityX;
            e.solidArea.y += e.entityY;
            gp.foodObject[i].solidArea.x += gp.foodObject[i].objectX;
            gp.foodObject[i].solidArea.y += gp.foodObject[i].objectY;
            e.solidArea.y -= (int) e.entitySpeed;

            if (e.solidArea.intersects(gp.foodObject[i].solidArea)) {
                pickUpObject(i);
            }
            e.solidArea.x = e.solidAreaDefaultX;
            e.solidArea.y = e.solidAreaDefaultY;
            if (gp.foodObject[i].image == null) continue;
            gp.foodObject[i].solidArea.x = gp.foodObject[i].solidADX;
            gp.foodObject[i].solidArea.y = gp.foodObject[i].solidADY;
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            gp.foodObject[i].image = null;
            gp.orjeli.fatnessLevel += 0.015F;
        }
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
            return tile[tileNum0] == tile[1];
        } else if (b == 1) {
            tileNum0 = mapTile[entityCol][entityBottomRow];
            return tile[tileNum0] == tile[1];
        } else if (b == 2) {
            tileNum0 = mapTile[entityLeftCol][entityRow];
            return tile[tileNum0] == tile[1];
        } else if (b == 3) {
            tileNum0 = mapTile[entityRightCol][entityRow];
            return tile[tileNum0] == tile[1];
        }
        return false;
    }

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[2];

        mapTile = new int[gp.maxCol][gp.maxRow];

        setTileImage();

        try {
            loadMap("map0.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String s) throws IOException {
        File file = new File(directory + "\\images\\maps\\" + s);
        BufferedReader br = new BufferedReader(new FileReader(file));

        col = 0;
        row = 0;
        x = 0;
        y = 0;

        while (col < gp.maxCol && row < gp.maxRow) {
            string = br.readLine();
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
        br.close();
    }

    public void setTileImage() {
        tile[0] = new Tile();
        tile[1] = new Tile();

        try {
            tile[0].image = ImageIO.read(new File(directory + "\\images\\tile.png"));
            tile[1].image = ImageIO.read(new File(directory + "\\images\\tile_brown.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g) {
        col = 0;
        row = 0;
        x = 0;
        y = 0;

        while (col < gp.maxCol && row < gp.maxRow) {
            tileNum = mapTile[col][row];

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
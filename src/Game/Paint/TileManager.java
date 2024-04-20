package Game.Paint;

import Game.Characters.Entity;
import Game.GamePanel;

import javax.imageio.ImageIO;

import java.awt.*;
import java.io.*;

import static Variables.Vars.directory;

public class TileManager {
    private int col, row, x, y;
    protected final int[][] mapTile;
    protected int num, tileNum;
    protected String string;
    protected String[] numbers;
    private final GamePanel gp;
    protected Tile[] tile;

    private int entityLeftWorldX, entityRightWorldX, entityTopWorldY, entityBottomWorldY,
            entityCol, entityRow, entityLeftCol, entityRightCol, entityTopRow, entityBottomRow,
            tileNum0;

    public boolean movementAllowed(Entity e, byte b) {
        entityLeftWorldX = e.playerX - e.playerSpeed;
        entityRightWorldX = e.playerX + e.size + e.playerSpeed - (byte) (0.66666666666 * e.playerSpeed);
        entityTopWorldY = e.playerY - e.playerSpeed;
        entityBottomWorldY = e.playerY + e.size + e.playerSpeed - (byte) (0.66666666666 * e.playerSpeed);

        entityCol = ((entityLeftWorldX + entityRightWorldX) / 2) / gp.tileSize;
        entityRow = ((entityBottomWorldY + entityTopWorldY) / 2) / gp.tileSize;

        entityLeftCol = entityLeftWorldX / gp.tileSize;
        entityRightCol = entityRightWorldX / gp.tileSize;
        entityTopRow = entityTopWorldY / gp.tileSize;
        entityBottomRow = entityBottomWorldY / gp.tileSize;

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

        public void loadMap (String s) throws IOException {
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

        public void setTileImage () {
            tile[0] = new Tile();
            tile[1] = new Tile();

            try {
                tile[0].image = ImageIO.read(new File(directory + "\\images\\tile.png"));
                tile[1].image = ImageIO.read(new File(directory + "\\images\\tile_brown.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void draw (Graphics2D g) {
            col = 0;
            row = 0;
            x = 0;
            y = 0;

            while (col < gp.maxCol && row < gp.maxRow) {
                tileNum = mapTile[col][row];

                g.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
                col++;
                x += gp.tileSize;

                if (col == gp.maxCol) {
                    col = 0;
                    x = 0;
                    row++;
                    y += gp.tileSize;
                }
            }
        }
    }

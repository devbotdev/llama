package Game.Tile;

import Game.GamePanel;

import javax.imageio.ImageIO;

import java.awt.*;
import java.io.*;

import static Variables.Vars.directory;

public class TileManager {
    private int col, row, x, y;
    private final int[][] mapTile;
    private int num, tileNum;
    private String string;
    private String[] numbers;
    private GamePanel gp;
    private Tile[] tile;

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

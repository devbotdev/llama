package game.object;

import game.object.management.Object;
import variables.util.UtilityTool;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static variables.Vars.*;

public class Pizza extends Object {

    public Pizza() {
        name = "pizza";
        try {
            image = UtilityTool.scaleImage(ImageIO.read(new File(directory + "\\game_resources\\4.png")), tileSizeX, tileSizeY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

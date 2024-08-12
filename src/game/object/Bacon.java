package game.object;

import game.object.management.Object;
import variables.util.UtilityTool;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static variables.Vars.*;

public class Bacon extends Object {

    public Bacon() {
        name = "bacon";
        try {
            image = UtilityTool.scaleImage(ImageIO.read(new File(directory + "\\game_resources\\1.png")), tileSizeX, tileSizeY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


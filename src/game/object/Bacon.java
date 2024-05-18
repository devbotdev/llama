package game.object;

import game.object.management.Object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static variables.Vars.directory;

public class Bacon extends Object {

    public Bacon() {
        name = "bacon";
        try {
            image = ImageIO.read(new File(directory + "\\game_resources\\1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


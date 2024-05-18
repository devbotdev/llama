package game.object;

import game.object.management.Object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static variables.Vars.directory;

public class FrenchFries extends Object {

    public FrenchFries() {
        name = "frenchfries";
        try {
            image = ImageIO.read(new File(directory + "\\game_resources\\3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

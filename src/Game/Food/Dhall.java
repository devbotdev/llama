package Game.Food;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static Variables.Vars.directory;

public class Dhall extends FoodObject {

    public Dhall() {
        name = "dhall";
        try {
            image = ImageIO.read(new File(directory + "\\images\\5.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

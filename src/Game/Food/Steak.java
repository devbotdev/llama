package Game.Food;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static Variables.Vars.directory;

public class Steak extends FoodObject {

    public Steak() {
        name = "steak";
        try {
            image = ImageIO.read(new File(directory + "\\images\\2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

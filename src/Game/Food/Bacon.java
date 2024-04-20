package Game.Food;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static Variables.Vars.directory;

public class Bacon extends FoodObject {

    public Bacon() {
        name = "bacon";
        try {
            image = ImageIO.read(new File(directory + "\\images\\1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


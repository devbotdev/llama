package Game.Food;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static Variables.Vars.directory;

public class FrenchFries extends FoodObject {

    public FrenchFries() {
        name = "bacon";
        try {
            image = ImageIO.read(new File(directory + "\\images\\3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

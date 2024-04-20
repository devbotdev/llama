package Game.Food;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static Variables.Vars.directory;

public class Hamburger extends FoodObject {

    public Hamburger() {
        name = "hamburger";
        try {
            image = ImageIO.read(new File(directory + "\\images\\0.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

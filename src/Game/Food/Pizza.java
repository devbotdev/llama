package Game.Food;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static Variables.Vars.directory;

public class Pizza extends FoodObject {

    public Pizza() {
        name = "pizza";
        try {
            image = ImageIO.read(new File(directory + "\\images\\4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

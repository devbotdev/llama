package game.object;

import game.object.management.Object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static variables.Vars.directory;

public class Key extends Object {

    private final File file;

    public Key() {
        file = new File(directory + "\\game_resources\\key.png");
        name = "key";
        isFood = false;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setImage() {
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

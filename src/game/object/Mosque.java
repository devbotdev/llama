package game.object;

import game.object.management.Object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static variables.Vars.directory;

public class Mosque extends Object {

    public Mosque() {
        name = "mosque";
        try {
            image = ImageIO.read(new File(directory + "\\game_resources\\mosque.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

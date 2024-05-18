package game.object;

import game.object.management.Object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static variables.Vars.directory;

public class Dhall extends Object {

    public Dhall() {
        name = "dhall";
        try {
            image = ImageIO.read(new File(directory + "\\game_resources\\5.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

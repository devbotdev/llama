package Game.Object;

import Game.Object.Management.Object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static Variables.Vars.directory;

public class Dhall extends Object {

    public Dhall() {
        name = "dhall";
        try {
            image = ImageIO.read(new File(directory + "\\images\\5.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

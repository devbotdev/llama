package panels.options;

import javax.swing.*;
import java.awt.*;

import static variables.Vars.*;

public class Options extends JWindow {

    public final OptionsPanel panel;
    public final SoundFrame soundFrame;

    public Options() {
        super();
        soundFrame = new SoundFrame(this);
        panel = new OptionsPanel(this);
    }

    public void optionsMenu(boolean visible) {
        optionsPressed = visible;

        getRootPane().setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY, Color.DARK_GRAY));

        setSize((int) (500 * getWidthScale()), (int) (400 * getHeightScale()));
        setLocationRelativeTo(null);

        if (optionsPressed) {
            add(panel);
            setVisible(true);
        } else {
            remove(panel);
            setVisible(false);
        }
    }

    public void hideOptions(boolean vis) {
        setVisible(!vis);
    }
}

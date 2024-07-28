package panels.options;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static variables.Vars.*;

public class Options extends JWindow {

    public final OptionsPanel panel;
    public final SoundFrame soundFrame;

    public Options() {
        super();
        soundFrame = new SoundFrame(this);
        panel = new OptionsPanel(this);

        setVisible(false);
        setAlwaysOnTop(true);
    }

    public void optionsMenu() {
        getRootPane().setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY, Color.DARK_GRAY));

        setSize((int) (500 * getWidthScale()), (int) (400 * getHeightScale()));
        setLocationRelativeTo(null);

        if (!isVisible()) {
            if (!Arrays.asList(panel.getComponents()).contains(panel)) {
                add(panel);
            }
            setVisible(true);
            requestFocus();
        } else {
            remove(panel);
            setVisible(false);
        }

        optionsPressed = isVisible();
    }

    public void optionsMenu(boolean b) {
        if (b) {
            if (!Arrays.asList(panel.getComponents()).contains(panel)) {
                add(panel);
            }
        } else {
            remove(panel);
        }
        setVisible(b);
    }
}

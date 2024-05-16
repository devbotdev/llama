package Panels.options;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Variables.Vars.*;
import static javax.swing.SwingUtilities.getRootPane;

public class Options extends JWindow{

    public final OptionsPanel panel;
    protected final SoundFrame soundFrame;

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
            setLayout(new BorderLayout());
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

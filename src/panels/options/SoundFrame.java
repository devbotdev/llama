package panels.options;

import javax.swing.*;

import java.awt.*;

import static variables.Vars.*;

public class SoundFrame extends JWindow {

    protected static boolean soundPressed = false;
    private final SoundPanel soundPanel;
    private final Options options;

    public SoundFrame(Options options) {
        super();
        this.options = options;

        soundPanel = new SoundPanel(this);
    }

    public void soundFrame(boolean visible) {
        getRootPane().setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY, Color.DARK_GRAY));
        soundPressed = visible;

        setSize((int) (500 * getWidthScale()), (int) (300 * getHeightScale()));
        setLocationRelativeTo(null);

        if (soundPressed) {
            add(soundPanel);
            options.hideOptions(true);
        } else {
            remove(soundPanel);
            if (!gameStarted) {
                options.hideOptions(false);
            }
        }

        soundPanel.setVisible(soundPressed);
        setVisible(soundPressed);

        addWindowListener(null);
    }
}

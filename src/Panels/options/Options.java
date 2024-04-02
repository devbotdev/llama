package Panels.options;

import javax.swing.*;
import java.awt.*;

import static Variables.Vars.getHeightScale;
import static Variables.Vars.getWidthScale;
import static Variables.Vars.optionsPressed;

public class Options {
    private static final JFrame options = new JFrame("Options");
    protected static final OptionsPanel panel = new OptionsPanel();
    public static void options(boolean visible) {
        optionsPressed = visible;

        if (optionsPressed) {
            options.setLayout(new BorderLayout());
            options.add(panel);
            options.setVisible(true);
        } else {
            options.remove(panel);
            options.setVisible(false);
        }

        options.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        options.setSize((int) (500 * getWidthScale()), (int) (300 * getHeightScale()));
        options.setLocationRelativeTo(null);

    }
}

package Panels.options;

import javax.swing.*;
import java.awt.*;

import static Panels.resource.GUI.optionsPressed;
import static Va.Vars.getHeightScale;
import static Va.Vars.getWidthScale;

public class Options {

    private static final JFrame options = new JFrame("Options");

    private static final OptionsPanel panel = new OptionsPanel();
    public static void options(boolean visible) {
        options.setLayout(new BorderLayout());
        options.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        options.setSize((int) (500 * getWidthScale()), (int) (300 * getHeightScale()));
        options.setLocationRelativeTo(null);

        options.add(panel);

        options.setVisible(visible);
        optionsPressed = !optionsPressed;
    }
}

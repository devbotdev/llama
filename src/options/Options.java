package options;

import javax.swing.*;
import java.awt.*;

import static resource.GUI.optionsPressed;

public class Options {

    private static final JFrame options = new JFrame("Options");



    private static final OptionsPanel panel = new OptionsPanel();
    public static void options(boolean visible) {
        options.setLayout(new BorderLayout());
        options.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        options.setSize(500, 300);
        options.setLocationRelativeTo(null);

        options.add(panel);

        options.setVisible(visible);
        optionsPressed = !optionsPressed;
    }
}

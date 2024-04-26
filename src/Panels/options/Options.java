package Panels.options;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Variables.Vars.*;

public class Options implements KeyListener {

    protected final JFrame options;
    public final OptionsPanel panel;
    private int e;

    public Options() {
        options = new JFrame("Options");
        panel = new OptionsPanel();
    }

    public void optionsMenu(boolean visible) {
        optionsPressed = visible;

        options.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        options.setSize((int) (500 * getWidthScale()), (int) (300 * getHeightScale()));
        options.setLocationRelativeTo(null);

        if (optionsPressed) {
            options.setLayout(new BorderLayout());
            options.add(panel);
            options.setVisible(true);
        } else {
            options.remove(panel);
            options.setVisible(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent event) {

    }

    @Override
    public void keyPressed(KeyEvent event) {
    }

    @Override
    public void keyReleased(KeyEvent event) {

    }
}

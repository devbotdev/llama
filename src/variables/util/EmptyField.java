package variables.util;

import variables.Vars;

import javax.swing.*;
import java.awt.*;

public class EmptyField extends JLabel {

    public EmptyField(Dimension d) {
        super(" ", JLabel.CENTER);
        setPreferredSize(d);
        setBackground(Vars.blankColor);
        setForeground(Vars.blankColor);
        setBorder(null);
    }

    public EmptyField clone(Dimension d) {
        return new EmptyField(d);
    }
}

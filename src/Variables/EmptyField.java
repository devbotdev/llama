package Variables;

import javax.swing.*;

public class EmptyField extends JLabel {

    public EmptyField() {
        super("", JLabel.CENTER);
    }

    public EmptyField clone() {
        return new EmptyField();
    }
}

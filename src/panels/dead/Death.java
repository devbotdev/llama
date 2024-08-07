package panels.dead;

import game.GamePanel;

import javax.swing.*;

import static variables.Vars.blankColor;
import static variables.Vars.fullscreenDimension;

public class Death extends JWindow {

    protected final GamePanel gp;
    public final DeathPanel deathPanel;

    public Death(GamePanel gp) {
        super();
        this.gp = gp;
        deathPanel = new DeathPanel(this);
        setFocusableWindowState(true);
        setAlwaysOnTop(true);
    }

    public void showDeathScreen() {
        gp.endGame();

        setFocusable(true);

        requestFocus();

        setSize(fullscreenDimension);

        setBackground(blankColor);

        add(deathPanel);

        setVisible(true);
    }

    public void hideDeathScreen() {
        setVisible(false);

        remove(deathPanel);
    }
}

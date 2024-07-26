package panels.dead;

import game.GamePanel;

import javax.swing.*;

import static variables.Vars.blankColor;
import static variables.Vars.fullscreenDimension;

public class Death extends JWindow {

    private final GamePanel gp;
    public final DeathPanel deathPanel;

    public Death(GamePanel gp) {
        super();
        this.gp = gp;
        deathPanel = new DeathPanel(this);
        setFocusableWindowState(true);
    }

    public void showDeathScreen() {
        setFocusable(true);

        requestFocus();

        setSize(fullscreenDimension);

        setBackground(blankColor);

        gp.gameRunning = false;

        add(deathPanel);

        setVisible(true);
    }

    public void hideDeathScreen() {
        setVisible(false);

        remove(deathPanel);
    }
}

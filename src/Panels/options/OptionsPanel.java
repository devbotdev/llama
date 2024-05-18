package Panels.options;

import Game.GamePanel;
import Panels.resource.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Variables.Vars.getDimension;
import static Variables.Vars.*;

public class OptionsPanel extends JPanel implements ActionListener {

    private Object button;
    private final JButton food, bossModeButton, exit, sound;
    private final JLabel foodLabel;
    private GamePanel gp;
    protected final GridLayout gridLayout;
    private final Options options;

    public OptionsPanel(Options options) {
        this.options = options;

        gridLayout = new GridLayout(5, 1);
        setLayout(gridLayout);

        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        setOpaque(true);
        setBackground(Color.WHITE);

        foodLabel = new JLabel("Food Type", JLabel.CENTER);

        food = new JButton("Hamburger");
        exit = new JButton("Exit");
        bossModeButton = new JButton();
        sound = new JButton("Sound");

        food.setBackground(Color.WHITE);
        exit.setBackground(Color.WHITE);
        bossModeButton.setBackground(Color.WHITE);
        sound.setBackground(Color.WHITE);

        food.setPreferredSize(getDimension());
        exit.setPreferredSize(getDimension());
        bossModeButton.setPreferredSize(getDimension());
        sound.setPreferredSize(getDimension());

        refresh();
        food.setFont(getMenuFont());
        exit.setFont(getMenuFont());
        bossModeButton.setFont(getMenuFont());
        foodLabel.setFont(getMenuFont());
        sound.setFont(getMenuFont());

        if (bossMode) {
            bossModeButton.setText("Boss Mode: Orielbisha");
        } else {
            bossModeButton.setText("Boss Mode: Disabled");
        }

        add(foodLabel);
        add(food);
        add(bossModeButton);
        add(sound);
        add(exit);

        food.addActionListener(this);
        exit.addActionListener(this);
        bossModeButton.addActionListener(this);
        sound.addActionListener(this);
    }

    public void setGamePanel(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        button = e.getSource();
        refresh();

        if (button == sound) {
            options.soundFrame.soundFrame(true);
        }

        if (button == food && !gameStarted) {
            if (foodType == 0) {
                food.setText("Bacon");
                foodType++;
            } else if (foodType == 1) {
                food.setText("Steak");
                foodType++;
            } else if (foodType == 2) {
                food.setText("French Fries");
                foodType++;
            } else if (foodType == 3) {
                food.setText("Pizza");
                foodType++;
            } else if (foodType == 4) {
                food.setText("Hamburger");
                foodType = 0;
            }
        }

        if (button == exit) {
            MainMenu.o.optionsMenu(false);
            if (gameStarted) gp.pause.getPausePanel().requestFocus();
        }

        if (button == bossModeButton && !gameStarted) {
            if (!bossMode) {
                bossModeButton.setText("Boss Mode: Orielbisha");
                bossMode = true;
            } else {
                bossModeButton.setText("Boss Mode: Disabled");
                bossMode = false;
            }
        }
    }
}


package Panels.options;

import Panels.pause.PausePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Panels.pause.Pause.*;
import static Variables.Vars.getDimension;
import static Panels.options.Options.*;
import static Variables.Vars.*;

public class OptionsPanel extends JPanel implements ActionListener {

    private Object button;
    private final JButton food, bossModeButton, exit;
    private final JLabel foodLabel = new JLabel("Food Type");

    public OptionsPanel() {
        setLayout(new GridBagLayout());

        setOpaque(true);
        setBackground(Color.WHITE);

        food = new JButton("Hamburger");
        exit = new JButton("Exit");
        bossModeButton = new JButton();

        food.setBackground(Color.WHITE);
        exit.setBackground(Color.WHITE);
        bossModeButton.setBackground(Color.WHITE);

        food.setPreferredSize(getDimension());
        exit.setPreferredSize(getDimension());
        bossModeButton.setPreferredSize(getDimension());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 20;
        gbc.ipady = 20;

        food.setFont(getMenuFont());
        exit.setFont(getMenuFont());
        bossModeButton.setFont(getMenuFont());
        foodLabel.setFont(getMenuFont());

        if (bossMode) {
            bossModeButton.setText("Boss Mode: Orielbisha");
        } else {
            bossModeButton.setText("Boss Mode: Disabled");
        }

        add(foodLabel);
        gbc.gridy++;
        add(food, gbc);
        gbc.gridy++;
        add(bossModeButton, gbc);
        gbc.gridy++;
        add(exit, gbc);

        food.addActionListener(this);
        exit.addActionListener(this);
        bossModeButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        button = e.getSource();
        refresh();

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
            Options.options(false);
            if (gameStarted) getPausePanel().requestFocus();
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


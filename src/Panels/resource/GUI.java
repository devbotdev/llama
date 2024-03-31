package Panels.resource;

import Game.GamePanel;
import Panels.options.Options;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Variables.Vars.*;

public class GUI extends JPanel implements ActionListener {
    private static JButton playButton, optionsButton, quitButton;
    public static boolean optionsPressed = false;
    private static boolean exitPressed = false;
    private static JButton button;
    public GUI() {
        refresh();

        setLayout(new GridBagLayout());

        setOpaque(true);
        setBackground(Color.WHITE);

        playButton = new JButton("Play");
        optionsButton = new JButton("Options");
        quitButton = new JButton("Quit");

        playButton.setBackground(Color.WHITE);
        optionsButton.setBackground(Color.WHITE);
        quitButton.setBackground(Color.WHITE);

        playButton.setPreferredSize(getDimension());
        optionsButton.setPreferredSize(getDimension());
        quitButton.setPreferredSize(getDimension());


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 20;
        gbc.ipady = 20;

        add(playButton, gbc);
        gbc.gridy++;
        add(optionsButton, gbc);
        gbc.gridy++;
        add(quitButton, gbc);

        playButton.setFont(getMenuFont());
        optionsButton.setFont(getMenuFont());
        quitButton.setFont(getMenuFont());

        /*playButton.setFont(new Font("Serif", Font.BOLD, 25));
        optionsButton.setFont(new Font("Serif", Font.BOLD, 25));
        quitButton.setFont(new Font("Serif", Font.BOLD, 25));*/

        playButton.addActionListener(this);
        optionsButton.addActionListener(this);
        quitButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        button = (JButton) e.getSource();

        if (button == quitButton) {
            if (!exitPressed) {
                quitButton.setText("ska shanc");
                exitPressed = true;
            } else {
                quitButton.setText("luj aty gej muti");
            }
            return;
        }
        if (button == optionsButton) {
            Options.options(!optionsPressed);
            return;
        }

        if (button == playButton) {
            gamePanel = new GamePanel();

            gamePanel.startGame();
        }
    }

    public static GamePanel getGamePanel() {
        return gamePanel;
    }
}

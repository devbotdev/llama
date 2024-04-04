package Panels.resource;

import Game.GamePanel;
import Panels.options.Options;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Variables.Vars.*;

public class MainMenu extends JPanel implements ActionListener {
    private static JButton playButton, optionsButton, quitButton;
    private static boolean exitPressed = false;
    private static Object button;
    private Graphics g;
    public MainMenu() {
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

        playButton.addActionListener(this);
        optionsButton.addActionListener(this);
        quitButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        button = e.getSource();

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
            playButton.removeActionListener(this);
            gamePanel = new GamePanel();
            gamePanel.startGame();
        }
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        g = graphics;

        getHome().paintIcon(this, g, 0, 0);

        g.dispose();
    }
}

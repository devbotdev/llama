package Panels.Pause;

import Game.GamePanel;
import Panels.options.OptionsPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Panels.resource.GUI.getGamePanel;
import static Va.Vars.*;

public class PausePanel extends JPanel implements ActionListener, KeyListener {

    private JButton menu, resume, options;
    private JLabel beastMode, foodType;
    private JPanel panelButtons;
    public PausePanel() {
        setLayout(new GridBagLayout());

        setBackground(new Color(0, 0, 0, 0));
        setOpaque(false);

        if (bossMode) {
            beastMode = new JLabel("Boss Mode: Orielbisha");
        } else {
            beastMode = new JLabel("Boss Mode: Disabled");
        }
        foodType = new JLabel("0");

        menu = new JButton("Exit to Main Menu");
        resume = new JButton("Resume");
        options = new JButton("Options");

        menu.setBackground(Color.WHITE);
        resume.setBackground(Color.WHITE);
        options.setBackground(Color.WHITE);

        beastMode.setBackground(Color.WHITE);
        foodType.setBackground(Color.WHITE);

        menu.setPreferredSize(getInGameButtonDimension());
        resume.setPreferredSize(getInGameButtonDimension());
        options.setPreferredSize(getInGameButtonDimension());
        beastMode.setPreferredSize(getDimension());
        foodType.setPreferredSize(getDimension());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 20;
        gbc.ipady = 20;

        menu.setFont(getMenuFont());
        resume.setFont(getMenuFont());
        options.setFont(getMenuFont());
        beastMode.setFont(getMenuFont());
        foodType.setFont(getMenuFont());

        panelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 5));

        panelButtons.add(beastMode);
        panelButtons.add(foodType);

        add(resume, gbc);
        gbc.gridy++;
        add(options, gbc);
        gbc.gridy++;
        add(menu, gbc);
        gbc.gridy++;
        add(panelButtons, gbc);

        menu.addActionListener(this);
        resume.addActionListener(this);
        options.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}

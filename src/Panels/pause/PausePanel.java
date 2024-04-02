package Panels.pause;

import Panels.options.Options;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Panels.options.Options.options;
import static Panels.pause.Pause.pause;
import static Variables.Vars.*;

public class PausePanel extends JPanel implements ActionListener, KeyListener {

    private final JButton menu, resume, options;
    private Object e;
    public PausePanel() {
        setLayout(new GridBagLayout());

        setBackground(blankColor);

        setOpaque(false);

        menu = new JButton("Exit to Main Menu");
        resume = new JButton("Resume");
        options = new JButton("Options");

        menu.setBackground(Color.WHITE);
        resume.setBackground(Color.WHITE);
        options.setBackground(Color.WHITE);

        menu.setPreferredSize(getInGameButtonDimension());
        resume.setPreferredSize(getInGameButtonDimension());
        options.setPreferredSize(getInGameButtonDimension());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 20;
        gbc.ipady = 20;

        menu.setFont(getMenuFont());
        resume.setFont(getMenuFont());
        options.setFont(getMenuFont());

        add(resume, gbc);
        gbc.gridy++;
        add(options, gbc);
        gbc.gridy++;
        add(menu, gbc);

        menu.addActionListener(this);
        resume.addActionListener(this);
        options.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        e = actionEvent.getSource();

        if (e == options) {
            if (!javaIsShit) {
                options(optionsPressed);
                javaIsShit = true;
            } else {
                options(!optionsPressed);
            }

            return;
        }
        if (e == resume) {
            if (optionsPressed) options(false);
            pause(false);
        }
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

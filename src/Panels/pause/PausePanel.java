package Panels.pause;

import Game.GamePanel;
import Panels.resource.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import static Variables.Vars.*;

public class PausePanel extends JPanel implements ActionListener, KeyListener {

    private final JButton menu, resume, options;
    private Object object;
    private int e;
    private GamePanel gp;

    public PausePanel(GamePanel gp) {
        this.gp = gp;

        setLayout(new GridBagLayout());

        setBackground(blankColor);

        setOpaque(true);

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

        refresh();

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

        addKeyListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.requestFocus();
        object = actionEvent.getSource();

        if (object == options) {
            if (!javaIsShit) {
                MainMenu.o.optionsMenu(optionsPressed);
                javaIsShit = true;
            } else {
                MainMenu.o.optionsMenu(!optionsPressed);
            }

            return;
        }
        if (object == resume) {
            if (optionsPressed) MainMenu.o.optionsMenu(false);
            gp.pause.pause(false);
            return;
        }

        if (object == menu) {
            try {
                restart();
            } catch (IOException ignored){}
        }
    }

    @Override
    public boolean isFocusable() {
        return true;
    }

    @Override
    public void keyTyped(KeyEvent event) {
    }

    @Override
    public void keyPressed(KeyEvent event) {
        e = event.getKeyCode();
        if (e == KeyEvent.VK_ESCAPE) {
            gp.pause.pause(false);
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
    }
}

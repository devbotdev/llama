package panels.pause;

import game.GamePanel;
import variables.Actions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static variables.Vars.*;

public class PausePanel extends JPanel implements ActionListener {

    private final JButton menu, resume, options;
    private Object object;
    private final GamePanel gp;
    private Graphics g;

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

        menu.setFont(getMenuFont());
        resume.setFont(getMenuFont());
        options.setFont(getMenuFont());

        add(resume, gbc);
        gbc.gridy++;
        add(options, gbc);
        gbc.gridy++;
        add(menu, gbc);

        requestFocus();

        menu.addActionListener(this);
        resume.addActionListener(this);
        options.addActionListener(this);
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        g = graphics;

        g.drawImage(pauseImage, 0 ,0, screenWidth, screenHeight,null);

        menu.repaint();
        resume.repaint();
        options.repaint();

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.requestFocus();
        object = actionEvent.getSource();

        if (object == options) {
            if (!javaIsShit) {
                buttons.o.optionsMenu(optionsPressed);
                javaIsShit = true;
            } else {
                buttons.o.optionsMenu(!optionsPressed);
            }

            return;
        }
        if (object == resume) {
            if (optionsPressed) buttons.o.optionsMenu(false);
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
}

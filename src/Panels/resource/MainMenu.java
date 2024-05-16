package Panels.resource;

import Game.GamePanel;
import Panels.options.Options;
import Variables.Sound;
import Variables.SoundType.SoundType;

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
    private GamePanel gp;
    private GridBagConstraints gbc;
    public final Sound sound;

    public static Options o;

    public MainMenu() {
        o = new Options();
        sound = new Sound();
        sound.setFile(SoundType.MUSIC, (byte) 0, Sound.getVolume(SoundType.MUSIC));

        refresh();

        playButton = new JButton("Play");
        optionsButton = new JButton("Options");
        quitButton = new JButton("Quit");

        gbc = new GridBagConstraints();

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
            MainMenu.o.optionsMenu(!optionsPressed);
            return;
        }

        if (button == playButton) {
            playButton.removeActionListener(this);
            gp = new GamePanel();
            gp.startGame(o);
        }
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        g = graphics;

        g.drawImage(home, 0 ,0, screenWidth, screenHeight,null);

        g.dispose();

        set();
    }

    private void set() {
        setLayout(new GridBagLayout());

        setOpaque(true);
        setBackground(Color.WHITE);

        playButton.setBackground(Color.WHITE);
        optionsButton.setBackground(Color.WHITE);
        quitButton.setBackground(Color.WHITE);

        playButton.setPreferredSize(getDimension());
        optionsButton.setPreferredSize(getDimension());
        quitButton.setPreferredSize(getDimension());

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
    }
}

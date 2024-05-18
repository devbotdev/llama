package panels.resource;

import game.GamePanel;
import panels.options.Options;
import variables.Sound;
import variables.sound.SoundType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static variables.Vars.*;

public class MainMenu extends JPanel implements ActionListener {

    private static JButton playButton, optionsButton, exitButton;
    private static boolean exitPressed = false;
    private static Object button;
    private Graphics g;
    private GamePanel gp;
    private final GridBagConstraints gbc;
    public final Sound sound;

    public final Options o;

    public MainMenu() {
        o = new Options();
        sound = new Sound();
        sound.setFile(SoundType.MUSIC, (byte) 0, Sound.getVolume(SoundType.MUSIC));

        playButton = new JButton("Play");
        optionsButton = new JButton("Options");
        exitButton = new JButton("Quit");

        gbc = new GridBagConstraints();

        playButton.addActionListener(this);
        optionsButton.addActionListener(this);
        exitButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        button = e.getSource();

        if (button == exitButton) {
            if (!exitPressed) {
                exitButton.setText("ska shanc");
                exitPressed = true;
            } else {
                exitButton.setText("luj aty gej muti");
            }
            return;
        }

        if (button == optionsButton) {
            buttons.o.optionsMenu(!optionsPressed);
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

        playButton.repaint();
        optionsButton.repaint();
        exitButton.repaint();

        g.dispose();

        set();
    }

    private void set() {
        setLayout(new GridBagLayout());

        setOpaque(true);
        setBackground(Color.WHITE);

        playButton.setBackground(Color.WHITE);
        optionsButton.setBackground(Color.WHITE);
        exitButton.setBackground(Color.WHITE);

        playButton.setPreferredSize(getDimension());
        optionsButton.setPreferredSize(getDimension());
        exitButton.setPreferredSize(getDimension());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 20;
        gbc.ipady = 20;

        add(playButton, gbc);
        gbc.gridy++;
        add(optionsButton, gbc);
        gbc.gridy++;
        add(exitButton, gbc);

        playButton.setFont(getMenuFont());
        optionsButton.setFont(getMenuFont());
        exitButton.setFont(getMenuFont());
    }
}

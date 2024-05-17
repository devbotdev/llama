package Panels.options;

import Variables.SoundType.SoundType;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import static Panels.options.SoundFrame.soundPressed;
import static Variables.Vars.*;
import static Variables.Vars.getSmallFont;

public class SoundPanel extends JPanel implements ChangeListener, ActionListener {
    private final JSlider musicSlider, soundFXSlider;
    private final Hashtable<Integer, JLabel> musicTable, soundFXTable;
    private JLabel jLabel;
    protected final GridLayout gridLayout;
    protected final SoundFrame soundFrame;
    private final JButton exit;

    public SoundPanel(SoundFrame soundFrame) {
        this.soundFrame = soundFrame;

        refresh();

        gridLayout = new GridLayout(3, 1);
        setLayout(gridLayout);

        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        setOpaque(true);
        setBackground(Color.WHITE);

        musicSlider = new JSlider(-40, 0, 0);
        soundFXSlider = new JSlider(-40, 0, 0);

        exit = new JButton("Exit");

        exit.setBackground(Color.WHITE);
        exit.setPreferredSize(getDimension());
        exit.setFont(getMenuFont());

        musicSlider.setBackground(Color.WHITE);
        musicSlider.setPreferredSize(getDimension());
        musicSlider.setFont(getMenuFont());
        musicSlider.setMajorTickSpacing(10);
        musicSlider.setPaintTicks(false);

        soundFXSlider.setBackground(Color.WHITE);
        soundFXSlider.setPreferredSize(getDimension());
        soundFXSlider.setFont(getMenuFont());
        soundFXSlider.setMajorTickSpacing(10);
        soundFXSlider.setPaintTicks(true);

        musicTable = new Hashtable<>();
        soundFXTable = new Hashtable<>();

        jLabel = new JLabel("0");
        jLabel.setFont(getSmallFont());
        musicTable.put(-40, jLabel);
        soundFXTable.put(-40 , jLabel);

        jLabel = new JLabel("100");
        jLabel.setFont(getSmallFont());
        musicTable.put(0, jLabel);
        soundFXTable.put(0, jLabel);

        jLabel = new JLabel("Music");
        jLabel.setFont(getSmallFont());
        musicTable.put(-20, jLabel);

        jLabel = new JLabel("Sound FX");
        jLabel.setFont(getSmallFont());
        soundFXTable.put(-20, jLabel);

        musicSlider.setLabelTable(musicTable);
        musicSlider.setPaintLabels(true);

        soundFXSlider.setLabelTable(soundFXTable);
        soundFXSlider.setPaintLabels(true);

        add(musicSlider);
        add(soundFXSlider);
        add(exit);

        exit.addActionListener(this);
        musicSlider.addChangeListener(this);
        soundFXSlider.addChangeListener(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == musicSlider){
            buttons.sound.setVolume(musicSlider.getValue(), SoundType.MUSIC);
        }
        if (e.getSource() == soundFXSlider){
            buttons.sound.setVolume(soundFXSlider.getValue(), SoundType.SOUND_FX);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            soundFrame.soundFrame(!soundPressed);
        }
    }
}

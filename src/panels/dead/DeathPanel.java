package panels.dead;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static variables.Vars.*;

public class DeathPanel extends JPanel implements ActionListener {

    private final Death death;
    protected final GridBagLayout gridLayout;
    private Graphics2D g;

    private final JButton mainMenu, restart, options;

    public DeathPanel(Death death) {
        this.death = death;

        gridLayout = new GridBagLayout();
        setLayout(gridLayout);


        restart = new JButton("Restart");
        restart.setBackground(Color.WHITE);
        restart.setPreferredSize(getInGameButtonDimension());

        options = new JButton("Options");
        options.setBackground(Color.WHITE);
        options.setPreferredSize(getInGameButtonDimension());

        mainMenu = new JButton("Exit to Main Menu");
        mainMenu.setBackground(Color.WHITE);
        mainMenu.setPreferredSize(getInGameButtonDimension());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 20;
        gbc.ipady = 20;

        restart.setFont(getMenuFont());
        options.setFont(getMenuFont());
        mainMenu.setFont(getMenuFont());

        add(restart, gbc);
        gbc.gridy++;
        add(options, gbc);
        gbc.gridy++;
        add(mainMenu, gbc);

        restart.addActionListener(this);
        options.addActionListener(this);
        mainMenu.addActionListener(this);

        setOpaque(false);
        setBackground(blankColor);
        requestFocus();
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        g = (Graphics2D) graphics;

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        g.setColor(Color.RED);
        g.fillRect(0, 0, screenWidth, screenHeight);

        restart.repaint();
        options.repaint();
        mainMenu.repaint();

        g.dispose();

        set();
    }

    private void set() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}

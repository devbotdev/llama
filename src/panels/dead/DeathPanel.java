package panels.dead;

import variables.util.EmptyField;

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
    private final JTextField youDied;
    private final EmptyField emptyField;

    public DeathPanel(Death death) {
        this.death = death;

        emptyField = new EmptyField(getInGameButtonDimension());

        gridLayout = new GridBagLayout();
        setLayout(gridLayout);

        youDied = new JTextField("You Died");

        restart = new JButton("Restart");
        restart.setBackground(Color.WHITE);
        restart.setPreferredSize(getInGameButtonDimension());

        options = new JButton("Options");
        options.setBackground(Color.WHITE);
        options.setPreferredSize(getInGameButtonDimension());

        mainMenu = new JButton("Exit to Main Menu");
        mainMenu.setBackground(Color.WHITE);
        mainMenu.setPreferredSize(getInGameButtonDimension());

        youDied.setFont(new Font("Serif", Font.BOLD, (int) (150 * getScreenScale())));
        youDied.setForeground(Color.BLACK);
        youDied.setBackground(blankColor);
        youDied.setEditable(false);
        youDied.setBorder(null);
        youDied.setHorizontalAlignment(SwingConstants.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 20;
        gbc.ipady = 20;

        restart.setFont(getMenuFont());
        options.setFont(getMenuFont());
        mainMenu.setFont(getMenuFont());

        add(youDied, gbc);
        gbc.gridy++;
        add(restart, gbc);
        gbc.gridy++;
        add(options, gbc);
        gbc.gridy++;
        add(mainMenu, gbc);
        gbc.gridy++;
        add(emptyField, gbc);
        gbc.gridy++;
        add(emptyField.clone(getInGameButtonDimension()), gbc);
        gbc.gridy++;
        add(emptyField.clone(getInGameButtonDimension()), gbc);

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

        youDied.repaint();

        g.dispose();
    }

    private Object button;

    @Override
    public void actionPerformed(ActionEvent e) {
        button = e.getSource();

        if (button == mainMenu) {
            death.gp.backToMenu(true);
        }
        if (button == restart) {
            death.gp.restart();
        }
        if (options == mainMenu) {
            // TO-DO
        }
    }
}

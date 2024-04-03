import Variables.Vars;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Llama extends Vars implements KeyListener {
    public static void main(String[] args) {
        setVariables();

        System.out.println(getScreenScale());
        System.out.println(getHeightScale());
        System.out.println(getWidthScale());

        background = new JLabel(home);
        background.setLayout(null);
        background.setBounds(0, 0, screenWidth, screenHeight);
        buttons.setBackground(blankColor);

        createGUI("Orjel Lamaj Type Game");

        frame.add(background);
        frame.add(buttons);

        frame.setVisible(true);
    }
    public static void createGUI(String title, int width, int height) {
        frame = new JFrame(title);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(width,height);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
    }
    public static void createGUI(String title) {
        frame = new JFrame(title);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
    }

    private static void setVariables() {
        foodType = 0;
        bossMode = false;
        gameStarted = false;
        javaIsShit = false;
        optionsPressed = false;
        isJavaGay = false;
        codeIsWritten = false;

        home = new ImageIcon(image);
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
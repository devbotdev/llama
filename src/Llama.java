import Va.Vars;

import javax.swing.*;
import java.awt.*;

public class Llama extends Vars {

    public static void main(String[] args) {
        System.out.println(getScreenScale());
        System.out.println(getHeightScale());
        System.out.println(getWidthScale());

        home = new ImageIcon(image);

        background = new JLabel(home);
        background.setLayout(null);
        background.setBounds(0, 0, getScreenWidth(), getScreenHeight());

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
}
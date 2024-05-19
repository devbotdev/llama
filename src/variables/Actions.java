package variables;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Actions {

    private final EscAction escAction;
    private final UpAction upAction;
    private final DownAction downAction;
    private final LeftAction leftAction;
    private final RightAction rightAction;

    public Actions() {
        escAction = new EscAction();
        upAction = new UpAction();
        downAction = new DownAction();
        leftAction = new LeftAction();
        rightAction = new RightAction();
    }

    public final static class EscAction extends AbstractAction {

        public EscAction() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("TEST");
        }
    }

    public final static class UpAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public final static class DownAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public final static class LeftAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public final static class RightAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public static String getName(Action a) {
        if (a.getClass() == EscAction.class) {
            return "escAction";
        }
        if (a.getClass() == UpAction.class) {
            return "upAction";
        }
        if (a.getClass() == DownAction.class) {
            return "downAction";
        }
        if (a.getClass() == LeftAction.class) {
            return "leftAction";
        }
        if (a.getClass() == RightAction.class) {
            return "rightAction";
        }
        return "";
    }

    public static KeyStroke getKeyStroke(Action a) {
        if (a.getClass() == EscAction.class) {
            return KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        }
        if (a.getClass() == UpAction.class) {
            return KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);
        }
        if (a.getClass() == DownAction.class) {
            return KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);
        }
        if (a.getClass() == LeftAction.class) {
            return KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0);
        }
        if (a.getClass() == RightAction.class) {
            return KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0);
        }
        return null;
    }

    public EscAction getEscAction() {
        return escAction;
    }

    public UpAction getUpAction() {
        return upAction;
    }

    public DownAction getDownAction() {
        return downAction;
    }

    public LeftAction getLeftAction() {
        return leftAction;
    }

    public RightAction getRightAction() {
        return rightAction;
    }
}

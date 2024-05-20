package variables;

import game.GamePanel;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Actions {

    private final EscAction escAction;
    private final UpAction upAction;
    private final DownAction downAction;
    private final LeftAction leftAction;
    private final RightAction rightAction;

    private final UpActionReleased upActionReleased;
    private final DownActionReleased downActionReleased;
    private final LeftActionReleased leftActionReleased;
    private final RightActionReleased rightActionReleased;
    private static GamePanel gp;

    public Actions() {
        escAction = new EscAction();
        upAction = new UpAction();
        downAction = new DownAction();
        leftAction = new LeftAction();
        rightAction = new RightAction();

        upActionReleased = new UpActionReleased();
        downActionReleased = new DownActionReleased();
        leftActionReleased = new LeftActionReleased();
        rightActionReleased = new RightActionReleased();
    }

    public void setGPClass(GamePanel gp) {
        Actions.gp = gp;
    }

    public final static class EscAction extends AbstractAction {

        public EscAction() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getSource().getClass());

            if (e.getSource().getClass() == GamePanel.class) {
                gp.pause.pause(!gp.pausePressed);
            }
        }
    }

    public final static class UpAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!gp.gameRunning) return;
            gp.orjeli.upPressed = true;
        }
    }

    public final static class DownAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!gp.gameRunning) return;
            gp.orjeli.downPressed = true;
        }
    }

    public final static class LeftAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!gp.gameRunning) return;
            gp.orjeli.leftPressed = true;
        }
    }

    public final static class RightAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!gp.gameRunning) return;
            gp.orjeli.rightPressed = true;
        }
    }

    public final static class UpActionReleased extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            gp.orjeli.upPressed = false;
        }
    }

    public final static class DownActionReleased extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            gp.orjeli.downPressed = false;
        }
    }

    public final static class LeftActionReleased extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            gp.orjeli.leftPressed = false;
        }
    }

    public final static class RightActionReleased extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            gp.orjeli.rightPressed = false;
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

        if (a.getClass() == UpActionReleased.class) {
            return "upActionReleased";
        }
        if (a.getClass() == DownActionReleased.class) {
            return "downActionReleased";
        }
        if (a.getClass() == LeftActionReleased.class) {
            return "leftActionReleased";
        }
        if (a.getClass() == RightActionReleased.class) {
            return "rightActionReleased";
        }
        return "";
    }

    public static KeyStroke getKeyStroke(Action a) {
        if (a.getClass() == EscAction.class) {
            return KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
        } else if (a.getClass() == UpAction.class) {
            return KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false);
        } else if (a.getClass() == DownAction.class) {
            return KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false);
        } else if (a.getClass() == LeftAction.class) {
            return KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false);
        } else if (a.getClass() == RightAction.class) {
            return KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false);
        }

        if (a.getClass() == UpActionReleased.class) {
            return KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true);
        } else if (a.getClass() == DownActionReleased.class) {
            return KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true);
        } else if (a.getClass() == LeftActionReleased.class) {
            return KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true);
        } else if (a.getClass() == RightActionReleased.class) {
            return KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true);
        }
        return null;
    }

    public EscAction getEscAction() {
        return escAction;
    }

    public AbstractAction getUpAction(boolean released) {
        if (released) {
            return upActionReleased;
        } else {
            return upAction;
        }
    }

    public AbstractAction getDownAction(boolean released) {
        if (released) {
            return downActionReleased;
        } else {
            return downAction;
        }
    }

    public AbstractAction getLeftAction(boolean released) {
        if (released) {
            return leftActionReleased;
        } else {
            return leftAction;
        }
    }

    public AbstractAction getRightAction(boolean released) {
        if (released) {
            return rightActionReleased;
        } else {
            return rightAction;
        }
    }
}

package variables.util;

import game.GamePanel;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Actions {

    private static GamePanel gp;

    private final EscAction escAction;
    private final UpAction upAction;
    private final DownAction downAction;
    private final LeftAction leftAction;
    private final RightAction rightAction;

    private final UpActionReleased upActionReleased;
    private final DownActionReleased downActionReleased;
    private final LeftActionReleased leftActionReleased;
    private final RightActionReleased rightActionReleased;

    private final TabAction tabAction;
    private final TabActionReleased tabActionReleased;

    public static byte ESC_ACTION = 0;

    public static byte UP_ACTION = 1;
    public static byte DOWN_ACTION = 2;
    public static byte LEFT_ACTION = 3;
    public static byte RIGHT_ACTION = 4;

    public static byte TAB_ACTION = 5;

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

        tabAction = new TabAction();
        tabActionReleased = new TabActionReleased();
    }

    public void setGPClass(GamePanel gp) {
        Actions.gp = gp;
    }

    private final static class TabAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            gp.orjeli.tabPressed = true;
        }
    }

    private final static class TabActionReleased extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            gp.orjeli.tabPressed = false;
        }
    }

    private final static class EscAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().getClass() == GamePanel.class) {
                gp.pause.pause(!gp.pausePressed, true);
            }
        }
    }

    private final static class UpAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!gp.gameRunning) return;
            gp.orjeli.upPressed = true;
        }
    }

    private final static class DownAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!gp.gameRunning) return;
            gp.orjeli.downPressed = true;
        }
    }

    private final static class LeftAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!gp.gameRunning) return;
            gp.orjeli.leftPressed = true;
        }
    }

    private final static class RightAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!gp.gameRunning) return;
            gp.orjeli.rightPressed = true;
        }
    }

    private final static class UpActionReleased extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            gp.orjeli.upPressed = false;
        }
    }

    private final static class DownActionReleased extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            gp.orjeli.downPressed = false;
        }
    }

    private final static class LeftActionReleased extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            gp.orjeli.leftPressed = false;
        }
    }

    private final static class RightActionReleased extends AbstractAction {
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
        if (a.getClass() == TabAction.class) {
            return "tabAction";
        }
        if (a.getClass() == TabActionReleased.class) {
            return "tabActionReleased";
        }
        return "";
    }

    public static KeyStroke getKeyStroke(Action a, boolean secondary) {
        if (a.getClass() == EscAction.class) {
            return KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
        }
        if (a.getClass() == TabAction.class) {
            return KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0, false);
        }
        if (a.getClass() == TabActionReleased.class) {
            return KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0, true);
        }

        if (!secondary) {
            if (a.getClass() == UpAction.class) {
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

        } else {
            if (a.getClass() == UpAction.class) {
                return KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false);
            } else if (a.getClass() == DownAction.class) {
                return KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false);
            } else if (a.getClass() == LeftAction.class) {
                return KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false);
            } else if (a.getClass() == RightAction.class) {
                return KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false);
            }

            if (a.getClass() == UpActionReleased.class) {
                return KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true);
            } else if (a.getClass() == DownActionReleased.class) {
                return KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true);
            } else if (a.getClass() == LeftActionReleased.class) {
                return KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true);
            } else if (a.getClass() == RightActionReleased.class) {
                return KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true);
            }
        }
        return null;
    }

    public AbstractAction getAction(boolean released, byte b) {
        if (b == ESC_ACTION) {
            return escAction;
        }
        if (!released) {
            if (b == TAB_ACTION) {
                return tabAction;
            }
            if (b == UP_ACTION) {
                return upAction;
            }
            if (b == DOWN_ACTION) {
                return downAction;
            }
            if (b == LEFT_ACTION) {
                return leftAction;
            }
            if (b == RIGHT_ACTION) {
                return rightAction;
            }
        } else {
            if (b == TAB_ACTION) {
                return tabActionReleased;
            }
            if (b == UP_ACTION) {
                return upActionReleased;
            }
            if (b == DOWN_ACTION) {
                return downActionReleased;
            }
            if (b == LEFT_ACTION) {
                return leftActionReleased;
            }
            if (b == RIGHT_ACTION) {
                return rightActionReleased;
            }
        }
        return null;
    }
}

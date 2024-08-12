package game.panels;

import game.GamePanel;
import game.characters.Entity;
import game.object.Key;
import game.object.Mosque;
import game.object.management.Object;
import variables.Vars;
import variables.util.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;

import static variables.Vars.*;

public class Inventory {

    private final GamePanel gp;

    int frameX, frameY, frameWidth, frameHeight;
    int cursorSize;

    public Inventory(GamePanel gp) {
        this.gp = gp;
        frameY = tileSizeY * 16;
        if (Vars.bossMode) {
            frameX = tileSizeX * 14;
            frameWidth = tileSizeX * 4;
        } else {
            frameX = (int) (tileSizeX * 14.5);
            frameWidth = tileSizeX * 3;
        }
        frameHeight = tileSizeY;

        cursorSize = getTileSize() - 6;
    }

    public void drawInventory() {
        if (!gp.orjeli.tabPressed) return;

        UtilityTool.drawSubWindow(frameX, frameY, frameWidth, frameHeight, gp.g);

        if (bossMode) {
            UtilityTool.drawRoundOutline(getCursorX(14), getCursorY(16), cursorSize, cursorSize, gp.g, new Color(255, 255, 255, 51));
            UtilityTool.drawRoundOutline(getCursorX(15), getCursorY(16), cursorSize, cursorSize, gp.g, new Color(255, 255, 255, 51));
            UtilityTool.drawRoundOutline(getCursorX(16), getCursorY(16), cursorSize, cursorSize, gp.g, new Color(255, 255, 255, 51));
            UtilityTool.drawRoundOutline(getCursorX(17), getCursorY(16), cursorSize, cursorSize, gp.g, new Color(255, 255, 255, 51));
        } else {
            UtilityTool.drawRoundOutline(getCursorX(14.5), getCursorY(16), cursorSize, cursorSize, gp.g, new Color(255, 255, 255, 51));
            UtilityTool.drawRoundOutline(getCursorX(15.5), getCursorY(16), cursorSize, cursorSize, gp.g, new Color(255, 255, 255, 51));
            UtilityTool.drawRoundOutline(getCursorX(16.5), getCursorY(16), cursorSize, cursorSize, gp.g, new Color(255, 255, 255, 51));
        }
    }

    private final Font font = new Font("Serif", Font.BOLD, (int) (20 * getScreenScale()));

    public void drawInventoryItems() {
        if (!gp.orjeli.tabPressed) return;

        gp.g.setFont(font);
        gp.g.setColor(Color.WHITE);

        if (bossMode) {
            if (gp.orjeli.entitiesKilled != 0) {
                gp.g.drawImage(gp.npcImage, getCursorX(17) + 5, getCursorY(16) + 5, cursorSize - 10, cursorSize - 10, null);
                if (gp.orjeli.entitiesKilled > 1) {
                    if (gp.orjeli.entitiesKilled >= 10) {
                        gp.g.drawString(String.valueOf(gp.orjeli.entitiesKilled), getStackSizeStringCoordinatesX(16.945), getStackSizeStringCoordinatesY(16));
                    } else {
                        gp.g.drawString(String.valueOf(gp.orjeli.entitiesKilled), getStackSizeStringCoordinatesX(17), getStackSizeStringCoordinatesY(16));
                    }
                }
            }
        }

        if (gp.orjeli.keysGathered != 0) {
            if (bossMode) {
                gp.g.drawImage(gp.setter.key.image, getCursorX(14), getCursorY(16), cursorSize, cursorSize, null);
                if (gp.orjeli.keysGathered > 1) {
                    if (gp.orjeli.keysGathered >= 10) {
                        gp.g.drawString(String.valueOf(gp.orjeli.keysGathered), getStackSizeStringCoordinatesX(13.945), getStackSizeStringCoordinatesY(16));
                    } else
                        gp.g.drawString(String.valueOf(gp.orjeli.keysGathered), getStackSizeStringCoordinatesX(14), getStackSizeStringCoordinatesY(16));
                }
            } else {
                gp.g.drawImage(gp.setter.key.image, getCursorX(14.5), getCursorY(16), cursorSize, cursorSize, null);
                if (gp.orjeli.keysGathered > 1) {
                    if (gp.orjeli.keysGathered >= 10) {
                        gp.g.drawString(String.valueOf(gp.orjeli.keysGathered), getStackSizeStringCoordinatesX(14.445), getStackSizeStringCoordinatesY(16));
                    } else
                        gp.g.drawString(String.valueOf(gp.orjeli.keysGathered), getStackSizeStringCoordinatesX(14.5), getStackSizeStringCoordinatesY(16));
                }
            }
        }

        if (gp.orjeli.mosquesEaten != 0) {
            if (bossMode) {
                gp.g.drawImage(gp.setter.mosque.image, getCursorX(15), getCursorY(16), cursorSize, cursorSize, null);
                if (gp.orjeli.mosquesEaten > 1) {
                    if (gp.orjeli.mosquesEaten >= 10) {
                        gp.g.drawString(String.valueOf(gp.orjeli.mosquesEaten), getStackSizeStringCoordinatesX(14.945), getStackSizeStringCoordinatesY(16));
                    } else
                        gp.g.drawString(String.valueOf(gp.orjeli.mosquesEaten), getStackSizeStringCoordinatesX(15), getStackSizeStringCoordinatesY(16));
                }
            } else {
                gp.g.drawImage(gp.setter.mosque.image, getCursorX(15.5), getCursorY(16), cursorSize, cursorSize, null);
                if (gp.orjeli.mosquesEaten > 1) {
                    if (gp.orjeli.mosquesEaten >= 10) {
                        gp.g.drawString(String.valueOf(gp.orjeli.mosquesEaten), getStackSizeStringCoordinatesX(15.445), getStackSizeStringCoordinatesY(16));
                    } else
                        gp.g.drawString(String.valueOf(gp.orjeli.mosquesEaten), getStackSizeStringCoordinatesX(15.5), getStackSizeStringCoordinatesY(16));
                }
            }
        }

        if (gp.orjeli.foodEaten != 0) {
            assert gp.setter.food != null;
            if (bossMode) {
                gp.g.drawImage(gp.setter.food.image, getCursorX(16), getCursorY(16), cursorSize, cursorSize, null);
                if (gp.orjeli.foodEaten > 1) {
                    if (gp.orjeli.foodEaten >= 10) {
                        gp.g.drawString(String.valueOf(gp.orjeli.foodEaten), getStackSizeStringCoordinatesX(15.945), getStackSizeStringCoordinatesY(16));
                    } else
                        gp.g.drawString(String.valueOf(gp.orjeli.foodEaten), getStackSizeStringCoordinatesX(16), getStackSizeStringCoordinatesY(16));
                }
            } else {
                gp.g.drawImage(gp.setter.food.image, getCursorX(16.5), getCursorY(16), cursorSize, cursorSize, null);
                if (gp.orjeli.foodEaten > 1) {
                    if (gp.orjeli.foodEaten >= 10) {
                        gp.g.drawString(String.valueOf(gp.orjeli.foodEaten), getStackSizeStringCoordinatesX(16.445), getStackSizeStringCoordinatesY(16));
                    } else
                        gp.g.drawString(String.valueOf(gp.orjeli.foodEaten), getStackSizeStringCoordinatesX(16.5), getStackSizeStringCoordinatesY(16));
                }
            }
        }
    }

    private int getStackSizeStringCoordinatesX(double i) {
        return getCursorX(i) + tileSizeX - 17;
    }

    private int getStackSizeStringCoordinatesY(double i) {
        return getCursorY(i) + tileSizeY - 7;
    }

    private int getCursorX(double i) {
        return (int) ((tileSizeX * i) + 3);
    }

    private int getCursorY(double i) {
        return (int) ((tileSizeY * i) + 3);
    }
}

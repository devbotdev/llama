package game.characters;

import java.awt.*;

public class Entity {

    public byte direction;
    public float fatnessLevel;
    public static int entityX, entityY;
    public double entitySpeed, entitySpeedF;

    public int entitySpeedI;

    public short size;
    protected short previousSize;

    public short sizeXI, sizeYI, sizeI;

    public Rectangle solidArea;

    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
}

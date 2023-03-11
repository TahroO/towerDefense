package brot.enemies;

import static brot.helperMethods.Constants.Enemies.KNIGHT;

public class Knight extends Enemy{
    public Knight(float x, float y, int iD) {
        super(x, y, iD, KNIGHT);
        setStartHealth();
    }
}

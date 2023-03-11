package brot.enemies;

import static brot.helperMethods.Constants.Enemies.ORC;

public class Orc extends Enemy{
    public Orc(float x, float y, int iD) {
        super(x, y, iD, ORC);
        setStartHealth();
    }
}

package brot.enemies;

import brot.managers.EnemyManager;

import static brot.helperMethods.Constants.Enemies.KNIGHT;

public class Knight extends Enemy{
    public Knight(float x, float y, int iD, EnemyManager enemyManager) {
        super(x, y, iD, KNIGHT, enemyManager);
    }
}

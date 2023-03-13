package brot.enemies;

import brot.managers.EnemyManager;

import static brot.helperMethods.Constants.Enemies.WOLF;

public class Wolf extends Enemy{
    public Wolf(float x, float y, int iD, EnemyManager enemyManager) {
        super(x, y, iD, WOLF, enemyManager);
    }
}

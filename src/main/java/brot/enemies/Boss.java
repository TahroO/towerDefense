package brot.enemies;

import brot.managers.EnemyManager;

import static brot.helperMethods.Constants.Enemies.BOSS;

public class Boss extends Enemy{
    public Boss(float x, float y, int iD, EnemyManager enemyManager) {
        super(x, y, iD, BOSS, enemyManager);
    }
}

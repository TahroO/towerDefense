package brot.enemies;

import brot.managers.EnemyManager;

import static brot.helperMethods.Constants.Enemies.BAT;

public class Bat extends Enemy{
    public Bat(float x, float y, int iD, EnemyManager enemyManager) {
        super(x, y, iD, BAT, enemyManager);
    }
}

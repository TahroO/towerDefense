package brot.enemies;

import brot.managers.EnemyManager;

import static brot.helperMethods.Constants.Enemies.ORC;

public class Orc extends Enemy{
    public Orc(float x, float y, int iD, EnemyManager enemyManager) {
        super(x, y, iD, ORC, enemyManager);
        health = 50;
    }
}

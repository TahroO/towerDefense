package brot.enemies;

import brot.helperMethods.Constants;
import brot.managers.EnemyManager;

import java.awt.*;
import static brot.helperMethods.Constants.Direction.*;

public abstract class Enemy {
    protected EnemyManager enemyManager;
    // Using float to be able to get half a pixel movement speed - more control
    protected float x, y;
    // HitBox rectangle
    protected Rectangle bounds;
    protected int health;
    protected int maxHealth;
    protected int iD;
    protected int enemyType;
    protected int lastDir;
    protected boolean alive = true;
    protected int slowTickLimit = 120;
    protected int slowTick = slowTickLimit;

    public Enemy(float x, float y, int iD, int enemyType, EnemyManager enemyManager) {
        this.x = x;
        this.y = y;
        this.iD = iD;
        this.enemyType = enemyType;
        this.enemyManager = enemyManager;
        bounds = new Rectangle((int) x, (int) y, 32, 32);
        // -1 for starting point / first decision where to go
        lastDir = -1;
        setStartHealth();
    }
    private void setStartHealth() {
        health = Constants.Enemies.getStartHealth(enemyType);
        maxHealth = health;
    }

    public void hurt(int dmg) {
        this.health -= dmg;
        if (health <=0) {
            alive = false;
            enemyManager.rewardPlayer(enemyType);
        }
    }

    // If enemy reaches end, kill it
    public void kill() {
        alive = false;
        health = 0;
    }

    // Move enemy
    public void move(float speed, int dir) {
        lastDir = dir;
        if (slowTick < slowTickLimit) {
            slowTick++;
            speed *= 0.5f;
        }
        switch (dir) {
            case LEFT:
                this.x -= speed;
                break;
            case UP:
                this.y -= speed;
                break;
            case RIGHT:
                this.x += speed;
                break;
            case DOWN:
                this.y += speed;
                break;
        }
        updateHitbox();
    }

    private void updateHitbox() {
        bounds.x = (int)x;
        bounds.y = (int)y;
    }
    public void setPos(int x, int y) {
        // For position fix only / do not use for move
        this.x = x;
        this.y = y;
    }
    // Dividing health through maxHealth -> reducing healthBar width
    public float getHealthBarFloat() {
        return health / (float)maxHealth;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getHealth() {
        return health;
    }

    public int getiD() {
        return iD;
    }
    public int getEnemyType() {
        return enemyType;
    }

    public int getLastDir() {
        return lastDir;
    }

    public boolean isAlive() {
        return alive;
    }
    public void slow() {
        slowTick = 0;
    }

    public boolean isSlowed() {
        return slowTick < slowTickLimit;
    }
}

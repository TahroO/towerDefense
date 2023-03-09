package brot.enemies;

import java.awt.*;

public class Enemy {
    // Using float to be able to get half a pixel movement speed - more control
    private float x, y;
    // HitBox rectangle
    private Rectangle bounds;
    private int health;
    private int iD;
    private int enemyType;

    public Enemy(float x, float y, int iD, int enemyType) {
        this.x = x;
        this.y = y;
        this.iD = iD;
        this.enemyType = enemyType;
        bounds = new Rectangle((int) x, (int) y, 32, 32);
    }

    // Move enemy
    public void move(float x, float y) {
        this.x += x;
        this.y += y;
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

}

package brot.enemies;

import java.awt.*;
import static brot.helperMethods.Constants.Direction.*;

public abstract class Enemy {
    // Using float to be able to get half a pixel movement speed - more control
    private float x, y;
    // HitBox rectangle
    private Rectangle bounds;
    private int health;
    private int iD;
    private int enemyType;
    private int lastDir;

    public Enemy(float x, float y, int iD, int enemyType) {
        this.x = x;
        this.y = y;
        this.iD = iD;
        this.enemyType = enemyType;
        bounds = new Rectangle((int) x, (int) y, 32, 32);
        // -1 for starting point / first decision where to go
        lastDir = -1;
    }

    // Move enemy
    public void move(float speed, int dir) {
        lastDir = dir;
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
    }

    public void setPos(int x, int y) {
        // For position fix only / do not use for move
        this.x = x;
        this.y = y;
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

}

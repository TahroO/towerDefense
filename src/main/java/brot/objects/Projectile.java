package brot.objects;

import java.awt.geom.Point2D;

public class Projectile {
    private Point2D.Float pos;
    private int iD, projectileType, dmg;
    private float xSpeed, ySpeed, rotation;
    private boolean active = true;

    public Projectile(float x, float y, float xSpeed, float ySpeed, int dmg, float rotation, int iD, int projectileType) {
        pos = new Point2D.Float(x, y);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.dmg = dmg;
        this.iD = iD;
        this.projectileType = projectileType;
        this.rotation = rotation;
    }
    public void move() {
        pos.x += xSpeed;
        pos.y += ySpeed;
    }

    public Point2D.Float getPos() {
        return pos;
    }

    public void setPos(Point2D.Float pos) {
        this.pos = pos;
    }

    public int getiD() {
        return iD;
    }

    public int getProjectileType() {
        return projectileType;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
    public int getDmg() {
        return dmg;
    }
    public float getRotation() {
        return rotation;
    }
}

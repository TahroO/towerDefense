package brot.objects;

import java.awt.geom.Point2D;

public class Projectile {
    private Point2D.Float pos;
    private int iD, projectileType;
    private boolean active = true;

    public Projectile(float x, float y, int iD, int projectileType) {
        pos = new Point2D.Float(x, y);
        this.iD = iD;
        this.projectileType = projectileType;
    }
    public void move(float x, float y) {
        pos.x += x;
        pos.y += y;
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
}

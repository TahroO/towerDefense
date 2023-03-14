package brot.objects;

import brot.helperMethods.Constants;

import static brot.helperMethods.Constants.Towers.*;

public class Tower {
    private int x, y, iD, towerType, cdTick, dmg, tier;
    private float range, cooldown;

    public Tower(int x, int y, int iD, int towerType) {
        this.x = x;
        this.y = y;
        this.iD = iD;
        this.towerType = towerType;
        tier = 1;
        setDefaultDmg();
        setDefaultRange();
        setDefaultCooldown();
    }
    public void update() {
        cdTick++;
    }
    public void upgradeTower() {
        this.tier++;
        switch (towerType) {
            case ARCHER:
                dmg += 2;
                range += 5;
                cooldown -= 3;
                break;
            case WIZARD:
                range += 20;
                cooldown -= 10;
                break;
            case CANNON:
                dmg += 5;
                range += 10;
                cooldown -= 15;
                break;
        }
    }

    public boolean isCooldownOver() {
        return cdTick >= cooldown;
    }

    public void resetCooldown() {
        cdTick = 0;
    }
    private void setDefaultCooldown() {
        cooldown = Constants.Towers.getDefaultCooldown(towerType);
    }

    private void setDefaultRange() {
        range = Constants.Towers.getDefaultRange(towerType);
    }

    private void setDefaultDmg() {
        dmg = Constants.Towers.getStartDmg(towerType);
    }

    public int getDmg() {
        return dmg;
    }

    public float getRange() {
        return range;
    }

    public float getCooldown() {
        return cooldown;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public int getTowerType() {
        return towerType;
    }

    public void setTowerType(int towerType) {
        this.towerType = towerType;
    }

    public int getTier() {
        return tier;
    }
}

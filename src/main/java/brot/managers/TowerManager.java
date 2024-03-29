package brot.managers;

import brot.enemies.Enemy;
import brot.helperMethods.Constants;
import brot.helperMethods.LoadSave;
import brot.objects.Tower;
import brot.scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static brot.helperMethods.Constants.Towers.ARCHER;

public class TowerManager {
    private Playing playing;
    private BufferedImage[] towerImgs;
    private ArrayList<Tower> towers = new ArrayList<>();
    private int towerAmount = 0;

    public TowerManager(Playing playing) {
        this.playing = playing;
        loadTowerImgs();
    }

    private void loadTowerImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        towerImgs = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            towerImgs[i] = atlas.getSubimage((4 + i) * 32, 32, 32, 32);
        }
    }

    public void update() {
        for (Tower t : towers) {
            t.update();
            attackEnemyIfClose(t);
        }
    }

    public void removeTower(Tower displayedTower) {
        for (int i = 0; i < towers.size(); i++) {
            if (towers.get(i).getiD() == displayedTower.getiD()) {
                towers.remove(i);
            }
        }
    }

    public void upgradeTower(Tower displayedTower) {
        for (Tower t : towers) {
            if (t.getiD() == displayedTower.getiD()) {
                t.upgradeTower();
            }
        }
    }

    private void attackEnemyIfClose(Tower t) {
            for (Enemy e : playing.getEnemyManager().getEnemies()) {
                if (e.isAlive()) {
                    if (isEnemyInRange(t, e)) {
                        if (t.isCooldownOver()) {
                            // Tower shoot enemy
                            playing.shootEnemy(t, e);
                            t.resetCooldown();
                        }
                    }
                } else {
                    // Do not shoot
                }
            }
    }

    private boolean isEnemyInRange(Tower t, Enemy e) {
        int range = brot.helperMethods.Utils.getHypoDistance(t.getX(), t.getY(), e.getX(), e.getY());
        return range < t.getRange();
    }
    public void draw(Graphics g) {
        for (Tower t : towers) {
            g.drawImage(towerImgs[t.getTowerType()], t.getX(), t.getY(), null);
        }
    }

    // Check if there is already a tower on this position

    public Tower getTowerAt(int x, int y) {
        for (Tower t : towers) {
            if (t.getX() == x) {
                if (t.getY() == y) {
                    return t;
                }
            }
        }
        return null;
    }

    public BufferedImage[] getTowerImgs() {
        return towerImgs;
    }

    public void addTower(Tower selectedTower, int xPos, int yPos) {
        towers.add(new Tower(xPos, yPos, towerAmount++, selectedTower.getTowerType()));
    }
    public void reset() {
        towers.clear();
        towerAmount = 0;
    }
}

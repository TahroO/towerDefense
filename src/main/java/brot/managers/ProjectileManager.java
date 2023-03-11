package brot.managers;

import brot.enemies.Enemy;
import brot.helperMethods.Constants;
import brot.helperMethods.LoadSave;
import brot.objects.Projectile;
import brot.objects.Tower;
import brot.scenes.Playing;

import javax.swing.plaf.ButtonUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static brot.helperMethods.Constants.Projectiles.*;
import static brot.helperMethods.Constants.Towers.*;

public class ProjectileManager {
    private Playing playing;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private BufferedImage[] proj_imgs;
    private int proj_id = 0;
    public ProjectileManager(Playing playing) {
        this.playing = playing;
        importImgs();
    }
    private void importImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        proj_imgs = new BufferedImage[3];
        for (int i= 0; i < 3; i++) {
            proj_imgs[i] = atlas.getSubimage((7 + i) * 32, 32, 32, 32);
        }
    }
    public void newProjectile(Tower t, Enemy e) {
        int type = getProjType(t);
        int xDist = (int)(t.getX() - e.getX());
        int yDist = (int)(t.getY() - e.getY());
        int totDist = Math.abs(xDist) + Math.abs(yDist);

        float xPer = (float)Math.abs(xDist) / Math.abs(totDist);

        float xSpeed = xPer * Constants.Projectiles.getSpeed(type);
        float ySpeed = Constants.Projectiles.getSpeed(type) - xSpeed;

        if (t.getX() > e.getX()) {
            xSpeed *= -1;
            if (t.getY() > e.getY()) {
                ySpeed *= -1;
            }
        }
        float arcValue = (float) Math.atan(yDist / (float) xDist);
        float rotate = (float) Math.toDegrees(arcValue);
        if (yDist < 0) {
            rotate += 180;
        }
        projectiles.add(new Projectile(t.getX() + 16, t.getY() + 16, xSpeed, ySpeed, t.getDmg(), rotate, proj_id++, type));
    }

    private int getProjType(Tower t) {
        switch (t.getTowerType()) {
            case ARCHER:
                return ARROW;
            case CANNON:
                return BOMB;
            case WIZARD:
                return CHAINS;
        }
        return 0;
    }

    public void update() {
        for (Projectile p : projectiles) {
            if (p.isActive()) {
                p.move();
                if (isProjHittingEnemy(p)) {
                    p.setActive(false);
                } else {
                    
                }

            }
        }
    }

    private boolean isProjHittingEnemy(Projectile p) {
        for (Enemy e : playing.getEnemyManager().getEnemies()) {
            if (e.getBounds().contains(p.getPos())) {
                e.hurt(p.getDmg());
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for (Projectile p : projectiles) {
            if (p.isActive()) {
                g2d.translate(p.getPos().x, p.getPos().y);
                g2d.rotate(Math.toRadians(p.getRotation()));
                g2d.drawImage(proj_imgs[p.getProjectileType()],-16, -16, null );
                g2d.rotate(-Math.toRadians(p.getRotation()));
                g2d.translate(-p.getPos().x, -p.getPos().y);


            }
        }
    }
}

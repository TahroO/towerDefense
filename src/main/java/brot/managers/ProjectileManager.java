package brot.managers;

import brot.enemies.Enemy;
import brot.helperMethods.Constants;
import brot.helperMethods.LoadSave;
import brot.objects.Projectile;
import brot.objects.Tower;
import brot.scenes.Playing;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static brot.helperMethods.Constants.Projectiles.*;
import static brot.helperMethods.Constants.Towers.*;

public class ProjectileManager {
    private Playing playing;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private ArrayList<Explosion> explosions = new ArrayList<>();
    private BufferedImage[] proj_imgs, explo_imgs;
    private int proj_id = 0;
    private boolean drawExplo;
    private int exploTick, exploIndex;
    private Point2D.Float exploPos;

    public ProjectileManager(Playing playing) {
        this.playing = playing;
        importImgs();
    }

    private void importImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        proj_imgs = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            proj_imgs[i] = atlas.getSubimage((7 + i) * 32, 32, 32, 32);
            importExplosion(atlas);
        }
    }

    private void importExplosion(BufferedImage atlas) {
        explo_imgs = new BufferedImage[7];
        for (int i = 0; i < 7; i++) {
            explo_imgs[i] = atlas.getSubimage(i * 32, 32 * 2, 32, 32);
        }
    }

    public void newProjectile(Tower t, Enemy e) {
        int type = getProjType(t);
        int xDist = (int) (t.getX() - e.getX());
        int yDist = (int) (t.getY() - e.getY());
        int totDist = Math.abs(xDist) + Math.abs(yDist);

        float xPer = (float) Math.abs(xDist) / (float) Math.abs(totDist);

        float xSpeed = xPer * Constants.Projectiles.getSpeed(type);
        float ySpeed = Constants.Projectiles.getSpeed(type) - xSpeed;

        if (t.getX() > e.getX()) {
            xSpeed *= -1;
        }
        if (t.getY() > e.getY()) {
            ySpeed *= -1;

        }
        float rotate = 0;
        // Rotate only for Arrows
        if (type == ARROW) {
            float arcValue = (float) Math.atan(yDist / (float) xDist);
            rotate = (float) Math.toDegrees(arcValue);
            if (yDist < 0) {
                rotate += 180;
            }
        }
        projectiles.add(new Projectile(t.getX() + 16, t.getY() + 16, xSpeed, ySpeed,
                t.getDmg(), rotate, proj_id++, type));
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
                    if (p.getProjectileType() == BOMB) {
                        explosions.add(new Explosion(p.getPos()));
                    }
                } else {

                }
            }
        }

        for (Explosion e : explosions) {
            e.update();
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

//        for (int i = 0; i < explo_imgs.length; i++) {
//            g2d.drawImage(explo_imgs[i], 300 + i * 32, 300, null);
//        }

        for (int i = 0; i < projectiles.size(); i++) {
            Projectile p = projectiles.get(i);
            if (p.isActive()) {
                if (p.getProjectileType() == ARROW) {
                    g2d.translate(p.getPos().x, p.getPos().y);
                    g2d.rotate(Math.toRadians(p.getRotation()));
                    g2d.drawImage(proj_imgs[p.getProjectileType()], -16, -16, null);
                    g2d.rotate(-Math.toRadians(p.getRotation()));
                    g2d.translate(-p.getPos().x, -p.getPos().y);
                } else {
                    g2d.drawImage(proj_imgs[p.getProjectileType()], (int) p.getPos().x - 16,
                            (int) p.getPos().y - 16, null);
                }
            }
        }
        drawExplosion(g2d);
    }

    private void drawExplosion(Graphics2D g2d) {
        for (Explosion e : explosions) {
            if (e.getExploIndex() < 7) {
                g2d.drawImage(explo_imgs[e.getExploIndex()],(int)e.getPos().x,(int)e.getPos().y, null);
            }
        }
    }
    public class Explosion {
        private Point2D.Float pos;
        private int exploTick = 0, exploIndex = 0;
        public Explosion(Point2D.Float pos) {
            this.pos = pos;
        }
        public void update() {
                exploTick++;
                if (exploTick >= 12) {
                    exploTick = 0;
                    exploIndex++;
                    }
                }

        public int getExploIndex() {
            return exploIndex;
        }
        public Point2D.Float getPos() {
            return pos;
        }
    }
}

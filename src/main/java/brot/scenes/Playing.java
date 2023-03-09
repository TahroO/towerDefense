package brot.scenes;

import brot.helperMethods.LoadSave;
import brot.main.Game;
import brot.managers.EnemyManager;
import brot.ui.ActionBar;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.logging.Level;

public class Playing extends GameScene implements SceneMethods {

    private int[][] lvl;
    private ActionBar bottomBar;
    private int mouseX, mouseY;
    private EnemyManager enemyManager;

    public Playing(Game game) {
        super(game);
        loadDefaultLevel();
        bottomBar = new ActionBar(0, 640, 640, 100, this);
        enemyManager = new EnemyManager(this);
    }

    private void loadDefaultLevel() {
        lvl = LoadSave.getLevelData("new level");
    }

    public void setLevel(int[][] lvl) {
        this.lvl = lvl;
    }
    public void update() {
        updateTick();
        enemyManager.update();
    }

    @Override
    public void render(Graphics g) {
        drawLevel(g);
        bottomBar.draw(g);
        enemyManager.draw(g);
    }

    private void drawLevel(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                if (isAnimation(id)) {
                    g.drawImage(getSprite(id, animationIndex), x * 32, y * 32, null);
                } else {
                    g.drawImage(getSprite(id), x * 32, y * 32, null);
                }
            }
        }
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 640) {
            bottomBar.mouseClicked(x, y);
        } else {
            enemyManager.addEnemy(x, y);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 640) {
            bottomBar.mouseMoved(x, y);
        } else {
            // Make tiles snap to position
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (y >= 640) {
            bottomBar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        bottomBar.mouseReleased(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {
    }
}

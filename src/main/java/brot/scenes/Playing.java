package brot.scenes;

import brot.helperMethods.LevelBuilder;
import brot.main.Game;
import brot.managers.TileManager;

import java.awt.*;

public class Playing extends GameScene implements SceneMethods{
    private int[][] lvl;
    private TileManager tileManager;
    public Playing(Game game) {
        super(game);

        //The lvl
        lvl = LevelBuilder.getLevelData();
        //Tilemanager
        tileManager = new TileManager();
    }

    @Override
    public void render(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(tileManager.getSprite(id),x * 32, y * 32, null);
            }
        }
    }

    @Override
    public void mouseClicked(int x, int y) {

    }

    @Override
    public void mouseMoved(int x, int y) {

    }

    @Override
    public void mousePressed(int x, int y) {

    }

    @Override
    public void mouseReleased(int x, int y) {

    }
}

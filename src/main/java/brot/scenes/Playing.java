package brot.scenes;

import brot.helperMethods.LevelBuilder;
import brot.main.Game;
import brot.managers.TileManager;
import brot.ui.MyButton;

import java.awt.*;

import static brot.main.GameStates.MENU;
import static brot.main.GameStates.setGameState;

public class Playing extends GameScene implements SceneMethods{
    private MyButton bMenu;
    private int[][] lvl;
    private TileManager tileManager;
    public Playing(Game game) {
        super(game);
        initButtons();

        //The lvl
        lvl = LevelBuilder.getLevelData();
        //Tilemanager
        tileManager = new TileManager();
    }

    private void initButtons() {
        bMenu = new MyButton("Menu", 2,2,100,30);
    }

    @Override
    public void render(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(tileManager.getSprite(id),x * 32, y * 32, null);
            }
        }
        drawButtons(g);
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x,y)) {
            setGameState(MENU);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        if (bMenu.getBounds().contains(x,y)) {
            bMenu.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {

    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    private void resetButtons() {
        bMenu.resetBooleans();
    }
}

package brot.scenes;

import brot.helperMethods.LevelBuilder;
import brot.main.Game;
import brot.managers.TileManager;
import brot.objects.Tile;
import brot.ui.BottomBar;
import brot.ui.MyButton;

import java.awt.*;

import static brot.main.GameStates.MENU;
import static brot.main.GameStates.setGameState;

public class Playing extends GameScene implements SceneMethods {

    private int[][] lvl;
    private TileManager tileManager;
    private Tile selectedTile;
    private BottomBar bottomBar;
    private int mouseX, mouseY;
    private boolean drawSelect;
    private int lastTileX, lastTileY, lastTileId;

    public Playing(Game game) {
        super(game);


        //The lvl
        lvl = LevelBuilder.getLevelData();
        //Tilemanager
        tileManager = new TileManager();
        bottomBar = new BottomBar(0, 640, 640, 100, this);
    }


    @Override
    public void render(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(tileManager.getSprite(id), x * 32, y * 32, null);
            }
        }

        bottomBar.draw(g);
        drawSelectedTile(g);
    }

    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null && drawSelect) {
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32, null);
        }
    }

    public void setSelectedTile(Tile tile) {
        this.selectedTile = tile;
        drawSelect = true;
    }

    public TileManager getTileManager() {
        return tileManager;
    }


    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 640) {
            bottomBar.mouseClicked(x, y);
        } else {
            // Change tile at position when clicked
            changeTile(mouseX, mouseY);
        }
    }

    private void changeTile(int x, int y) {
        // Coordinates of tile + check if tile is set
        if (selectedTile != null) {
            int tileX = x / 32;
            int tileY = y / 32;
            // Check if we are still on the same tile or the tile is already placed
            if (lastTileX == tileX && lastTileY == tileY && lastTileId == selectedTile.getId()) {
                return;
            } else {
                lastTileX = tileX;
                lastTileY = tileY;
                lastTileId = selectedTile.getId();
            }
            // Change tile inside Array
            lvl[tileY][tileX] = selectedTile.getId();
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 640) {
            bottomBar.mouseMoved(x, y);
            // No draw on bottom bar
            drawSelect = false;
        } else {
            drawSelect = true;
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
        if (y >= 640) {

        } else {
            // Change tiles by holding down mouseButton
            changeTile(x, y);
        }
    }

    private void resetButtons() {

    }
}

package brot.scenes;

import brot.helperMethods.LoadSave;
import brot.main.Game;
import brot.objects.Tile;
import brot.ui.ToolBar;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Editing extends GameScene implements SceneMethods {
    private int[][] lvl;
    private Tile selectedTile;
    private int mouseX, mouseY;
    private boolean drawSelect;
    private ToolBar toolBar;
    private int lastTileX, lastTileY, lastTileId;

    public Editing(Game game) {
        super(game);
        loadDefaultLevel();
        toolBar = new ToolBar(0, 640, 640, 100, this);
    }

    private void loadDefaultLevel() {
        lvl = LoadSave.getLevelData("new level");
    }

    @Override
    public void render(Graphics g) {
        drawLevel(g);
        toolBar.draw(g);
        drawSelectedTile(g);
    }

    private void drawLevel(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(getSprite(id), x * 32, y * 32, null);
            }
        }
    }

    private BufferedImage getSprite(int spriteId) {
        return game.getTileManager().getSprite(spriteId);
    }

    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null && drawSelect) {
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32, null);
        }
    }

    public void saveLevel() {
        LoadSave.saveLevel("new level", lvl);
        game.getPlaying().setLevel(lvl);
    }

    public void setSelectedTile(Tile tile) {
        this.selectedTile = tile;
        drawSelect = true;
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
    public void mouseClicked(int x, int y) {
        if (y >= 640) {
            toolBar.mouseClicked(x, y);
        } else {
            // Change tile at position when clicked
            changeTile(mouseX, mouseY);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 640) {
            toolBar.mouseMoved(x, y);
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

    }

    @Override
    public void mouseReleased(int x, int y) {

    }

    @Override
    public void mouseDragged(int x, int y) {
        if (y >= 640) {

        } else {
            // Change tiles by holding down mouseButton
            changeTile(x, y);
        }
    }
}

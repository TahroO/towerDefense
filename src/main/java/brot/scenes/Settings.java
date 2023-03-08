package brot.scenes;

import brot.main.Game;
import brot.ui.MyButton;

import java.awt.*;

import static brot.main.GameStates.MENU;
import static brot.main.GameStates.setGameState;

public class Settings extends GameScene implements SceneMethods{
    private MyButton bMenu;
    public Settings(Game game) {
        super(game);
        initButtons();
    }

    private void initButtons() {
        bMenu = new MyButton("Menu", 2,2,100,30);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(0,0,640,640);
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

    @Override
    public void mouseDragged(int x, int y) {

    }

    private void resetButtons() {
        bMenu.resetBooleans();
    }
}

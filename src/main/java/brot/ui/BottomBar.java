package brot.ui;

import brot.scenes.Playing;

import java.awt.*;
import java.util.ArrayList;

import static brot.main.GameStates.MENU;
import static brot.main.GameStates.setGameState;

public class BottomBar {
    private int x, y, width, height;
    private Playing playing;
    private MyButton bMenu;
    private ArrayList<MyButton> tileButtons = new ArrayList<>();

    public BottomBar(int x, int y, int width, int height, Playing playing) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.playing = playing;
        initButtons();
    }
    public void draw(Graphics g) {
        //Background
        g.setColor(new Color(252, 165, 3));
        g.fillRect(x,y,width,height);
        //Buttons
        drawButtons(g);
    }
    private void initButtons() {
        bMenu = new MyButton("Menu", 2,642,100,30);
        for ()
    }
    private void drawButtons(Graphics g) {
        bMenu.draw(g);
    }

    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x,y)) {
            setGameState(MENU);
        }
    }


    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        if (bMenu.getBounds().contains(x,y)) {
            bMenu.setMouseOver(true);
        }
    }


    public void mousePressed(int x, int y) {

    }


    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    private void resetButtons() {
        bMenu.resetBooleans();
    }
}

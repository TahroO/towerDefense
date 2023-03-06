package brot.scenes;

import brot.main.Game;

import java.awt.*;

public class Settings extends GameScene implements SceneMethods{
    public Settings(Game game) {
        super(game);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(0,0,640,640);
    }
}

package brot.scenes;

import brot.main.Game;
import java.awt.*;

public class Playing extends GameScene implements SceneMethods{
    public Playing(Game game) {
        super(game);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(0,0,640,640);
    }
}

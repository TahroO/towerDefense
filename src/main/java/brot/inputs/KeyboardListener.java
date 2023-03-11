package brot.inputs;

import brot.main.Game;
import brot.main.GameStates;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static brot.main.GameStates.*;

public class KeyboardListener implements KeyListener {

    private Game game;
    public KeyboardListener(Game game) {
        this.game = game;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (GameStates.gameState == EDIT) {
            game.getEditor().keyPressed(e);
        } else if (gameState == PLAYING) {
            game.getPlaying().keyPressed(e);

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}

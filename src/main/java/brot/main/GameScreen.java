package brot.main;

import brot.inputs.KeyboardListener;
import brot.inputs.MyMouseListener;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameScreen extends JPanel {
    private Random random;
    private Game game;
    private Dimension size;
    private double timePerFrame;
    private long lastFrame;
    private MyMouseListener myMouseListener;
    private KeyboardListener keyboardListener;
    public GameScreen(Game game) {
        this.game = game;

        setPanelSize();

    }

    public void initInputs() {
        myMouseListener = new MyMouseListener(game);
        keyboardListener = new KeyboardListener(game);
        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyboardListener);
        requestFocus();
    }
    private void setPanelSize() {
        size =  new Dimension(640, 740);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }


    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        game.getRender().render(g);
    }



}

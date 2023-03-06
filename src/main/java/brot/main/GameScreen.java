package brot.main;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameScreen extends JPanel {
    private Random random;
    private Game game;
    private Dimension size;
    private double timePerFrame;
    private long lastFrame;
    public GameScreen(Game game) {
        this.game = game;

        setPanelSize();

    }
    private void setPanelSize() {
        size =  new Dimension(640, 640);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }


    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        game.getRender().render(g);
    }



}

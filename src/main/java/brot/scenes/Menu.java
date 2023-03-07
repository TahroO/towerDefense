package brot.scenes;

import brot.main.Game;
import brot.ui.MyButton;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import static brot.main.GameStates.*;
import static brot.main.GameStates.setGameState;

public class Menu extends GameScene implements SceneMethods{
    private BufferedImage img;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private Random random;
    private MyButton bPlaying, bSettings, bQuit;
    public Menu(Game game) {
        super(game);
        random = new Random();
        importImg();
        loadSprites();
        initButtons();
    }

    private void initButtons() {
        bPlaying = new MyButton("Play", 100, 100, 100, 30);
    }

    @Override
    public void render(Graphics g) {

        drawButtons(g);
//        for (int y = 0; y < 20; y++) {
//            for (int x = 0; x < 20; x++) {
//                g.drawImage(sprites.get(getRndInt()), x * 32, y * 32, null);
//            }
//        }
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bPlaying.getBounds().contains(x, y)) {
            setGameState(PLAYING);
        }
    }

    private void drawButtons(Graphics g) {
        bPlaying.draw(g);
    }
    public void importImg() {
        InputStream is = getClass().getResourceAsStream("/spriteatlas.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadSprites() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                sprites.add(img.getSubimage(x * 32, y * 32, 32, 32));
            }
        }
    }
    private int getRndInt() {
        return random.nextInt(100);
    }
}

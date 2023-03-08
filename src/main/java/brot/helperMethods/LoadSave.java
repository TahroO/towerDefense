package brot.helperMethods;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    public static BufferedImage getSpriteAtlas() {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/spriteatlas.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    // Create a txt file to store map data
    public static void createFile() {
        File txtFile = new File("src/main/resources/testTextFile.txt");
        try {
            txtFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

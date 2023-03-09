package brot.helperMethods;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImgFix {
    // Rotate
    public static BufferedImage getRotImg(BufferedImage img, int rotAngle) {
        int w = img.getWidth();
        int h = img.getHeight();

        BufferedImage newImg = new BufferedImage(w, h, img.getType());
        // Graphics 2d needed to rotate img
        Graphics2D g2d = newImg.createGraphics();

        // Rotate the img at center point
        g2d.rotate(Math.toRadians(rotAngle), w / 2d, h / 2d);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return newImg;
    }
    // Img layer build / 2 sprites over each other
    public static BufferedImage buildImg(BufferedImage[] imgs) {
        int w = imgs[0].getWidth();
        int h = imgs[0].getHeight();

        BufferedImage newImg = new BufferedImage(w, h, imgs[0].getType());
        // Graphics 2d needed to rotate img
        Graphics2D g2d = newImg.createGraphics();

        for (BufferedImage img : imgs) {
            g2d.drawImage(img, 0, 0, null);
        }
        g2d.dispose();
        return newImg;
    }

    // Rotate second img only
    public static BufferedImage getBuildRotImg(BufferedImage[] imgs, int rotAngle, int rotAtIndex) {
        int w = imgs[0].getWidth();
        int h = imgs[0].getHeight();

        BufferedImage newImg = new BufferedImage(w, h, imgs[0].getType());
        // Graphics 2d needed to rotate img
        Graphics2D g2d = newImg.createGraphics();
        for (int i = 0; i < imgs.length; i++) {
            if (rotAtIndex == i) {
                g2d.rotate(Math.toRadians(rotAngle), w / 2d, h / 2d);
            }
                g2d.drawImage(imgs[i], 0, 0, null);
                if (rotAtIndex == i) {
                    g2d.rotate(Math.toRadians(-rotAngle), w / 2d, h / 2d);
                }
        }
        g2d.dispose();
        return newImg;
    }

    // Rotate second img only +animation
    public static BufferedImage[] getBuildRotImg(BufferedImage[] imgs, BufferedImage secondImage, int rotAngle) {
        int w = imgs[0].getWidth();
        int h = imgs[0].getHeight();
        BufferedImage[] arr = new BufferedImage[imgs.length];

        for (int i = 0; i < imgs.length; i++) {
            BufferedImage newImg = new BufferedImage(w, h, imgs[0].getType());
            // Graphics 2d needed to rotate img
            Graphics2D g2d = newImg.createGraphics();
            // Draw water tile
            g2d.drawImage(imgs[i], 0, 0, null);
            // Rotate water tile
            g2d.rotate(Math.toRadians(rotAngle), w / 2d, h / 2d);
            // Place second image on top
            g2d.drawImage(secondImage, 0, 0, null);
            g2d.dispose();
            arr[i] = newImg;
        }
        return arr;
    }

}

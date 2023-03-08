package brot.helperMethods;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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
    public static void createLevel(String name, int[] idArr) {
        File newLevel = new File("src/main/resources/" + name + ".txt");
        if (newLevel.exists()) {
            System.out.println("File: " + name + " already exists!");
            return;
        } else {
            try {
                newLevel.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            writeToFile(newLevel, idArr);
        }
    }
    private static void writeToFile(File f, int[] idArr) {
        try {
            PrintWriter pw = new PrintWriter(f);
            for (Integer i : idArr) {
                pw.println(i);
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void saveLevel(String name, int[][] idArr) {
        File levelFile = new File("src/main/resources/" + name + ".txt");
        if (levelFile.exists()) {
            writeToFile(levelFile, Utils.twoDto1DintArr(idArr));
        } else {
            System.out.println("File: " + name + " does not exits!");
        }
    }
    private static ArrayList<Integer> readFromFile(File file) {
        ArrayList<Integer> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                // Transform String into Integer to store it in Integer ArrayList
                list.add(Integer.parseInt(sc.nextLine()));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static int[][] getLevelData(String name) {
        File lvlFile = new File("src/main/resources/" + name + ".txt");
        if (lvlFile.exists()) {
            ArrayList<Integer> list = readFromFile(lvlFile);
            return Utils.arrayListTo2Dint(list, 20, 20);
        } else {
            System.out.println("File: " +  name + " does not exits!");
            return null;
        }
    }
    // Save 2d int array to file

    // Load int array from file

    // Create a new lvl with default values



}

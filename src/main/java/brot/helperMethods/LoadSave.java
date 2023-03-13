package brot.helperMethods;

import brot.objects.PathPoint;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadSave {
    public static BufferedImage getSpriteAtlas() {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/spriteatlas-boss.png");
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
            writeToFile(newLevel, idArr, new PathPoint(0, 0), new PathPoint(0, 0));
        }
    }

    private static void writeToFile(File f, int[] idArr, PathPoint start, PathPoint end) {
        try {
            PrintWriter pw = new PrintWriter(f);
            for (Integer i : idArr) {
                pw.println(i);
            }
            pw.println(start.getxCord());
            pw.println(start.getyCord());
            pw.println(end.getxCord());
            pw.println(end.getyCord());

            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveLevel(String name, int[][] idArr, PathPoint start, PathPoint end) {
        File levelFile = new File("src/main/resources/" + name + ".txt");
        if (levelFile.exists()) {
            writeToFile(levelFile, Utils.twoDto1DintArr(idArr), start, end);
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

    public static ArrayList<PathPoint> getLevelPathPoints(String name) {
        File lvlFile = new File("src/main/resources/" + name + ".txt");
        if (lvlFile.exists()) {
            ArrayList<Integer> list = readFromFile(lvlFile);
            ArrayList<PathPoint> points = new ArrayList<>();
            // Starting Position
            points.add(new PathPoint(list.get(400), list.get(401)));
            points.add(new PathPoint(list.get(402), list.get(403)));
            return points;
        } else {
            System.out.println("File: " + name + " does not exits!");
            return null;
        }
    }

    public static int[][] getLevelData(String name) {
        File lvlFile = new File("src/main/resources/" + name + ".txt");
        if (lvlFile.exists()) {
            ArrayList<Integer> list = readFromFile(lvlFile);
            return Utils.arrayListTo2Dint(list, 20, 20);
        } else {
            System.out.println("File: " + name + " does not exits!");
            return null;
        }
    }
    // Save 2d int array to file

    // Load int array from file

    // Create a new lvl with default values


}

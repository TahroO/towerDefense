package brot.helperMethods;

import java.util.ArrayList;

public class Utils {
    // create a 2d int array out of an arrayList
    public static int[][] arrayListTo2Dint(ArrayList<Integer> list, int ySize, int xSize) {
        int[][] newArr = new int[ySize][xSize];

        for (int j = 0; j < newArr.length; j++) {
            for (int i = 0; i < newArr[j].length; i++) {
                int index = j * ySize + i;
                newArr[j][i] = list.get(index);
            }
        }
        return newArr;
    }

    public static int[] twoDto1DintArr(int[][] twoArr) {
        int[] oneArr = new int[twoArr.length * twoArr[0].length];
        for (int j = 0; j < twoArr.length; j++) {
            for (int i = 0; i < twoArr[j].length; i++) {
                int index = j * twoArr.length + i;
                oneArr[index] = twoArr[j][i];
            }
        }
        return oneArr;
    }
    // Calculate range using pythagoras
    public static int getHypoDistance(float x1, float y1, float x2, float y2) {
        float xDiff = Math.abs(x1 - x2);
        float yDiff = Math.abs(y1 - y2);

        return (int)Math.hypot(xDiff, yDiff);
    }
}

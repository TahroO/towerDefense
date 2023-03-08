package brot.managers;

import brot.helperMethods.ImgFix;
import brot.helperMethods.LoadSave;
import brot.objects.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager {
    public Tile GRASS, WATER, ROAD_LR, ROAD_TB, ROAD_B_TO_R, ROAD_L_TO_B, ROAD_L_TO_T, ROAD_T_TO_R,
            BL_WATER_CORNER, TL_WATER_CORNER, TR_WATER_CORNER, BR_WATER_CORNER, T_WATER, R_WATER, B_WATER, L_WATER,
            TL_ISLE, TR_ISLE, BR_ISLE, BL_ISLE;
    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();

    public TileManager() {
        loadAtlas();
        createTiles();
    }
    public void createTiles() {
        int id = 0;
        tiles.add(GRASS = new Tile(getSprite(9,0), id++, "Grass"));

        tiles.add(ROAD_LR = new Tile(getSprite(8,0), id++, "Road"));
        tiles.add(ROAD_TB = new Tile(ImgFix.getRotImg(getSprite(8,0), 90), id++, "TB_ROAD"));
        tiles.add(ROAD_B_TO_R = new Tile(getSprite(7,0), id++, "Road_Bottom_To_Right"));
        tiles.add(ROAD_L_TO_B = new Tile(ImgFix.getRotImg(getSprite(7,0), 90), id++, "Road_Left_To_Bottom"));
        tiles.add(ROAD_L_TO_T = new Tile(ImgFix.getRotImg(getSprite(7,0), 180), id++, "Road_Left_To_Top"));
        tiles.add(ROAD_T_TO_R = new Tile(ImgFix.getRotImg(getSprite(7,0), 270), id++, "Road_Top_To_Right"));

        tiles.add(WATER = new Tile(getSprite(0,0), id++, "Water"));
        tiles.add(BL_WATER_CORNER = new Tile(
                ImgFix.buildImg(
                        getImgs(0,0,5,0)
                ), id++, "BL_WATER_CORNER"));
        tiles.add(TL_WATER_CORNER = new Tile(
                ImgFix.getBuildRotImg(
                        getImgs(0,0,5,0), 90, 1),
                id++, "TL_WATER_CORNER"));
        tiles.add(TR_WATER_CORNER = new Tile(
                ImgFix.getBuildRotImg(
                        getImgs(0,0,5,0), 180, 1),
                id++, "TR_WATER_CORNER"));
        tiles.add(BR_WATER_CORNER = new Tile(
                ImgFix.getBuildRotImg(
                        getImgs(0,0,5,0), 270, 1),
                id++, "BR_WATER_CORNER"));
        tiles.add(T_WATER = new Tile(
                ImgFix.buildImg(
                        getImgs(0,0,6,0)
                ), id++, "T_WATER"));
        tiles.add(R_WATER = new Tile(
                ImgFix.getBuildRotImg(
                        getImgs(0,0,6,0), 90, 1),
                id++, "R_WATER"));
        tiles.add(B_WATER = new Tile(
                ImgFix.getBuildRotImg(
                        getImgs(0,0,6,0), 180, 1),
                id++, "B_WATER"));
        tiles.add(L_WATER = new Tile(
                ImgFix.getBuildRotImg(
                        getImgs(0,0,6,0), 270, 1),
                id++, "L_WATER"));
        tiles.add(TL_ISLE = new Tile(
                ImgFix.buildImg(
                        getImgs(0,0,4,0)
                ), id++, "TL_ISLE"));
        tiles.add(TR_ISLE= new Tile(
                ImgFix.getBuildRotImg(
                        getImgs(0,0,4,0), 90, 1),
                id++, "TR_ISLE"));
        tiles.add(BR_ISLE= new Tile(
                ImgFix.getBuildRotImg(
                        getImgs(0,0,4,0), 180, 1),
                id++, "BR_ISLE"));
        tiles.add(BL_ISLE= new Tile(
                ImgFix.getBuildRotImg(
                        getImgs(0,0,4,0), 270, 1),
                id++, "BL_ISLE"));
    }
    private BufferedImage[] getImgs(int firstX, int firstY, int secondX, int secondY){
        return new BufferedImage[] {getSprite(firstX, firstY),
        getSprite(secondX, secondY) };
    }
    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas();
    }
    public Tile getTile(int id) {
        return tiles.get(id);
    }

    public BufferedImage getSprite(int id) {
        return tiles.get(id).getSprite();
    }

    private BufferedImage getSprite(int xCord, int  yCord) {
        return atlas.getSubimage(xCord * 32,yCord * 32,32,32);
    }
}

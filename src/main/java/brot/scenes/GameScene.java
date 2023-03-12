package brot.scenes;

import brot.main.Game;

import java.awt.image.BufferedImage;

public class GameScene {
    protected Game game;
    protected int animationIndex;
    protected int tick;
    protected static final int ANIMATION_SPEED = 25;

    public GameScene(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    protected boolean isAnimation(int spriteId) {
        return game.getTileManager().isSpriteAnimation(spriteId);
    }

    /**
     * Every Frame update animation / change speed of animation
     * Higher numbers result in slower animation.
     */
    protected void updateTick() {
        tick++;
        if (tick >= ANIMATION_SPEED) {
            tick = 0;
            animationIndex = animationIndex == 3 ? 0 : animationIndex + 1;
            System.out.println("updateTick: " + Thread.currentThread().getName());
        }
    }
    protected BufferedImage getSprite(int spriteId) {
        return game.getTileManager().getSprite(spriteId);
    }

    protected BufferedImage getSprite(int spriteId, int animationIndex) {
        return game.getTileManager().getAniSprite(spriteId, animationIndex);
    }

}

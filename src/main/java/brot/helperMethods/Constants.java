package brot.helperMethods;

public class Constants {
    public static class Projectiles {
        public static final int ARROW = 0;
        public static final int CHAINS = 1;
        public static final int BOMB = 2;

        public static float getSpeed(int type) {
            switch (type) {
                case ARROW:
                    return 8f;
                case BOMB:
                    return 4f;
                case CHAINS:
                    return 6f;
            }
            return 0f;
        }
    }

    public static class Direction {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;

    }

    public static class Towers {
        public static final int CANNON = 0;
        public static final int ARCHER = 1;
        public static final int WIZARD = 2;

        public static int getTowerCost(int towerType) {
            switch (towerType) {
                case CANNON:
                    return 65;
                case ARCHER:
                    return 30;
                case WIZARD:
                    return 45;
            }
            return 0;
        }

        public static String getName(int towerType) {
            switch (towerType) {
                case CANNON:
                    return "Cannon";
                case ARCHER:
                    return "Archer";
                case WIZARD:
                    return "Wizard";
            }
            return "";
        }

        public static int getStartDmg(int towerType) {
            switch (towerType) {
                case CANNON:
                    return 10;
                case ARCHER:
                    return 6;
                case WIZARD:
                    return 0;
            }
            return 0;
        }

        public static float getDefaultRange(int towerType) {
            switch (towerType) {
                case CANNON:
                    return 80;
                case ARCHER:
                    return 100;
                case WIZARD:
                    return 90;
            }
            return 0;
        }

        // Amount of updates between shots
        public static float getDefaultCooldown(int towerType) {
            switch (towerType) {
                case CANNON:
                    return 120;
                case ARCHER:
                    return 25;
                case WIZARD:
                    return 40;
            }
            return 0;
        }
    }

    public static class Enemies {
        public static final int ORC = 0;
        public static final int BAT = 1;
        public static final int KNIGHT = 2;
        public static final int WOLF = 3;
        public static final int BOSS = 4;
        public static int getReward(int enemyType) {
            switch (enemyType) {
                case ORC:
                    return 5;
                case BAT:
                    return 6;
                case KNIGHT:
                    return 8;
                case WOLF:
                    return 7;
                case BOSS:
                    return 50;
            }
            return 0;
        }

        public static float getSpeed(int enemyType) {

            switch (enemyType) {
                case ORC:
                    return 0.5f;
                case BAT:
                    return 0.6f;
                case KNIGHT:
                    return 0.4f;
                case WOLF:
                    return 0.65f;
                case BOSS:
                    return 0.45f;
            }
            return 0f;
        }

        public static int getStartHealth(int enemyType) {
            switch (enemyType) {
                case ORC:
                    return 190;
                case BAT:
                    return 175;
                case KNIGHT:
                    return 500;
                case WOLF:
                    return 165;
                case BOSS:
                    return 9000;
            }
            return 0;
        }
    }

    public static class Tiles {
        public static final int WATER_TILE = 0;
        public static final int GRASS_TILE = 1;
        public static final int ROAD_TILE = 2;
    }
}

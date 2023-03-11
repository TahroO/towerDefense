package brot.helperMethods;

public class Constants {
    public static class Projectiles {
        public static final int ARROW = 0;
        public static final int BOMB = 2;
        public static final int CHAINS = 1;
        public static float getSpeed(int type) {
            switch (type) {
                case ARROW:
                    return 2f;
                case BOMB:
                    return 1f;
                case CHAINS:
                    return 1.5f;
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
                    return 25;
                case ARCHER:
                    return 1;
                case WIZARD:
                    return 5;
            }
            return 0;
        }
        public static float getDefaultRange(int towerType) {
            switch (towerType) {
                case CANNON:
                    return 100;
                case ARCHER:
                    return 100;
                case WIZARD:
                    return 100;
            }
            return 0;
        }
        // Amount of updates between shots
        public static float getDefaultCooldown(int towerType) {
            switch (towerType) {
                case CANNON:
                    return 20;
                case ARCHER:
                    return 10;
                case WIZARD:
                    return 15;
            }
            return 0;
        }
    }
    public static class Enemies {
        public static final int ORC = 0;
        public static final int BAT = 1;
        public static final int KNIGHT = 2;
        public static final int WOLF = 3;
        public static float getSpeed(int enemyType) {

            switch (enemyType) {
                case ORC:
                    return 0.5f;
                case BAT:
                    return 0.65f;
                case KNIGHT:
                    return 0.3f;
                case WOLF:
                    return 0.75f;
            }
            return 0f;
        }

        public static int getStartHealth(int enemyType) {
            switch (enemyType) {
                case ORC:
                    return 100;
                case BAT:
                    return 60;
                case KNIGHT:
                    return 250;
                case WOLF:
                    return 85;
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

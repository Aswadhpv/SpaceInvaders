package com.zetcode.config;

public final class GameConfig {

    private GameConfig() {
        // Prevent instantiation
    }

    public static class Board {
        public static final int WIDTH = 358;
        public static final int HEIGHT = 350;
        public static final int GROUND = 290;
        public static final int DELAY = 15;
    }

    public static class Alien {
        public static final int WIDTH = 12;
        public static final int HEIGHT = 12;
        public static final int INIT_X = 150;
        public static final int INIT_Y = 5;
        public static final int CHANCE = 5;
    }

    public static class Player {
        public static final int WIDTH = 15;
        public static final int HEIGHT = 10;
        public static final int INIT_X = 270;
        public static final int INIT_Y = 280;
    }

    public static class Bomb {
        public static final int WIDTH = 5;
        public static final int HEIGHT = 5;
    }

    public static class Logic {
        public static final int BORDER_LEFT = 5;
        public static final int BORDER_RIGHT = 5;
        public static final int GO_DOWN = 15;
        public static final int NUMBER_OF_ALIENS_TO_DESTROY = 24;
    }
}

package io.github.spiritstead.main;

public class ScreenSetting {
    final static int ORIGINAL_TILE_SIZE = 16;
    public static final int SCALE = 3;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = TILE_SIZE * maxScreenCol;
    public final int screenHeight = TILE_SIZE * maxScreenRow;
}

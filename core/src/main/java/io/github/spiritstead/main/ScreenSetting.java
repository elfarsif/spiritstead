package io.github.spiritstead.main;

public class ScreenSetting {
    final int orginalTileSize = 16;
    public final int scale = 3;
    public final int TILE_SIZE = orginalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = TILE_SIZE * maxScreenCol;
    public final int screenHeight = TILE_SIZE * maxScreenRow;
}

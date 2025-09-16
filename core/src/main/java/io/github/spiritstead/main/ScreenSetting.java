package io.github.spiritstead.main;

public class ScreenSetting {
    final int orginalTileSize = 16;
    public final int scale = 3;
    public final int tileSize = orginalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
}

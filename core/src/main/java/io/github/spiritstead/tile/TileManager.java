package io.github.spiritstead.tile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.ScreenSetting;
import io.github.spiritstead.main.WorldSettings;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class TileManager {
    private final WorldSettings worldSettings;

    public final Tile[] tiles;
    public final int mapTileNum[][];

    public TileManager(WorldSettings worldSettings, Tile[] tiles, int[][] mapTileNum) {
        this.worldSettings = worldSettings;
        this.tiles = tiles;
        this.mapTileNum = mapTileNum;
        loadTileSprites();
        loadMap("/maps/map1.txt");
    }

    private void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = this.worldSettings.maxWorldRow - 1;//start from the bottom row since 0,0 in libgdx is bottom left

            while (col < this.worldSettings.maxWorldCol && row >= 0) {
                String line = br.readLine();
                while (col < this.worldSettings.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == this.worldSettings.maxWorldCol) {
                    col = 0;
                    row--;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTileSprites() {
        tiles[0] = new Tile();
        tiles[0].image = new Sprite(new Texture("tiles/grass.png"));
        tiles[1] = new Tile();
        tiles[1].image = new Sprite(new Texture("tiles/wall.png"));
        tiles[1].collision = true;
        tiles[2] = new Tile();
        tiles[2].image = new Sprite(new Texture("tiles/water.png"));
        tiles[2].collision = true;
        tiles[3] = new Tile();
        tiles[3].image = new Sprite(new Texture("tiles/sand.png"));
        tiles[4] = new Tile();
        tiles[4].image = new Sprite(new Texture("tiles/tree.png"));
        tiles[4].collision = true;
        tiles[5] = new Tile();
        tiles[5].image = new Sprite(new Texture("tiles/earth.png"));
    }

    public void draw(SpriteBatch batch) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < this.worldSettings.maxWorldCol && worldRow < this.worldSettings.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * ScreenSetting.TILE_SIZE;
            int worldY = worldRow * ScreenSetting.TILE_SIZE;
            //Calculate where on the screen to draw the tile relative to the player
            int screenX = worldX - Game.player.getWorldPosition().getX() + Game.player.getScreenPosition().getX();
            int screenY = worldY - Game.player.getWorldPosition().getY() + Game.player.getScreenPosition().getY();

            //only draw the tile if it is within the screen bounds plus one tile size to blend
            if (worldX + ScreenSetting.TILE_SIZE > Game.player.getWorldPosition().getX() - Game.player.getScreenPosition().getX() &&
                    worldX - ScreenSetting.TILE_SIZE < Game.player.getWorldPosition().getX() + Game.player.getScreenPosition().getX() &&
                    worldY + ScreenSetting.TILE_SIZE > Game.player.getWorldPosition().getY() - Game.player.getScreenPosition().getY() &&
                    worldY - ScreenSetting.TILE_SIZE < Game.player.getWorldPosition().getY() + Game.player.getScreenPosition().getY()) {

                batch.draw(tiles[tileNum].image, screenX, screenY, ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);

            }

            worldCol++;

            if (worldCol == this.worldSettings.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }

        }

    }
}

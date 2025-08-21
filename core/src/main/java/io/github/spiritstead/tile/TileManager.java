package io.github.spiritstead.tile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.main.GamePanel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    private GamePanel gp;
    private SpriteBatch batch;
    private Tile[] tile;
    private int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadTileSprites();
        loadMap("/maps/map1.txt");
    }

    private void loadMap(String filePath) {
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = gp.maxWorldRow-1;//start from the bottom row since 0,0 in libgdx is bottom left

            while(col < gp.maxWorldCol && row >= 0){
                String line = br.readLine();
                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row--;
                }
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadTileSprites(){
        tile[0] = new Tile();
        tile[0].image = new Sprite(new Texture("tiles/grass.png"));

        tile[1] = new Tile();
        tile[1].image = new Sprite(new Texture("tiles/wall.png"));
        tile[2] = new Tile();
        tile[2].image = new Sprite(new Texture("tiles/water.png"));
        tile[3] = new Tile();
        tile[3].image = new Sprite(new Texture("tiles/sand.png"));
        tile[4] = new Tile();
        tile[4].image = new Sprite(new Texture("tiles/tree.png"));
        tile[5] = new Tile();
        tile[5].image = new Sprite(new Texture("tiles/earth.png"));
    }


    public void draw(SpriteBatch batch){
        int worldCol =0;
        int worldRow =0;

        while (worldCol< gp.maxWorldCol && worldRow <gp.maxWorldRow){
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            //Calculate where on the screen to draw the tile relative to the player
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            //only draw the tile if it is within the screen bounds plus one tile size to blend
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                batch.draw(tile[tileNum].image,screenX,screenY,gp.tileSize,gp.tileSize);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol){
                worldCol =0;
                worldRow ++;
            }

        }

    }
}

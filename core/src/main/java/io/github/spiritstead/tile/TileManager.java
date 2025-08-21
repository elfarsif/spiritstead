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
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        loadTileSprites();
        loadMap("/maps/map1.txt");
    }

    private void loadMap(String filePath) {
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = gp.maxScreenRow-1;//start from the bottom row since 0,0 in libgdx is bottom left

            while(col < gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine();
                while(col < gp.maxScreenCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol) {
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

    }

    public void draw(SpriteBatch batch){
        int col =0;
        int row =0;
        int x =0;
        int y = 0;

        while (col< gp.maxScreenCol && row <gp.maxScreenRow){
            int tileNum = mapTileNum[col][row];

            batch.draw(tile[tileNum].image,x,y,gp.tileSize,gp.tileSize);
            col++;
            x+= gp.tileSize;

            if (col == gp.maxScreenCol){
                col =0;
                x =0;
                row ++;
                y+=gp.tileSize;
            }

        }

    }
}

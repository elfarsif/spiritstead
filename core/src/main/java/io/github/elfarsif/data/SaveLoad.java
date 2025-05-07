package io.github.elfarsif.data;

import io.github.elfarsif.gdx.GamePanel;

import java.io.*;

public class SaveLoad {
    GamePanel gp;

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
    }

    public void save(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.dat"));

            DataStorage ds = new DataStorage();

            ds.level = gp.player.level;
            ds.currentLife = gp.player.currentLife;

            //Write the dataStorage object
            oos.writeObject(ds);

        } catch (Exception e) {
            System.out.println("Error saving game: " + e.getMessage());
        }

    }

    public void load(){

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

            //Read the dataStorage object
            DataStorage ds = (DataStorage) ois.readObject();
            gp.player.level = ds.level;
            gp.player.currentLife = ds.currentLife;

        } catch (Exception e) {
            System.out.println("Error loading game: " + e.getMessage());
        }
    }
}

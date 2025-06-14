package io.github.elfarsif.gdx.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.elfarsif.entity.Entity;
import io.github.elfarsif.gdx.GamePanel;
import io.github.elfarsif.objects.HealthBar;

import java.util.ArrayList;

public class UI {
    private GamePanel gp;
    public BitmapFont font;
    private Texture mushroomTexture;
    private boolean messageOn = false;
    public boolean gameFinished = false;
    SpriteBatch spriteBatch;
    public String currentDialogue;
    private Texture titleScreenImage;
    private Texture inventoryStrip;
    public int commandNum = 0;
    Sprite health1, health2, health3;
    public int slotCol = 0;
    public int slotRow = 0;
    int subState = 0;
    ArrayList<String> messages = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    int counter = 0;
    public Entity npc;
    int charIndex = 0;
    String combinedText = "";
    public OptionMenu optionMenu;

    private Texture blackTexture;

    public UI(GamePanel gp) {
        this.gp = gp;
        this.font = new BitmapFont();
        this.font.getData().setScale(2f);
        optionMenu = new OptionMenu(gp);
        loadBackgroundImage();
        loadUIImages();
        initializeTransitionTexture();

        //CREATE HEALTH OBJECTS
        Entity healthBar = new HealthBar(gp);
        health1 = healthBar.down1;
        health2 = healthBar.down2;
        health3 = healthBar.down3;
    }

    private void loadUIImages(){
        try{
            inventoryStrip = new Texture(Gdx.files.internal("ui/inventory-strip.png"));
        }catch (Exception e){
            throw new RuntimeException("Error loading image inventory strip:"+e);
        }
    }

    public void addMessage(String text){
        messages.add(text);
        messageCounter.add(0);
    }

    private void loadBackgroundImage() {
        try{
            titleScreenImage = new Texture(Gdx.files.internal("player/standing/character.png"));
        }catch (Exception e){
            throw new RuntimeException("Error loading image title screen image:"+e);
        }
    }

    private void drawMessage() {
        int messageX = gp.tileSize;
        int messageY = gp.tileSize*5;


        for(int i = 0; i < messages.size(); i++){
            if(messages.get(i) != null){
                font.setColor(Color.WHITE);
                font.draw(spriteBatch, messages.get(i), messageX, messageY);

                int counter = messageCounter.get(i)+i;
                messageCounter.set(i,counter);
                messageY +=50;

                if(counter > 120){
                    messages.remove(i);
                    messageCounter.remove(i);
                }
            }
        }

    }

    public void draw(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;

        font.setColor(Color.WHITE);
        //Play State
        if (gp.gameState == gp.playState){
            //play state studd
            drawInventory();
            drawHealthBar();
            drawMessage();
        }

        //Pause State
        if (gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        //Dialog State
        if (gp.gameState == gp.dialogueState){
            drawDialogScreen();
            drawHealthBar();
        }
        //TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        //OPTIONS STATE
        if(gp.gameState == gp.optionsState){
            optionMenu.drawOptionsScreen(spriteBatch,currentDialogue);
        }
        //TRANSITION MAP STATE
        if(gp.gameState == gp.transitionMapState){
            drawTransitionMapAnimation();
        }

        //TRADE STATE
        if(gp.gameState == gp.tradeState){
            drawTradeScreen();
        }
    }

    private void drawTradeScreen() {
        switch (subState){
            case 0:
                tradeSelect();
                break;
            case 1:
                tradeBuy();
                break;
            case 2:
                tradeSell();
                break;
        }
        gp.keyHandler.enterPressed = false;
    }

    private void tradeSell() {

    }

    private void tradeBuy() {

    }

    private void tradeSelect() {
        drawDialogScreen();

        //DRAW WINDOW
        int x = gp.tileSize*15;
        int y = gp.tileSize*4;
        int width = gp.tileSize*10;
        int height = gp.tileSize*10;
        drawSubWindow(x,y,width,height);
    }

    private void initializeTransitionTexture() {
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        blackTexture = new Texture(pixmap);
        pixmap.dispose(); // Texture is now holding the data
    }

    private void drawTransitionMapAnimation() {
        counter++;

        float alpha = Math.min(counter * 0.05f, 1f); // Counter from 0 to 50 → alpha from 0 to 1

        // Set blending and draw with alpha
        Color originalColor = spriteBatch.getColor();
        spriteBatch.setColor(0, 0, 0, alpha);
        spriteBatch.draw(blackTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.setColor(originalColor);

        if (counter ==50){
            counter = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.eventHandler.tempMap;
            gp.player.worldX = gp.eventHandler.tempCol * gp.tileSize;
            gp.player.worldY = gp.eventHandler.tempRow * gp.tileSize;
            gp.eventHandler.previousEventX = gp.player.worldX;
            gp.eventHandler.previousEventY = gp.player.worldY;
        }
    }

    private void drawInventory() {
        font.getData().setScale(1.5f);

        int frameX = gp.screenWidth/2-gp.tileSize*6;
        int frameY = gp.tileSize/2;

        spriteBatch.draw(inventoryStrip,frameX,frameY,gp.tileSize*13,gp.tileSize*2);

        //SLOT
        final int slotXStart = frameX + gp.tileSize/2;
        final int slotYStart = frameY + gp.tileSize/2;
        int slotX = slotXStart;
        int slotY = slotYStart;

        //DRAW PLAYER INVENTORY
        for(int i = 0; i < gp.player.inventory.size(); i++){
            spriteBatch.draw(gp.player.inventory.get(i).down1,slotX,slotY,gp.tileSize,gp.tileSize);
            //display amount
            if (gp.player.inventory.get(i).amount>1){

                int amountX;
                int amountY;

                String s = ""+gp.player.inventory.get(i).amount;
                amountX = getXforAlignToRightText(font,s,slotX+44);
                amountY = slotY+gp.tileSize;

                /*//shadow of number
                g2d.setColor(new Color(60,60,60));
                g2d.drawString(s,amountX+2,amountY+2);*/

                //number
               font.draw(spriteBatch, s, amountX, amountY);


            }


            slotX += gp.tileSize + gp.tileSize/2-8;

        }

        //CURSOR
        int cursorX = slotXStart + slotCol*(gp.tileSize+gp.tileSize/2-8);
        int cursorY = slotYStart + slotRow*gp.tileSize;
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;


        //DRAW CURSOR
        Pixmap pixmap = new Pixmap(cursorWidth, cursorHeight, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawRectangle(0, 0, cursorWidth, cursorHeight); // Draw rectangle

        // Convert Pixmap to Texture
        Texture cursorTexture = new Texture(pixmap);
        pixmap.dispose();

        spriteBatch.draw(cursorTexture, cursorX, cursorY);

        int itemIndex = getItemIndexOnSlot();
        if (itemIndex < gp.player.inventory.size()){
            font.setColor(Color.WHITE);
            font.draw(spriteBatch, gp.player.inventory.get(itemIndex).name,cursorX,cursorY+gp.tileSize+20);
        }


    }

    public int getItemIndexOnSlot(){
        int index = slotCol;
        return index;
    }

    private void drawHealthBar() {
        int x = gp.tileSize/2;
        int y = gp.tileSize;

        if(gp.player.currentLife == 3){
            spriteBatch.draw(health3, x, y, gp.tileSize, gp.tileSize*4);
        }

        if(gp.player.currentLife == 2){
            spriteBatch.draw(health2, x, y, gp.tileSize, gp.tileSize*4);
        }

        if(gp.player.currentLife == 1){
            spriteBatch.draw(health1, x, y, gp.tileSize, gp.tileSize*4);
        }

        if(gp.player.currentLife <= 0){
            spriteBatch.draw(health1, x, y, gp.tileSize, gp.tileSize*4);
        }
    }

    private void drawTitleScreen() {
        font.getData().setScale(2f);
        String text = "Spiritstead";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight - gp.tileSize*2;

        //SHADOW
        font.setColor(Color.GRAY);
        font.draw(spriteBatch, text, x + 3, y - 3);
        font.setColor(Color.WHITE);
        font.draw(spriteBatch, text, x, y);

        //Image
        x = gp.screenWidth / 2 - gp.tileSize;
        y -= gp.tileSize*3;
        spriteBatch.draw(titleScreenImage, x, y, gp.tileSize*2, gp.tileSize*2);

        //MENU
        font.getData().setScale(1.5f);
        text = "New Game";
        x = getXforCenteredText(text);
        y -= gp.tileSize;
        font.draw(spriteBatch, text, x, y);
        if(commandNum == 0){
            font.draw(spriteBatch, ">", x - gp.tileSize, y);
        }

        text = "Load Game";
        x = getXforCenteredText(text);
        y -= gp.tileSize;
        font.draw(spriteBatch, text, x, y);
        if(commandNum == 1){
            font.draw(spriteBatch, ">", x - gp.tileSize, y);
        }

        text = "Quit";
        x = getXforCenteredText(text);
        y -= gp.tileSize;
        font.draw(spriteBatch, text, x, y);
        if(commandNum == 2){
            font.draw(spriteBatch, ">", x - gp.tileSize, y);
        }
    }

    private void drawDialogScreen() {
        //Window
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - gp.tileSize*4;
        int height = gp.tileSize*5;

        drawSubWindow(x,y,width,height);
        font = new BitmapFont();
        font.getData().setScale(1.5f); // Adjust font size as needed
        font.setColor(Color.WHITE);

        //DIALOGUE PAGINATION
        if (npc.dialogues[npc.dialogueSetNumber][npc.dialogueIndex]!=null){

//            currentDialogue = npc.dialogues[npc.dialogueSetNumber][npc.dialogueIndex];
           char characters[] = npc.dialogues[npc.dialogueSetNumber][npc.dialogueIndex].toCharArray();
           if (charIndex<characters.length) {
               String s = String.valueOf(characters[charIndex]);
               combinedText += s;
               currentDialogue = combinedText;
               gp.playSoundEffect(4);
               charIndex++;
           }
            if (gp.keyHandler.spacePressed){
                //reset letter by letter
                charIndex = 0;
                combinedText = "";

                if (gp.gameState == gp.dialogueState){
                    npc.dialogueIndex++;
                    gp.keyHandler.spacePressed = false;
                }
            }
        }else{
            npc.dialogueIndex = 0;
            if (gp.gameState == gp.dialogueState){
                gp.gameState = gp.playState;
            }
        }
        font.draw(spriteBatch, currentDialogue, x + gp.tileSize, y + height - gp.tileSize);
    }

    public void drawSubWindow(int x, int y, int width, int height){
        Texture rectangleTexture;
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);

        // Draw filled rectangle (semi-transparent black)
        pixmap.setColor(new Color(0, 0, 0, 0.8f));
        pixmap.fillRectangle(0, 0, width, height);

        // Draw border (white)
        pixmap.setColor(Color.WHITE);
        pixmap.drawRectangle(3, 3, width - 6, height - 6);

        // Convert Pixmap to Texture
        rectangleTexture = new Texture(pixmap);
        pixmap.dispose();
        spriteBatch.draw(rectangleTexture, x, y);
    }

    private void drawPauseScreen() {
        //Frame
        final int frameX = gp.tileSize*2;
        final int frameY = gp.tileSize*2;
        final int frameWidth = gp.tileSize*10;
        final int frameHeight = gp.tileSize*5;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        //Text
        font.setColor(Color.WHITE);
//        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 22));

        int textX = frameX + gp.tileSize/2;
        int textY = frameY + gp.tileSize/2;
        final int lineHeight = gp.tileSize;//font height

        //Names
        font.draw(spriteBatch, "Level", textX, textY);
        textY += lineHeight;
        font.draw(spriteBatch, "Attack", textX, textY);
        textY += lineHeight;
        font.draw(spriteBatch, "XP", textX, textY);
        textY += lineHeight;
        font.draw(spriteBatch, "Weapon", textX, textY);
        textY += lineHeight;
        font.draw(spriteBatch, "Defense", textX, textY);

        //VALUES
        int tailX = frameX + frameWidth - 30;
        textY = frameY + gp.tileSize/2;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(font,value,tailX);
        font.draw(spriteBatch, value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXforAlignToRightText(font,value,tailX);
        font.draw(spriteBatch, value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXforAlignToRightText(font,value,tailX);
        font.draw(spriteBatch, value, textX, textY);
        textY += lineHeight;

        value = gp.player.currentWeapon.name;
        textX = getXforAlignToRightText(font,value,tailX);
        font.draw(spriteBatch, value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXforAlignToRightText(font,value,tailX);
        font.draw(spriteBatch, value, textX, textY);
        textY += lineHeight;

    }

    public int getXforCenteredText(String text){
        GlyphLayout layout = new GlyphLayout(font, text);
        return (int) (gp.screenWidth / 2 - layout.width / 2);
    }

    public int getXforAlignToRightText(BitmapFont font, String text, int tailX) {
        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, text);
        int length = (int) layout.width;
        return tailX - length;
    }

}

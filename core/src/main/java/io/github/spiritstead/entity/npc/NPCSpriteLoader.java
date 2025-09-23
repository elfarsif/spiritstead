package io.github.spiritstead.entity.npc;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class NPCSpriteLoader {
    NPC npc;

    public NPCSpriteLoader(NPC npc) {
        this.npc = npc;
    }

    public void load() {
        npc.setUp1(new Sprite(new Texture("player/up1.png")));
        npc.setUp2(new Sprite(new Texture("player/up2.png")));
        npc.setDown1(new Sprite(new Texture("player/down1.png")));
        npc.setDown2(new Sprite(new Texture("player/down2.png")));
        npc.setLeft1(new Sprite(new Texture("player/left1.png")));
        npc.setLeft2(new Sprite(new Texture("player/left2.png")));
        npc.setRight1(new Sprite(new Texture("player/right1.png")));
        npc.setRight2(new Sprite(new Texture("player/right2.png")));
    }
}

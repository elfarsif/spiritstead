package io.github.spiritstead.entity;

public class EntityAnimator {
    Entity entity;

    public EntityAnimator(Entity entity) {
        this.entity = entity;
    }

    public void update() {
        entity.spriteCounter++;
        if (entity.spriteCounter > 15) {
            if (entity.spriteNum == 1) {
                entity.spriteNum = 2;
            } else if (entity.spriteNum == 2) {
                entity.spriteNum = 1;
            }
            entity.spriteCounter = 0;
        }
    }

}

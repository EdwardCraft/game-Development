package com.mygdx.gamedev.chie.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.gamedev.chie.Level;
import com.mygdx.gamedev.chie.util.Assets;
import com.mygdx.gamedev.chie.util.Constants;
import com.mygdx.gamedev.chie.util.Enums.Facing;

/**
 * Created by PEDRO on 12/04/2016.
 */
public class Bullet {

    private final Facing facing;
    private boolean active;
    private Vector2 position;
    private Level level;

    public Bullet(Level level, Vector2 position, Facing facing){
        this.position = position;
        this.facing = facing;
        this.level = level;
        active = true;
    }

    public void update(float delta){
        switch (facing){
            case LEFT: position.x -= delta * Constants.BULLET_MOVE_SPEED; break;
            case RIGHT: position.x += delta * Constants.BULLET_MOVE_SPEED;  break;
        }



        final float worldWidth = level.getViewport().getWorldWidth();
        final float cameraX = level.getViewport().getCamera().position.x;

        if(position.x < cameraX - worldWidth / 2 || position.x > cameraX + worldWidth / 2){
            active = false;
        }

        collisonEnemy();
    }

    public void collisonEnemy(){
        for(Enemy enemy : level.getEnemies()){
            if(bulletBounds().overlaps(enemy.enemyBounds())){
                level.spawnExplosions(position);
                active = false;
                enemy.setHealth(enemy.getHealth()-1);
            }
        }
    }

    public void render(SpriteBatch batch, ShapeRenderer renderer){


        TextureRegion region = Assets.instance.bulletAssets.bullet;

        if(facing == Facing.RIGHT){
            region = Assets.instance.bulletAssets.bullet;
        }else{
            region = Assets.instance.bulletAssets.bulletLeft;
        }
        batch.draw(
                region.getTexture(),
                position.x - Constants.CHIE_EYE_POSITION.x,
                position.y - Constants.CHIE_EYE_POSITION.y,
                0,
                0,
                25,
                23,
                1,
                1,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false
        );

    }

    public boolean getActive(){ return active;}

    public Rectangle bulletBounds(){
        return new Rectangle(
                position.x  - 15,
                position.y - 30,
                25 - 3,
                23 - 3
        );
    }

}




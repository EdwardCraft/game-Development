package com.mygdx.gamedev.chie.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.gamedev.chie.util.Assets;
import com.mygdx.gamedev.chie.util.Constants;
import com.mygdx.gamedev.chie.util.Enums.Facing;
import com.mygdx.gamedev.chie.util.Utils;

import org.omg.CORBA.TIMEOUT;

/**
 * Created by PEDRO on 11/04/2016.
 */
public class Enemy {

    private final Platform platform;
    public Vector2 position;
    private Facing facing;
    private long startTine;
    private int health;
    private final float randomPhase;

    public Enemy(Platform platform){
        this.platform = platform;
        facing = Facing.RIGHT;
        position = new Vector2(platform.getLeft(),
                platform.getTop() + Constants.ENEMY_CENTER.y);
        startTine = TimeUtils.nanoTime();
        health = Constants.ENEMY_HEALTH;
        randomPhase = MathUtils.random();
    }

    public void update(float delta){

        switch (facing){
            case LEFT: position.x -= Constants.ENEMY_MOVEMENT_SPEED * delta;
                break;
            case RIGHT: position.x += Constants.ENEMY_MOVEMENT_SPEED * delta;
                break;
        }

        if(position.x > platform.getRight()){
            position.x = platform.getRight();
            facing = Facing.LEFT;
        }else if(position.x < platform.getLeft()){
            position.x = platform.getLeft();
            facing = facing.RIGHT;
        }

        final float elpasedTime = Utils.secondsSince(startTine);
        final float bobMultiplier = 1 + MathUtils.sin(MathUtils.PI2 * (elpasedTime / Constants.ENEMY_BOB_PERIOD) + randomPhase);
        position.y = platform.getTop() + Constants.ENEMY_CENTER.y + Constants.ENEMY_BOB_AMPLITUDE * bobMultiplier;

    }

    public void render(SpriteBatch batch){


        final TextureRegion region = Assets.instance.enemyAssets.enemy;
        Utils.drawTextureRegion(batch, region, position, Constants.ENEMY_CENTER);
    }

    public Rectangle enemyBounds(){
        return new Rectangle(
                position.x - Constants.ENEMY_COLLISION_RADIUS,
                position.y - Constants.ENEMY_COLLISION_RADIUS,
                2 * Constants.ENEMY_COLLISION_RADIUS,
                2 * Constants.ENEMY_COLLISION_RADIUS
        );
    }

    public int getHealth(){return health;}
    public void setHealth(int health){this.health = health; }


}

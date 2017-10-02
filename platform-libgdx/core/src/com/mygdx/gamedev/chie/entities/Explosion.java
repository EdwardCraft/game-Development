package com.mygdx.gamedev.chie.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.gamedev.chie.util.Assets;
import com.mygdx.gamedev.chie.util.Constants;
import com.mygdx.gamedev.chie.util.Utils;

/**
 * Created by PEDRO on 12/04/2016.
 */
public class Explosion {

    private Vector2 position;
    private final long statTime;
    public float offset = 0;
    public Explosion(Vector2 position){
        this.position = position;
        statTime = TimeUtils.nanoTime();
    }


    public void update(){

    }

    public void render(SpriteBatch batch){
        if(!isFinished() && !yetToStart()){
            Utils.drawTextureRegion(
                    batch,
                    Assets.instance.explosionAssets.explosion.getKeyFrame(Utils.secondsSince(statTime)),
                    position.x - Constants.EXPLOSION_CENTER.x,
                    position.y - Constants.EXPLOSION_CENTER.y
            );
        }

    }

    public boolean yetToStart(){
        return Utils.secondsSince(statTime) - offset < 0;
    }

    public boolean isFinished(){
        //Animation.isAnimationFinished() to find out if the explosion is done
        final float elapseTime = Utils.secondsSince(statTime) - offset;
        return Assets.instance.explosionAssets.explosion.isAnimationFinished(elapseTime);
    }


}

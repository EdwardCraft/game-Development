package com.mygdx.gamedev.chie.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.particles.batches.BillboardParticleBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.gamedev.chie.util.Assets;
import com.mygdx.gamedev.chie.util.Constants;
import com.mygdx.gamedev.chie.util.Utils;


/**
 * Created by PEDRO on 13/04/2016.
 */
public class ExitPortal {

    public final static String TAG = ExitPortal.class.getName();
    public final Vector2 postition;
    private final long startTime;

    public ExitPortal(Vector2 postition){
        this.postition = postition;
        startTime = TimeUtils.nanoTime();
    }

    public void render(SpriteBatch batch){
        final float elapseTime = Utils.secondsSince(startTime);
        final TextureRegion region = Assets.instance.exitProtalAssets.exitPortal.getKeyFrame(elapseTime);
        Utils.drawTextureRegion(batch, region, postition, Constants.EXIT_PORTAL_CENTER);
    }



}



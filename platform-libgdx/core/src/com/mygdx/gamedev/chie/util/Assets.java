package com.mygdx.gamedev.chie.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.gamedev.chie.entities.Platform;

import javax.naming.CompositeName;
import javax.swing.table.AbstractTableModel;


/**
 * Created by PEDRO on 11/04/2016.
 */
public class Assets implements Disposable, AssetErrorListener {

    public static final String TAG = Assets.class.getName();
    public static final Assets instance = new Assets();
    public ChieAssets chiAssets;
    public PlatformAssets platformAssets;
    private AssetManager assetManager;
    public EnemyAssets enemyAssets;
    public BulletAssets bulletAssets;
    public ExplosionAssets explosionAssets;
    public PowerupAssets powerupAssets;
    public ExitProtalAssets exitProtalAssets;
    public OnscreenControlsAssets onscreenControlsAssets;

    public Assets(){

    }

    public void init(AssetManager assetManager){
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS_1 , TextureAtlas.class);
        assetManager.finishLoading();

        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_1);
        chiAssets = new ChieAssets(atlas);
        platformAssets = new PlatformAssets(atlas);
        enemyAssets = new EnemyAssets(atlas);
        bulletAssets = new BulletAssets(atlas);
        explosionAssets = new ExplosionAssets(atlas);
        powerupAssets = new PowerupAssets(atlas);
        exitProtalAssets = new ExitProtalAssets(atlas);
        onscreenControlsAssets = new OnscreenControlsAssets(atlas);
    }





    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
     Gdx.app.error(TAG, "Couldm't load asset: "+ asset.fileName, throwable);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }

    public class ChieAssets{
        public final AtlasRegion standingRight;
        public final AtlasRegion standingLeft;
        public final AtlasRegion jumpingRight;
        public final AtlasRegion jumpingLeft;
        public final AtlasRegion walkingLeft;
        public final AtlasRegion walkingRight;


        public Animation walkingLeftAnimation;
        public Animation walkingRightAnimation;
        public Animation fireRightAnimation;
        public Animation fireLeftAnimation;

        public ChieAssets(TextureAtlas atlas){
            standingRight = atlas.findRegion(Constants.STANDING_RIGHT);
            standingLeft = atlas.findRegion(Constants.STANDING_LEFT);
            jumpingLeft = atlas.findRegion(Constants.JUMPING_LEFT);
            jumpingRight = atlas.findRegion(Constants.JUMPING_RIGHT);
            walkingLeft = atlas.findRegion(Constants.WALKING_LEFT);
            walkingRight = atlas.findRegion(Constants.WALKING_RIGHT);

            Array<AtlasRegion> walkingLeftFrames = new Array<AtlasRegion>();
            walkingLeftFrames.add(atlas.findRegion(Constants.WALKING_LEFT));
            walkingLeftFrames.add(atlas.findRegion(Constants.WALKING_LEFT_3));
            walkingLeftFrames.add(atlas.findRegion(Constants.WALKING_LEFT_4));
            walkingLeftFrames.add(atlas.findRegion(Constants.WALKING_LEFT_5));
            walkingLeftFrames.add(atlas.findRegion(Constants.WALKING_LEFT_6));
            walkingLeftFrames.add(atlas.findRegion(Constants.WALKING_LEFT_7));
            walkingLeftFrames.add(atlas.findRegion(Constants.WALKING_LEFT_8));

            walkingLeftAnimation = new Animation(
                    Constants.WALK_LOOP_DURATION,
                    walkingLeftFrames,
                    Animation.PlayMode.LOOP);

            Array<AtlasRegion> walkingRightFrames = new Array<AtlasRegion>();
            walkingRightFrames.add(atlas.findRegion(Constants.WALKING_RIGHT));
            walkingRightFrames.add(atlas.findRegion(Constants.WALKING_RIGHT_3));
            walkingRightFrames.add(atlas.findRegion(Constants.WALKING_RIGHT_4));
            walkingRightFrames.add(atlas.findRegion(Constants.WALKING_RIGHT_5));
            walkingRightFrames.add(atlas.findRegion(Constants.WALKING_RIGHT_6));
            walkingRightFrames.add(atlas.findRegion(Constants.WALKING_RIGHT_7));
            walkingRightFrames.add(atlas.findRegion(Constants.WALKING_RIGHT_8));

            walkingRightAnimation = new Animation(
                    Constants.WALK_LOOP_DURATION,
                    walkingRightFrames,
                    Animation.PlayMode.LOOP);

            Array<AtlasRegion> fireRightFrames = new Array<AtlasRegion>();
            fireRightFrames.add(atlas.findRegion(Constants.FIRE_1_RIGHT));
            fireRightFrames.add(atlas.findRegion(Constants.FIRE_2_RIGHT));
            fireRightFrames.add(atlas.findRegion(Constants.FIRE_3_RIGHT));
            fireRightFrames.add(atlas.findRegion(Constants.FIRE_4_RIGHT));
            fireRightFrames.add(atlas.findRegion(Constants.FIRE_4_RIGHT));
            fireRightFrames.add(atlas.findRegion(Constants.FIRE_6_RIGHT));
            fireRightFrames.add(atlas.findRegion(Constants.FIRE_7_RIGHT));
            fireRightFrames.add(atlas.findRegion(Constants.FIRE_8_RIGHT));
            fireRightFrames.add(atlas.findRegion(Constants.FIRE_9_RIGHT));
            fireRightFrames.add(atlas.findRegion(Constants.FIRE_10_RIGHT));
            fireRightFrames.add(atlas.findRegion(Constants.FIRE_11_RIGHT));
            fireRightFrames.add(atlas.findRegion(Constants.FIRE_12_RIGHT));
            fireRightFrames.add(atlas.findRegion(Constants.FIRE_13_RIGHT));
            fireRightFrames.add(atlas.findRegion(Constants.FIRE_6_RIGHT));
            fireRightFrames.add(atlas.findRegion(Constants.FIRE_5_RIGHT));
            fireRightFrames.add(atlas.findRegion(Constants.FIRE_4_RIGHT));
            fireRightFrames.add(atlas.findRegion(Constants.FIRE_3_RIGHT));
            fireRightFrames.add(atlas.findRegion(Constants.FIRE_2_RIGHT));
            fireRightFrames.add(atlas.findRegion(Constants.FIRE_1_RIGHT));
            fireRightFrames.add(atlas.findRegion(Constants.STANDING_RIGHT));

            fireRightAnimation = new Animation(
                    Constants.FIRE_ANIMATION_DURATION,
                    fireRightFrames,
                    Animation.PlayMode.NORMAL
            );

            Array<AtlasRegion> fireLeftFrames = new Array<AtlasRegion>();
            fireLeftFrames.add(atlas.findRegion(Constants.FIRE_1_LEFT));
            fireLeftFrames.add(atlas.findRegion(Constants.FIRE_2_LEFT));
            fireLeftFrames.add(atlas.findRegion(Constants.FIRE_3_LEFT));
            fireLeftFrames.add(atlas.findRegion(Constants.FIRE_4_LEFT));
            fireLeftFrames.add(atlas.findRegion(Constants.FIRE_5_LEFT));
            fireLeftFrames.add(atlas.findRegion(Constants.FIRE_6_LEFT));
            fireLeftFrames.add(atlas.findRegion(Constants.FIRE_7_LEFT));
            fireLeftFrames.add(atlas.findRegion(Constants.FIRE_8_LEFT));
            fireLeftFrames.add(atlas.findRegion(Constants.FIRE_9_LEFT));
            fireLeftFrames.add(atlas.findRegion(Constants.FIRE_10_LEFT));
            fireLeftFrames.add(atlas.findRegion(Constants.FIRE_11_LEFT));
            fireLeftFrames.add(atlas.findRegion(Constants.FIRE_12_LEFT));
            fireLeftFrames.add(atlas.findRegion(Constants.FIRE_13_LEFT));
            fireLeftFrames.add(atlas.findRegion(Constants.FIRE_6_LEFT));
            fireLeftFrames.add(atlas.findRegion(Constants.FIRE_5_LEFT));
            fireLeftFrames.add(atlas.findRegion(Constants.FIRE_4_LEFT));
            fireLeftFrames.add(atlas.findRegion(Constants.FIRE_3_LEFT));
            fireLeftFrames.add(atlas.findRegion(Constants.FIRE_2_LEFT));
            fireLeftFrames.add(atlas.findRegion(Constants.FIRE_1_LEFT));
            fireLeftFrames.add(atlas.findRegion(Constants.STANDING_LEFT));

            fireLeftAnimation = new Animation(
                    Constants.FIRE_ANIMATION_DURATION,
                    fireLeftFrames,
                    Animation.PlayMode.NORMAL
            );

        }
    }

    public class PlatformAssets{
        public final NinePatch platformNimePatch;

        public PlatformAssets(TextureAtlas atlas){
            AtlasRegion region = atlas.findRegion(Constants.PLATFORM_SPRITE);
            int edge = Constants.PLATFORM_EDGE;
            platformNimePatch = new NinePatch(region, edge, edge, edge, edge);
        }
    }

    public class EnemyAssets{
        public final AtlasRegion enemy;

        public EnemyAssets(TextureAtlas atlas){
            enemy = atlas.findRegion(Constants.ENEMy_SPRITE);
        }
    }

    public class BulletAssets{
        public final AtlasRegion bullet;
        public final AtlasRegion bulletLeft;
        public BulletAssets(TextureAtlas atlas){
            bullet = atlas.findRegion(Constants.BULLET_SPRITE);
            bulletLeft = atlas.findRegion(Constants.BULLET_SPRITE_LEFT);
        }
    }

    public class ExplosionAssets{
        public final Animation explosion;

        public ExplosionAssets(TextureAtlas atlas){
            Array<AtlasRegion> explosionRegions = new Array<AtlasRegion>();
            explosionRegions.add(atlas.findRegion(Constants.EXPLOSION_LARGE));
            explosionRegions.add(atlas.findRegion(Constants.EXPLOSION_MEDIUM));
            explosionRegions.add(atlas.findRegion(Constants.EXPLOSION_SMALL));
            explosion = new Animation(Constants.EXPLOSION_DURATION / explosionRegions.size,
                    explosionRegions, Animation.PlayMode.NORMAL);
        }
    }

    public class PowerupAssets{
        public final AtlasRegion powerup;

        public PowerupAssets(TextureAtlas atlas){
            powerup = atlas.findRegion(Constants.POWERUP_SPRITE);
        }
    }

    public class ExitProtalAssets{
        public final Animation exitPortal;

        public ExitProtalAssets(TextureAtlas atlas){

            final AtlasRegion exitPortal1 = atlas.findRegion(Constants.EXIT_PORTAL_SPRITE_1);
            final AtlasRegion exitPortal2 = atlas.findRegion(Constants.EXIT_PORTAL_SPRITE_2);
            final AtlasRegion exitPortal3 = atlas.findRegion(Constants.EXIT_PORTAL_SPRITE_3);
            final AtlasRegion exitPortal4 = atlas.findRegion(Constants.EXIT_PORTAL_SPRITE_4);
            final AtlasRegion exitPortal5 = atlas.findRegion(Constants.EXIT_PORTAL_SPRITE_5);
            final AtlasRegion exitPortal6 = atlas.findRegion(Constants.EXIT_PORTAL_SPRITE_6);
            Array<AtlasRegion> exitPortalFrames = new Array<AtlasRegion>();

            exitPortalFrames.addAll(exitPortal1, exitPortal2, exitPortal3, exitPortal4, exitPortal5, exitPortal6);
            exitPortal = new Animation(Constants.EXIT_PORTAL_FRAME_DURATION, exitPortalFrames, Animation.PlayMode.LOOP);
        }
    }

    public class OnscreenControlsAssets {

        public final AtlasRegion moveRight;
        public final AtlasRegion moveLeft;
        public final AtlasRegion shoot;
        public final AtlasRegion jump;

        public OnscreenControlsAssets(TextureAtlas atlas) {
            moveRight = atlas.findRegion(Constants.MOVE_RIGHT_BUTTON);
            moveLeft = atlas.findRegion(Constants.MOVE_LEFT_BUTTON);
            shoot = atlas.findRegion(Constants.SHOOT_BUTTON);
            jump = atlas.findRegion(Constants.JUMP_BUTTON);
        }


    }

}

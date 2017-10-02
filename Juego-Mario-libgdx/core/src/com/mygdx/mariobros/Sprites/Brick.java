package com.mygdx.mariobros.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.mariobros.MarioBros;
import com.mygdx.mariobros.Scenes.Hud;
import com.mygdx.mariobros.Util.Constants;

/**
 * Created by PEDRO on 07/02/2016.
 */
public class Brick extends interactiveTileObject {



    public Brick(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(Constants.BRICK_BIT);
    }

    public void onHeadHit() {

        Gdx.app.log("Brick", "Collision");
        setCategoryFilter(Constants.DESTROYED_BIT);
        getCell().setTile(null);
        Hud.addScore(100);
        MarioBros.manager.get(Constants.SOUND_BREAK_BLOCK, Sound.class).play();
    }
}

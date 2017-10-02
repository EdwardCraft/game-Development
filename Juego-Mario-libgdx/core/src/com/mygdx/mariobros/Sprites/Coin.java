package com.mygdx.mariobros.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.mariobros.MarioBros;
import com.mygdx.mariobros.Scenes.Hud;
import com.mygdx.mariobros.Util.Constants;

import java.util.Map;

/**
 * Created by PEDRO on 07/02/2016.
 */
public class Coin extends interactiveTileObject {

    private static TiledMapTileSet tileset;
    public Coin(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
        tileset = map.getTileSets().getTileSet("tileset_gutter");
        fixture.setUserData(this);
        setCategoryFilter(Constants.COIN_BIT);
    }

    public void onHeadHit() {
        Gdx.app.log("Coin", "Collision");
        if(getCell().getTile().getId() == Constants.BLANK_COIN){
            MarioBros.manager.get(Constants.SOUND_COIN, Sound.class).play();
        }else{

        }
        getCell().setTile(tileset.getTile(Constants.BLANK_COIN));
        Hud.addScore(500);
    }


}

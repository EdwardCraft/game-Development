package com.mygdx.mariobros.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.mariobros.Util.Constants;



/**
 * Created by PEDRO on 07/02/2016.
 */
public abstract class interactiveTileObject {

    private World world;
    private TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;
    BodyDef bodyDef;
    FixtureDef fixtureDef;
    PolygonShape polygonShape;
    protected Fixture fixture;

    public  interactiveTileObject(World world, TiledMap map, Rectangle bounds ){
        this.world = world;
        this.map = map;
        this.bounds = bounds;
        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();
        polygonShape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX() + bounds.getWidth() / 2)/ Constants.PIXELS_PER_METER,
                (bounds.getY() + bounds.getHeight() / 2) / Constants.PIXELS_PER_METER);
        body = world.createBody(bodyDef);
        polygonShape.setAsBox(bounds.getWidth() / 2 / Constants.PIXELS_PER_METER,
                bounds.getHeight() / 2 / Constants.PIXELS_PER_METER);
        fixtureDef.shape = polygonShape;
        fixture = body.createFixture(fixtureDef);

    }

    public abstract void onHeadHit();
    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(1);
        return  layer.getCell((int)(body.getPosition().x * Constants.PIXELS_PER_METER / 16),
                (int)(body.getPosition().y * Constants.PIXELS_PER_METER / 16));
    }

}

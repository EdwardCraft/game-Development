package com.mygdx.mariobros.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.mariobros.Sprites.Brick;
import com.mygdx.mariobros.Sprites.Coin;
import com.mygdx.mariobros.Util.Constants;

/**
 * Created by PEDRO on 07/02/2016.
 */
public class Box2DWolrdCreator {

    BodyDef bodyDef;
    PolygonShape shape;
    FixtureDef fixtureDef;
    Body body;

    public Box2DWolrdCreator(World world, TiledMap map){

         bodyDef = new BodyDef();
         shape = new PolygonShape();
         fixtureDef = new FixtureDef();

        //create ground bodies/fixtures
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / Constants.PIXELS_PER_METER,
                    (rect.getY() + rect.getHeight() / 2) / Constants.PIXELS_PER_METER);
            body = world.createBody(bodyDef);
            shape.setAsBox(rect.getWidth() / 2 / Constants.PIXELS_PER_METER,
                    rect.getHeight() / 2 / Constants.PIXELS_PER_METER);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);

        }
        //create pipe bodies/fixtues
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / Constants.PIXELS_PER_METER,
                    (rect.getY() + rect.getHeight() / 2) / Constants.PIXELS_PER_METER);
            body = world.createBody(bodyDef);
            shape.setAsBox(rect.getWidth() / 2 / Constants.PIXELS_PER_METER,
                    rect.getHeight() / 2 / Constants.PIXELS_PER_METER);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);

        }
        //create brick bodies /fixtures
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            new Brick(world, map, rect);

        }
        //create coins/fixtures
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            new Coin(world, map,rect);

        }
    }

}

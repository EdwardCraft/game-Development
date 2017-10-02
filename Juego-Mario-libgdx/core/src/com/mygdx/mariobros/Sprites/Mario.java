package com.mygdx.mariobros.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.mariobros.Screens.PlayScreen;
import com.mygdx.mariobros.Util.Constants;
import com.mygdx.mariobros.Util.Enums.Direction;
import com.mygdx.mariobros.Util.Enums.State;


/**
 * Created by PEDRO on 05/02/2016.
 */
public class Mario  extends Sprite{
    public static final String TAG = Mario.class.getName();
    public World world;
    public Body  body;
    BodyDef bodyDef;
    FixtureDef fixtureDef;

    private TextureRegion foxidle;
    public State currentState;
    public State previousState;
    private Direction direction;
    private float startTimer;

    private Animation foxRun;
    private Animation foxJump;


    public Mario(World world, PlayScreen playScreen){
        super(playScreen.getAtlas().findRegion("FOX_MOVING"));
        this.world = world;

        currentState = State.STANDING;
        previousState = State.STANDING;
        startTimer = 0;
        direction = Direction.RIGHT;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(getTexture(), 36, 10, 36, 36));
        frames.add(new TextureRegion(getTexture(), 80, 10, 36, 36));
        frames.add(new TextureRegion(getTexture(), 116, 10, 36, 36));
        foxRun = new Animation(Constants.FOX_FRAME_ANIMATION_DURATION, frames, Animation.PlayMode.LOOP);
        frames.clear();

        for(int i= 1; i < 5;i++){
            frames.add(new TextureRegion(getTexture(), i * 36, 0, 36, 36));
        }

        foxJump = new Animation(Constants.FOX_FRAME_ANIMATION_DURATION, frames);


        foxidle = new TextureRegion(getTexture(), 0, 8, 36, 36);
        defineMario();
        setBounds(0, 0, 36 / Constants.PIXELS_PER_METER, 36 / Constants.PIXELS_PER_METER);
        setRegion(foxidle);
    }


    public void update(float delta){
        setPosition(
                body.getPosition().x - getWidth() / 2,
                body.getPosition().y - getHeight() / 2);

        setRegion(getFrame(delta));

    }

    public  TextureRegion getFrame(float delta){
        currentState = getState();
        TextureRegion region;
        switch(currentState){
            case JUMPING: region = foxJump.getKeyFrame(startTimer); break;
            case RUNNING: region = foxRun.getKeyFrame(startTimer); break;
            case FALLING:
            case STANDING:
            default: region = foxidle; break;
        }
        if((body.getLinearVelocity().x < 0 || direction == Direction.LEFT) && !region.isFlipX()){
            region.flip(true,false);
            direction = Direction.LEFT;
        }else if((body.getLinearVelocity().x > 0 || direction == Direction.RIGHT) && region.isFlipX()){
            region.flip(true, false);
            direction = Direction.RIGHT;
        }
        startTimer = currentState == previousState ? startTimer + delta : 0;
        previousState = currentState;

        return region;
    }



    public void defineMario(){
        bodyDef = new BodyDef();
        bodyDef.position.set(32 / Constants.PIXELS_PER_METER,
                32 / Constants.PIXELS_PER_METER);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        fixtureDef = new FixtureDef();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(12f / Constants.PIXELS_PER_METER);
        fixtureDef.filter.categoryBits = Constants.MARIO_BIT;
        //what can mario collide wit
        fixtureDef.filter.maskBits = Constants.DEFAULT_BIT  | Constants.COIN_BIT | Constants.BRICK_BIT;

        fixtureDef.shape = circleShape;
        body.createFixture(fixtureDef);
        circleShape.dispose();

        EdgeShape head = new EdgeShape();
        head.set(new Vector2( -2 / Constants.PIXELS_PER_METER, 12 / Constants.PIXELS_PER_METER ),
                new Vector2( 2 / Constants.PIXELS_PER_METER, 12 / Constants.PIXELS_PER_METER));
        fixtureDef.shape = head;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef).setUserData("head");
        head.dispose();



    }

    float ConvertToBox(float x){
        return x * Constants.PIXELS_PER_METER;
    }

    public State getState(){
        if(body.getLinearVelocity().y > 0 || (body.getLinearVelocity().y < 0 && previousState == State.JUMPING)){
            return State.JUMPING;
        }else if(body.getLinearVelocity().y < 0){
            return State.FALLING;
        }else if(body.getLinearVelocity().x != 0){
            return State.RUNNING;
        }else{
            return State.STANDING;
        }
    }


}

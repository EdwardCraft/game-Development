package com.mygdx.gamedev.chie.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.gamedev.chie.Level;
import com.mygdx.gamedev.chie.util.Assets;
import com.mygdx.gamedev.chie.util.Constants;
import com.mygdx.gamedev.chie.util.Enums.WalkState;
import com.mygdx.gamedev.chie.util.Enums.JumpState;
import com.mygdx.gamedev.chie.util.Enums.Facing;
import com.sun.jndi.cosnaming.CNCtx;

import org.omg.CORBA.SystemException;
import org.omg.CORBA.TIMEOUT;


/**
 * Created by PEDRO on 11/04/2016.
 */
public class Chie {
    public final static String TAG = Chie.class.getName();

    public boolean jumpButtonPressed;
    public boolean leftButtonPressed;
    public boolean rightButtonPressed;

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 lastFramePosition;
    private Vector2 spawnLocation;

    private Facing facing;
    private JumpState jumpState;
    private WalkState walkState;

    private long jumpStartRime;
    private long walkStartTime;
    private long fireStartTime;
    private Level level;
    private boolean fire;
    private  float fireTimeSeconds;
    private int ammo;
    private int lives;

    public Chie(Vector2 spawnLocation, Level level){
        this.spawnLocation = spawnLocation;
        this.level = level;
        position = new Vector2();
        lastFramePosition = new Vector2();
        velocity = new Vector2();
        init();
    }

    private void init(){

        ammo = Constants.INITIAL_AMMO;
        lives = Constants.INITIAL_LIVES;
        respawn();
    }

    private void respawn(){
        position.set(spawnLocation);
        lastFramePosition.set(spawnLocation);
        velocity.setZero();
        jumpState = JumpState.FALLING;
        facing = Facing.RIGHT;
        walkState = WalkState.STANDING;
        fire = false;
    }

    public  void update(float delta, Array<Platform> platforms){
        lastFramePosition.set(position);
        // Accelerate Chie down
        //Multiple delta by the acceleration due to gravity and subtract it form Chie's vertical velocity
        velocity.y -= delta * Constants.GRAVITY;
        position.mulAdd(velocity, delta);

        if(position.y < Constants.KILL_PLANE){
            lives--;
            respawn();
        }

        if(jumpState != JumpState.JUMPING){
            if(jumpState != JumpState.RECOILING){
                jumpState = JumpState.FALLING;
            }
            collisionPlatform(platforms);
        }

        // removing the ground
       /* if(position.y - Constants.CHIE_SIZE_HEIGHT < 0){
            jumpState = JumpState.JUMPING.GROUNDED;
            position.y = Constants.CHIE_SIZE_HEIGHT;
            velocity.y = 0;
        }*/
        collisionEnemy();
        collisionPowerup();

        if(!fire){
            if(Gdx.input.isKeyPressed(Input.Keys.UP) || jumpButtonPressed){
                switch(jumpState){
                    case GROUNDED: startJump(); break;
                    case JUMPING: continueJump(); break;
                    case FALLING:; break;
                }
            }else{
                endJump();
            }

            if(jumpState != JumpState.RECOILING){
                if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || leftButtonPressed){
                    moveLeft(delta);
                }else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || rightButtonPressed){
                    moveRight(delta);
                }else{
                    walkState = WalkState.STANDING;
                }
            }

        }


        if(walkState == WalkState.STANDING && fire == false){
            if(Gdx.input.isKeyJustPressed(Input.Keys.X) ){
                shoot();
            }
        }






    }

    private void moveLeft(float delta){
        if(jumpState == JumpState.GROUNDED && walkState != WalkState.WALKING){
            walkStartTime = TimeUtils.nanoTime();
        }
        walkState = WalkState.WALKING;
        facing = Facing.LEFT;
        position.x -= delta * Constants.CHIE_MOVE_SPEED;
    }

    private void moveRight(float delta){
        if(jumpState == JumpState.GROUNDED && walkState != WalkState.WALKING){
            walkStartTime = TimeUtils.nanoTime();
        }
        walkState = WalkState.WALKING;
        facing = Facing.RIGHT;
        position.x += delta * Constants.CHIE_MOVE_SPEED;
    }

    private void startJump(){
        jumpState = JumpState.JUMPING;
        jumpStartRime = TimeUtils.nanoTime();
        continueJump();
    }

    private void continueJump(){
        if(jumpState == JumpState.JUMPING){
            // Finding how long whe are  jumping
            float jumpDuration = MathUtils.nanoToSec * (TimeUtils.nanoTime() - jumpStartRime);
            if(jumpDuration <  Constants.MAX_JUMP_DURATION){
                velocity.y = Constants.CHIE_JUMP_SPEED;
            }else {
                endJump();
            }
        }
    }


    private void endJump(){
        if(jumpState == JumpState.JUMPING){
            jumpState = JumpState.FALLING;
        }
    }

    private void collisionPlatform(Array<Platform> platforms){
        for(Platform platform: platforms){
            if(landedOnPlatform(platform)){
                jumpState = jumpState.GROUNDED;
                velocity.y = 0;
                velocity.x = 0;
                position.y = platform.getTop() + Constants.CHIE_SIZE_HEIGHT;
            }
        }
    }

    private void collisionEnemy(){
       for(int  i = 0; i < level.getEnemies().size; i++){
           Enemy enemy = level.getEnemies().get(i);
           if(chieBounds().overlaps(enemy.enemyBounds())){
               if(position.x < enemy.position.x){
                   recoildFromEnemy(Facing.LEFT);
               }else{
                   recoildFromEnemy(Facing.RIGHT);
               }
           }
       }
    }

    private void collisionPowerup(){
        DelayedRemovalArray<Powerup> powerups = level.getPowerups();
        powerups.begin();
        for(int i = 0; i < powerups.size; i++){
            if(chieBounds().overlaps(powerups.get(i).powerupBounds())){
                ammo += Constants.POWER_UP_AMMO;
                level.setScore(level.getScore() + Constants.POWERUP_SCORE);
                powerups.removeIndex(i);
            }
        }
        powerups.end();


    }

    private void recoildFromEnemy(Facing facing){
        jumpState = JumpState.RECOILING;
        velocity.y = Constants.NKOCKBACK_VELOCITY.y;
        if(facing == Facing.LEFT){
            velocity.x = -Constants.NKOCKBACK_VELOCITY.x;
        }else{
            velocity.x = Constants.NKOCKBACK_VELOCITY.x;
        }
    }

    public void shoot() {
        if (ammo > 0) {
            fire = true;
            fireStartTime = TimeUtils.nanoTime();
            ammo--;
            Vector2 bulletPosition;

            if (facing == Facing.RIGHT) {
                bulletPosition = new Vector2(
                        position.x + Constants.CHIE_CANNON_OFFSET.x + 10,
                        position.y + Constants.CHIE_CANNON_OFFSET.y + 3
                );
            } else {
                bulletPosition = new Vector2(
                        position.x + Constants.CHIE_CANNON_OFFSET.x - 10,
                        position.y + Constants.CHIE_CANNON_OFFSET.y + 3
                );
            }
            level.spawnBullet(bulletPosition, facing);
        }
    }


    private boolean landedOnPlatform(Platform platform){
        boolean leftFootIn = false;
        boolean rightFootIn = false;
        boolean straddle = false;

        if(lastFramePosition.y - Constants.CHIE_SIZE_HEIGHT >= platform.getTop() &&
                position.y - Constants.CHIE_SIZE_HEIGHT < platform.getTop()){
            float leftFoot = position.x - (Constants.CHIE_SIZE_WIDTH - 25)  ;
            float rightFoot = position.x + (Constants.CHIE_SIZE_WIDTH - 27);

            leftFootIn = (platform.getLeft() < leftFoot && platform.getRight() > leftFoot);
            rightFootIn = (platform.getLeft() < rightFoot && platform.getRight() > rightFoot);

            straddle = (platform.getLeft() > leftFoot && platform.getRight() < rightFoot);
        }

        return leftFootIn || rightFootIn || straddle;

    }

    public void render(SpriteBatch batch ,ShapeRenderer renderer){
        /*renderer.setColor(Color.CORAL);
        renderer.rect(chieBounds().x, chieBounds().y,
                chieBounds().width, chieBounds().height);*/

        TextureRegion region = Assets.instance.chiAssets.standingRight;

        if(facing == Facing.RIGHT && jumpState != JumpState.GROUNDED){
            region = Assets.instance.chiAssets.jumpingRight;
        }else if(facing == Facing.RIGHT  && walkState == WalkState.STANDING && fire == false){
            region = Assets.instance.chiAssets.standingRight;
        }else if(facing == Facing.RIGHT  && walkState == WalkState.STANDING && fire == true){
            fireTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - fireStartTime);
            region = Assets.instance.chiAssets.fireRightAnimation.getKeyFrame(fireTimeSeconds);
            animationOver();
        }else if(facing == Facing.RIGHT  && walkState == WalkState.WALKING){
            float walkTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkStartTime);
            region = Assets.instance.chiAssets.walkingRightAnimation.getKeyFrame(walkTimeSeconds);
        }else if (facing == Facing.LEFT && jumpState != JumpState.GROUNDED){
            region = Assets.instance.chiAssets.jumpingLeft;
        }else if(facing == Facing.LEFT && walkState == WalkState.STANDING && fire == false){
            region = Assets.instance.chiAssets.standingLeft;
        }else if(facing == Facing.LEFT && walkState == WalkState.STANDING && fire == true){
            fireTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - fireStartTime);
            region = Assets.instance.chiAssets.fireLeftAnimation.getKeyFrame(fireTimeSeconds);
            animationOver();
        }else if(facing == Facing.LEFT && walkState == WalkState.WALKING){
            float walkTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkStartTime);
            region = Assets.instance.chiAssets.walkingLeftAnimation.getKeyFrame(walkTimeSeconds);
        }


        batch.draw(
                region.getTexture(),
                position.x - Constants.CHIE_EYE_POSITION.x,
                position.y - Constants.CHIE_EYE_POSITION.y,
                0,
                0,
                Constants.CHIE_SIZE_WIDTH,
                Constants.CHIE_SIZE_HEIGHT,
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

    public Vector2 getPosition() {
        return position;
    }

    public void animationOver(){
        if(fireTimeSeconds >= 1){
            fire = false;
        }
    }

    public Rectangle chieBounds(){
        return new Rectangle(
                position.x - Constants.CHIE_SIZE_WIDTH / 2,
                position.y - Constants.CHIE_SIZE_HEIGHT,
                Constants.CHIE_SIZE_WIDTH,
                Constants.CHIE_SIZE_HEIGHT
        );
    }

    public float getfireTimeSeconds(){ return fireTimeSeconds;}

    public int getAmmo() {
        return ammo;
    }

    public int getLives() {
        return lives;
    }

    public boolean isFire() {
        return fire;
    }

    public void setFire(boolean fire) {
        this.fire = fire;
    }

    public WalkState getWalkState() {
        return walkState;
    }

    public void setWalkState(WalkState walkState) {
        this.walkState = walkState;
    }
}

package com.mygdx.gamedev.chie.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.mygdx.gamedev.chie.entities.Chie;

/**
 * Created by PEDRO on 11/04/2016.
 */
public class ChaseCam {

    private Camera camera;
    private Chie target;
    private Boolean following;

    public ChaseCam(){
        following = true;
    }

    public void update(float delta){

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            following = !following;
        }

        if(following){
            camera.position.x = target.getPosition().x;
            camera.position.y = target.getPosition().y ;
        }else{
            if(Gdx.input.isKeyPressed(Input.Keys.A)){
                camera.position.x -= delta * Constants.CHASE_CAMERA_MOVE_SPEED;
            }

            if(Gdx.input.isKeyPressed(Input.Keys.D)){
                camera.position.x += delta * Constants.CHASE_CAMERA_MOVE_SPEED;
            }

            if(Gdx.input.isKeyPressed(Input.Keys.W)){
                camera.position.y += delta * Constants.CHASE_CAMERA_MOVE_SPEED;
            }

            if(Gdx.input.isKeyPressed(Input.Keys.S)){
                camera.position.y -= delta * Constants.CHASE_CAMERA_MOVE_SPEED;
            }
        }

    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Chie getTarget() {
        return target;
    }

    public void setTarget(Chie target) {
        this.target = target;
    }
}

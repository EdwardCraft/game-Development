package com.mygdx.gamedev.chie.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gamedev.chie.entities.Chie;
import com.mygdx.gamedev.chie.util.Assets;
import com.mygdx.gamedev.chie.util.Constants;
import com.mygdx.gamedev.chie.util.Enums;
import com.mygdx.gamedev.chie.util.Utils;


public class OnscreenControls extends InputAdapter {

    public static final String TAG = OnscreenControls.class.getName();

    public final Viewport viewport;
    public Chie chie;
    private Vector2 moveLeftCenter;
    private Vector2 moveRightCenter;
    private Vector2 shootCenter;
    private Vector2 jumpCenter;
    private int moveLeftPointer;
    private int moveRightPointer;
    private int jumpPointer;

    public OnscreenControls() {
        this.viewport = new ExtendViewport(
                Constants.ONSCREEN_CONTROLS_VIEWPORT_SIZE,
                Constants.ONSCREEN_CONTROLS_VIEWPORT_SIZE);


        moveLeftCenter = new Vector2();
        moveRightCenter = new Vector2();
        shootCenter = new Vector2();
        jumpCenter = new Vector2();

        recalculateButtonPositions();
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector2 viewportPosition = viewport.unproject(new Vector2(screenX, screenY));

            if (viewportPosition.dst(shootCenter) < Constants.BUTTON_RADIUS && chie.getWalkState() == Enums.WalkState.STANDING) {

                // TODO: Call shoot() on GigaGal
                chie.shoot();

            } else if (viewportPosition.dst(jumpCenter) < Constants.BUTTON_RADIUS ) {

                // TODO: Save the jumpPointer and set gigaGal.jumpButtonPressed = true
                jumpPointer = pointer;
                chie.jumpButtonPressed = true;

            } else if (viewportPosition.dst(moveLeftCenter) < Constants.BUTTON_RADIUS ) {

                // TODO: Save the moveLeftPointer, and set gigaGal.leftButtonPressed = true
                moveLeftPointer = pointer;
                chie.leftButtonPressed = true;

            } else if (viewportPosition.dst(moveRightCenter) < Constants.BUTTON_RADIUS ) {

                // TODO: Save the moveRightPointer, and set gigaGal.rightButtonPressed = true
                moveRightPointer = pointer;
                chie.rightButtonPressed = true;

            }



        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 viewportPosition = viewport.unproject(new Vector2(screenX, screenY));
        if(!chie.isFire()){
            if (pointer == moveLeftPointer && viewportPosition.dst(moveRightCenter) < Constants.BUTTON_RADIUS) {

                //  Handle the case where the left button touch has been dragged to the right button
                // Inform GigaGal that the left button is no longer pressed
                chie.leftButtonPressed = false;

                // Inform GigaGal that the right button is now pressed
                chie.rightButtonPressed = true;

                // Zero moveLeftPointer
                moveLeftPointer = 0;

                // Save moveRightPointer
                moveRightPointer = pointer;

            }

            if (pointer == moveRightPointer && viewportPosition.dst(moveLeftCenter) < Constants.BUTTON_RADIUS) {

                // TODO: Handle the case where the right button touch has been dragged to the left button
                chie.rightButtonPressed = false;
                chie.leftButtonPressed = true;
                moveRightPointer = 0;
                moveLeftPointer = pointer;

            }
        }



        return super.touchDragged(screenX, screenY, pointer);
    }

    public void render(SpriteBatch batch) {

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        if (!Gdx.input.isTouched(jumpPointer)) {
            chie.jumpButtonPressed = false;
            jumpPointer = 0;
        }

        // TODO: If the moveLeftPointer is no longer touched, inform GigaGal and zero moveLeftPointer
        if (!Gdx.input.isTouched(moveLeftPointer)) {
            chie.leftButtonPressed = false;
            moveLeftPointer = 0;
        }

        // TODO: Do the same for moveRightPointer
        if (!Gdx.input.isTouched(moveRightPointer)) {
            chie.rightButtonPressed = false;
            moveRightPointer = 0;
        }

        Utils.drawTextureRegion(
                batch,
                Assets.instance.onscreenControlsAssets.moveLeft,
                moveLeftCenter,
                Constants.BUTTON_CENTER
        );

        Utils.drawTextureRegion(
                batch,
                Assets.instance.onscreenControlsAssets.moveRight,
                moveRightCenter,
                Constants.BUTTON_CENTER
        );

        Utils.drawTextureRegion(
                batch,
                Assets.instance.onscreenControlsAssets.shoot,
                shootCenter,
                Constants.BUTTON_CENTER
        );

        Utils.drawTextureRegion(
                batch,
                Assets.instance.onscreenControlsAssets.jump,
                jumpCenter,
                Constants.BUTTON_CENTER
        );
        batch.end();
    }

    public void recalculateButtonPositions() {
        moveLeftCenter.set(Constants.BUTTON_RADIUS * 3 / 4, Constants.BUTTON_RADIUS);
        moveRightCenter.set(Constants.BUTTON_RADIUS * 2, Constants.BUTTON_RADIUS * 3 / 4);
        shootCenter.set(
                viewport.getWorldWidth() - Constants.BUTTON_RADIUS * 2f,
                Constants.BUTTON_RADIUS * 3 / 4
        );
        jumpCenter.set(
                viewport.getWorldWidth() - Constants.BUTTON_RADIUS * 3 / 4,
                Constants.BUTTON_RADIUS
        );
    }
}

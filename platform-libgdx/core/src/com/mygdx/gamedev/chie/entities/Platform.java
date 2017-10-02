package com.mygdx.gamedev.chie.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.gamedev.chie.util.Assets;

/**
 * Created by PEDRO on 11/04/2016.
 */
public class Platform {

    private float top;
    private float botton;
    private float left;
    private float right;
    private String identifier;

    public Platform(float left, float top, float width, float height){
        this.top = top;
        this.botton = top - height;
        this.left = left;
        this.right = left + width;
    }

    public void render(SpriteBatch batch){
        float width = right - left;
        float height = top -botton;

        Assets.instance.platformAssets.platformNimePatch.draw(
                batch,
                left - 1,
                botton - 1,
                width + 2,
                height + 2);

    }

    public String getIdentifier(){
        return identifier;
    }

    public void setIdentifier(String identifier){
        this.identifier = identifier;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getBotton() {
        return botton;
    }

    public void setBotton(float botton) {
        this.botton = botton;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }
}

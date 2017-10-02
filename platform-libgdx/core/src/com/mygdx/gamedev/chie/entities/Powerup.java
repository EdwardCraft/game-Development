package com.mygdx.gamedev.chie.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.gamedev.chie.util.Assets;
import com.mygdx.gamedev.chie.util.Constants;
import com.mygdx.gamedev.chie.util.Utils;



/**
 * Created by PEDRO on 12/04/2016.
 */
public class Powerup {

    private Vector2 position;

    public Powerup(Vector2 position){
        this.position = position;

    }

    public void render(SpriteBatch batch, ShapeRenderer renderer){
        /*renderer.setColor(Color.CHARTREUSE);
        renderer.rect(powerupBounds().x, powerupBounds().y,
                powerupBounds().width, powerupBounds().height);
*/
        final TextureRegion region = Assets.instance.powerupAssets.powerup;
        Utils.drawTextureRegion(batch, region, position, Constants.POWERUP_CENTER);
    }

    public Rectangle powerupBounds(){
        return new Rectangle(
                position.x - Constants.POWERUP_CENTER.x,
                position.y - Constants.POWERUP_CENTER.y,
                Assets.instance.powerupAssets.powerup.getRegionWidth(),
                Assets.instance.powerupAssets.powerup.getRegionHeight()
        );
    }


}

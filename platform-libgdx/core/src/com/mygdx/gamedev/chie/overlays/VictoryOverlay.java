package com.mygdx.gamedev.chie.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gamedev.chie.entities.Explosion;
import com.mygdx.gamedev.chie.util.Constants;

/**
 * Created by PEDRO on 13/04/2016.
 */
public class VictoryOverlay {
    public final static String TAG = VictoryOverlay.class.getName();
    private final Viewport viewport;
    final BitmapFont font;
    Array<Explosion> explosions;


    public VictoryOverlay(){
        this.viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        font = new BitmapFont(Gdx.files.internal(Constants.FONT_FILE));
        font.getData().setScale(1);

    }

    public void init(){
        explosions = new Array<Explosion>();
        for(int i = 0;  i < Constants.EXPLOSION_COUNT; i++){
            Explosion explosion = new Explosion(new Vector2(
                    MathUtils.random(viewport.getWorldWidth()),
                    MathUtils.random(viewport.getWorldHeight())
            ));
            explosion.offset =MathUtils.random(Constants.LEVEL_END_DURATION);
            explosions.add(explosion);
        }
    }

    public void render(SpriteBatch batch){
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        for(Explosion explosion: explosions){
                explosion.render(batch);
        }

        font.draw(batch, Constants.VICTORY_MESSAGE, viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2.5f, 0, Align.center, false);

        batch.end();
    }

    public static String getTAG() {
        return TAG;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public Array<Explosion> getExplosions() {
        return explosions;
    }

    public void setExplosions(Array<Explosion> explosions) {
        this.explosions = explosions;
    }

    public BitmapFont getFont() {
        return font;
    }
}

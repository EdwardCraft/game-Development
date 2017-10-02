package com.mygdx.gamedev.chie.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gamedev.chie.entities.Enemy;
import com.mygdx.gamedev.chie.entities.Platform;
import com.mygdx.gamedev.chie.util.Constants;
import com.mygdx.gamedev.chie.util.Utils;

/**
 * Created by PEDRO on 13/04/2016.
 */
public class GameOverOverlay {

    private final Viewport viewport;
    private final BitmapFont font;
    private Array<Enemy> enemies;
    private long startTime;

    public GameOverOverlay(){
        this.viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        font = new BitmapFont(Gdx.files.internal(Constants.FONT_FILE));
        font.getData().setScale(1);

    }

    public void init() {
        startTime = TimeUtils.nanoTime();

        enemies = new Array<Enemy>(Constants.ENEMY_COUNT);

       /* for (int i = 0; i < Constants.ENEMY_COUNT - 1; i++) {

            Platform fakePlatform = new Platform(
                    MathUtils.random(viewport.getWorldWidth()),
                    MathUtils.random(-Constants.ENEMY_CENTER.y, viewport.getWorldHeight()
                    ), 0, 0);

            Enemy enemy = new Enemy(fakePlatform);

            enemies.add(enemy);
        }*/
    }

    public void render(SpriteBatch batch) {

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        // TODO: Draw a game over message
        // Feel free to get more creative with this screen. Perhaps you could cover the screen in enemy robots?

        float timeElapsed = Utils.secondsSince(startTime);
        int enemiesToShow = (int) (Constants.ENEMY_COUNT * (timeElapsed / Constants.LEVEL_END_DURATION));

        /*for (int i = 0; i < enemiesToShow; i++){
            Enemy enemy = enemies.get(i);
            enemy.update(0);
            enemy.render(batch);
        }*/

        font.draw(batch, Constants.GAME_OVER_MESSAGE, viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2.5f, 0, Align.center, false);

        batch.end();

    }

    public Viewport getViewport() {
        return viewport;
    }

    public BitmapFont getFont() {
        return font;
    }

    public Array<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(Array<Enemy> enemies) {
        this.enemies = enemies;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}

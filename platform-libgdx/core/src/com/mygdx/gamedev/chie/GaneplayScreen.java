package com.mygdx.gamedev.chie;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.gamedev.chie.overlays.ChieHud;
import com.mygdx.gamedev.chie.overlays.GameOverOverlay;
import com.mygdx.gamedev.chie.overlays.OnscreenControls;
import com.mygdx.gamedev.chie.overlays.VictoryOverlay;
import com.mygdx.gamedev.chie.util.Assets;
import com.mygdx.gamedev.chie.util.ChaseCam;
import com.mygdx.gamedev.chie.util.Constants;
import com.mygdx.gamedev.chie.util.LevelLoader;
import com.mygdx.gamedev.chie.util.Utils;

/**
 * Created by PEDRO on 11/04/2016.
 */
public class GaneplayScreen extends ScreenAdapter {
    public static final  String TAG = GaneplayScreen.class.getName();
    OnscreenControls onscreenControls;
    private Level level;
    private SpriteBatch batch;
    private ChaseCam chaseCam;
    private ShapeRenderer renderer;
    private ChieHud hud;
    private VictoryOverlay victoryOverlay;
    private GameOverOverlay gameOverOverlay;
    private long levelEndOverlayStartTime;
    private Music music = Gdx.audio.newMusic(Gdx.files.internal(Constants.LEVEL_1_MUSIC));


    @Override
    public void show() {
        AssetManager am = new AssetManager();
        Assets.instance.init(am);
        batch = new SpriteBatch();
        chaseCam = new ChaseCam();
        hud = new ChieHud();
        victoryOverlay = new VictoryOverlay();
        gameOverOverlay = new GameOverOverlay();
        onscreenControls = new OnscreenControls();
        renderer = new ShapeRenderer();
        //music.setLooping(true);
        if (onMobile()) {
            Gdx.input.setInputProcessor(onscreenControls);
        }

        startNewLevel();
    }

    private boolean onMobile() {
        return Gdx.app.getType() == Application.ApplicationType.Android || Gdx.app.getType() == Application.ApplicationType.iOS;
    }

    @Override
    public void render(float delta) {

        level.update(delta);
        chaseCam.update(delta);
        Gdx.gl.glClearColor(
                Constants.BACKGROUND_COLOR.r,
                Constants.BACKGROUND_COLOR.g,
                Constants.BACKGROUND_COLOR.b,
                Constants.BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        level.render(batch, renderer);
        if (onMobile()) {
            onscreenControls.render(batch);
        }
        hud.render(batch, level.getChie().getLives(), level.getChie().getAmmo(), level.getScore());
        renderLevelEndOverlays(batch);

    }


    @Override
    public void resize(int width, int height) {
        hud.viewport.update(width, height,true);
        victoryOverlay.getViewport().update(width,height,true);
        gameOverOverlay.getViewport().update(width, height, true);
        level.getViewport().update(width, height, true);
        chaseCam.setCamera(level.getViewport().getCamera());
        onscreenControls.viewport.update(width, height, true);
        onscreenControls.recalculateButtonPositions();
    }


    @Override
    public void dispose() {
        Assets.instance.dispose();
        batch.dispose();
        renderer.dispose();
        music.dispose();

    }

    public void startNewLevel(){
        //level = level.debugLevel();
        String levelName = Constants.LEVELS;
        level = LevelLoader.load(levelName);
        chaseCam.setCamera(level.getViewport().getCamera());
        chaseCam.setTarget(level.getChie());
        onscreenControls.chie = level.getChie();
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //music.play();
    }

    private void renderLevelEndOverlays(SpriteBatch batch) {

        if (level.isVictory()) {
           // music.stop();
            if (levelEndOverlayStartTime == 0) {
                // Set levelEndOverlayStartTime = TimeUtils.nanoTime()
                levelEndOverlayStartTime = TimeUtils.nanoTime();

                // Call init on the victory overlay
                victoryOverlay.init();
            }

            // Render the victory overlay
            victoryOverlay.render(batch);

            if (Utils.secondsSince(levelEndOverlayStartTime) > Constants.LEVEL_END_DURATION) {

                //  Reset levelEndOverlayStartTime to 0
                levelEndOverlayStartTime = 0;

                //  Call levelComplete()
                levelComplete();
            }
        }

        if(level.isGameOver()){
           // music.stop();
            if(levelEndOverlayStartTime == 0){
                levelEndOverlayStartTime = TimeUtils.nanoTime();
                gameOverOverlay.init();
            }
            gameOverOverlay.render(batch);
            if(Utils.secondsSince(levelEndOverlayStartTime) >  Constants.LEVEL_END_DURATION){
                levelEndOverlayStartTime = 0;
                levelFailed();
            }
        }
    }


    public void levelComplete() {
        startNewLevel();
    }

    public void levelFailed() {
        startNewLevel();
    }



}



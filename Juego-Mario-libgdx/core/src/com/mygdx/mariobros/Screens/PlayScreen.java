package com.mygdx.mariobros.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.mariobros.MarioBros;
import com.mygdx.mariobros.Scenes.Hud;
import com.mygdx.mariobros.Sprites.Mario;
import com.mygdx.mariobros.Tools.Box2DWolrdCreator;
import com.mygdx.mariobros.Tools.WorldContactLestener;
import com.mygdx.mariobros.Util.Constants;



/**
 * Created by PEDRO on 02/02/2016.
 */
public class PlayScreen implements Screen {

    private MarioBros game;
    private OrthographicCamera gamecam;
    private Viewport viewport;
    private Hud hud;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    Mario mario;
    //Box 2d variables
    private World world;
    private Box2DDebugRenderer b2dr;
    private TextureAtlas atlas;
    private Music music;



    public PlayScreen(MarioBros gane){
        atlas = new TextureAtlas("FOX_ATLAS.pack");

        this.game = gane;
        gamecam = new OrthographicCamera();
        viewport = new FitViewport(Constants.V_WIDTH / Constants.PIXELS_PER_METER,
                Constants.V_HEIGHT / Constants.PIXELS_PER_METER ,gamecam);
        hud = new Hud(gane.batch);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(Constants.WOLRD_MAP_TMX);
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Constants.PIXELS_PER_METER);
        gamecam.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        world = new World(new Vector2(0,Constants.GRAVITY_ACCELERATION), true);
        b2dr = new Box2DDebugRenderer();
        new Box2DWolrdCreator(world, map);
        mario = new Mario(world, this);
        world.setContactListener(new WorldContactLestener());
        music = MarioBros.manager.get(Constants.MUSIC_WOLRD_1, Music.class);
        music.setLooping(true);
        music.play();
    }



    @Override
    public void show() {

    }

    public  void handleInput(float delta){
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            mario.body.applyLinearImpulse(new Vector2(0, 4f), mario.body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && mario.body.getLinearVelocity().x <= Constants.MARIO_SPEED_LIMIT){
            mario.body.applyLinearImpulse(new Vector2(Constants.MARIO_MOVE_VELOCITY,0),mario.body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && mario.body.getLinearVelocity().x >= -Constants.MARIO_SPEED_LIMIT){
            mario.body.applyLinearImpulse(new Vector2(-Constants.MARIO_MOVE_VELOCITY,0),mario.body.getWorldCenter(), true);
        }
    }

    public void update(float delta){
        handleInput(delta);
        world.step(1 / 45f, 6, 2);
        mario.update(delta);
        hud.update(delta);
        gamecam.position.x = mario.body.getPosition().x;
        gamecam.update();
        renderer.setView(gamecam);


    }

    @Override
    public void render(float delta) {
        delta = .01f;
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // render the map
        renderer.render();
        // render our Box2D
        b2dr.render(world, gamecam.combined);
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        mario.draw(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        renderer.dispose();
        map.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }


    public TextureAtlas getAtlas(){
        return atlas;
    }







}

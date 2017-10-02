package com.mygdx.gamedev.chie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gamedev.chie.entities.Bullet;
import com.mygdx.gamedev.chie.entities.Chie;
import com.mygdx.gamedev.chie.entities.Enemy;
import com.mygdx.gamedev.chie.entities.ExitPortal;
import com.mygdx.gamedev.chie.entities.Explosion;
import com.mygdx.gamedev.chie.entities.Platform;
import com.mygdx.gamedev.chie.entities.Powerup;
import com.mygdx.gamedev.chie.util.Assets;
import com.mygdx.gamedev.chie.util.Constants;
import com.mygdx.gamedev.chie.util.Enums.Facing;
import com.mygdx.gamedev.chie.util.Utils;

/**
 * Created by PEDRO on 11/04/2016.
 */
public class Level {
    private boolean gameOver;
    private boolean victory;
    private int score;
    private Chie chie;
    private Viewport viewport;
    private Array<Platform> platforms;
    private DelayedRemovalArray<Enemy> enemies;
    private DelayedRemovalArray<Bullet> bullets;
    private DelayedRemovalArray<Explosion> explosions;
    private DelayedRemovalArray<Powerup> powerups;
    private ExitPortal exitPortal;

    public Level(){
        viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        chie = new Chie(Constants.DEFAULT_SPAWN_LOCATION, this );
        platforms = new Array<Platform>();
        bullets = new DelayedRemovalArray<Bullet>();
        enemies = new DelayedRemovalArray<Enemy>();
        explosions = new DelayedRemovalArray<Explosion>();
        powerups = new DelayedRemovalArray<Powerup>();
        exitPortal = new ExitPortal(Constants.EXIT_PORTAL_DEFAULT_LOCATION);
        gameOver = false;
        victory = false;
        score = 0;
    }

    public static Level debugLevel(){
        Level level = new Level();
        level.initializeDebugLevel();
        return level;
    }
    public void update(float delta){
        if(chie.getLives() < 0){
            gameOver = true;
        }
        else if(chie.getPosition().dst(exitPortal.postition)< Constants.EXIT_PORTAL_RADIUS){
            victory = true;
        }

        if(!gameOver && !victory){
            chie.update(delta, platforms);
            bullets.begin();
            for(Bullet bullet :  bullets){
                if(chie.getfireTimeSeconds() >= 0.7){
                    bullet.update(delta);
                }

                if(!bullet.getActive()){
                    bullets.removeValue(bullet, false);
                }
            }
            bullets.end();

            enemies.begin();
            for(int  i = 0; i < enemies.size; i++){
                Enemy enemy = enemies.get(i);
                enemy.update(delta);
                if(enemy.getHealth() < 1){
                    enemies.removeIndex(i);
                    score += Constants.ENEMY_KILL_SCORE;
                }
            }
            enemies.end();

            explosions.begin();
            for(int i = 0; i < explosions.size; i++){
                if(explosions.get(i).isFinished()){
                    explosions.removeIndex(i);
                }
            }
            explosions.end();
        }

    }

    public void render(SpriteBatch batch, ShapeRenderer renderer){
        viewport.apply();
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        batch.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        batch.begin();

        for(Platform platform : platforms){
            platform.render(batch);
        }

        exitPortal.render(batch);

        for(Powerup powerup : powerups){
            powerup.render(batch, renderer);
        }

        for(Enemy enemy : enemies){
            enemy.render(batch);
        }
        chie.render(batch, renderer);

        for(Bullet bullet : bullets){
            if(chie.getfireTimeSeconds() >= 0.7){
                bullet.render(batch, renderer);
            }

        }

        for(Explosion explosion: explosions){
            explosion.render(batch);
        }
        batch.end();
        renderer.end();
    }

    public Chie getChie(){ return  chie; }

    public void initializeDebugLevel(){

        chie = new Chie(new Vector2( 15, 40), this );
        exitPortal = new ExitPortal(new Vector2(150,150));
        platforms = new Array<Platform>();
        bullets = new DelayedRemovalArray<Bullet>();
        enemies = new DelayedRemovalArray<Enemy>();
        explosions = new DelayedRemovalArray<Explosion>();
        powerups = new DelayedRemovalArray<Powerup>();
        Platform enemyPlatform = new Platform(80, 100 , 50, 50);
        enemies.add(new Enemy(enemyPlatform));
        platforms.add(enemyPlatform);
        platforms.add(new Platform(20, 40 , 50, 20));
        platforms.add(new Platform(90, 60 , 50, 20));
        platforms.add(new Platform(20, 80 , 50, 20));
        platforms.add(new Platform( 0, 10, 500, 10));
        powerups.add(new Powerup(new Vector2(20, 85)));
    }

    public DelayedRemovalArray<Powerup> getPowerups(){
        return powerups;
    }

    public void setChie(Chie chie) {
        this.chie = chie;
    }

    public Array<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Array<Platform> platforms) {
        this.platforms = platforms;
    }

    public DelayedRemovalArray<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(DelayedRemovalArray<Enemy> enemies) {
        this.enemies = enemies;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    public void spawnBullet(Vector2 position, Facing facing){
        bullets.add(new Bullet(this, position, facing));
    }

    public ExitPortal getExitPortal() {
        return exitPortal;
    }

    public void setExitPortal(ExitPortal exitPortal) {
        this.exitPortal = exitPortal;
    }

    public void spawnExplosions(Vector2 position){
        explosions.add(new Explosion(position));
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isVictory() {
        return victory;
    }

    public void setVictory(boolean victory) {
        this.victory = victory;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public DelayedRemovalArray<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(DelayedRemovalArray<Bullet> bullets) {
        this.bullets = bullets;
    }

    public DelayedRemovalArray<Explosion> getExplosions() {
        return explosions;
    }

    public void setExplosions(DelayedRemovalArray<Explosion> explosions) {
        this.explosions = explosions;
    }

    public void setPowerups(DelayedRemovalArray<Powerup> powerups) {
        this.powerups = powerups;
    }


}

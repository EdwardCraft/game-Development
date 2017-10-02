package com.mygdx.mariobros.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.mariobros.Util.Constants;

/**
 * Created by PEDRO on 03/02/2016.
 */
public class Hud implements Disposable {
    public Stage stage;
    public Viewport viewport;
    private Integer worldTimer;
    private float timeCount;
    private static  Integer score;
    Label countDownLabel;
   static Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label worldLabel;
    Label marioLabel;

    public Hud(SpriteBatch batch){
        worldTimer = 300;
        timeCount = 0;
        score = 0;
        viewport = new FillViewport(Constants.V_WIDTH, Constants.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, batch);
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        countDownLabel = new Label(String.format("%03d", worldTimer),
                new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%06d", score),
                new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label(Constants.HUD_TIME_LABEL, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label(Constants.HUD_LEVEL_1_LABEL, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label(Constants.HUD_WORLD_LABEL, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        marioLabel = new Label(Constants.HUD_MARIO_LABEL, new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(marioLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countDownLabel).expandX();

        stage.addActor(table);

    }

    public void update(float delta){
        timeCount += delta;
        if(timeCount >= 1){
            worldTimer--;
            countDownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }
    }

    public static void addScore(int value){
        score += value;
        scoreLabel.setText(String.format("%06d", score));

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

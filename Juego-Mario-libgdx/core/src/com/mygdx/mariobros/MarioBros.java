package com.mygdx.mariobros;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.mariobros.Screens.PlayScreen;
import com.mygdx.mariobros.Util.Constants;
import com.sun.corba.se.impl.orbutil.closure.Constant;

public class MarioBros extends Game {
	public static final String TAG = MarioBros.class.getName();
	public SpriteBatch batch;
	/*Warning using assetManager in a static way can cause  issues, especially on android.
	  instead you may want to pass AssetManager to those classes that need it.  */

	public static AssetManager manager;

	@Override
	public void create() {
		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load(Constants.MUSIC_WOLRD_1, Music.class);
		manager.load(Constants.SOUND_BUMP, Sound.class);
		manager.load(Constants.SOUND_BREAK_BLOCK, Sound.class);
		manager.load(Constants.SOUND_COIN, Sound.class);
		manager.load(Constants.SOUND_PLAYER_DIE, Sound.class);
		manager.load(Constants.SOUND_POWER_DOWN, Sound.class);
		manager.load(Constants.SOUND_POWER_UP_SPAWN, Sound.class);
		manager.load(Constants.SOUND_POWER_UP, Sound.class);
		manager.load(Constants.SOUND_STOMP, Sound.class);
		manager.finishLoading();
		setScreen(new PlayScreen(this));

	}

	@Override
	public void dispose() {
		super.dispose();
		manager.dispose();
		batch.dispose();
	}

	@Override
	public void render(){
		super.render();
	}
}

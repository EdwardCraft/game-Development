package com.mygdx.gamedev.chie.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.gamedev.chie.ChieGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1200;
		config.height = 700;
		config.title = "Test";
		config.resizable = false;

		new LwjglApplication(new ChieGame(), config);
	}
}


package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.VioletBird;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = VioletBird.HEIGHT;
		config.width = VioletBird.WIDTH;
		config.title = VioletBird.TITLE;
		new LwjglApplication(new VioletBird(), config);
	}
}

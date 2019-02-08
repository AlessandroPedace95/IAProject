package com.sfo.games.puzzlebobble;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopLauncher {

	public static void main(String[] args) {
		Game.i = new Game();
        new LwjglApplication(Game.i, "Game", 480, 800,true);

	}

}

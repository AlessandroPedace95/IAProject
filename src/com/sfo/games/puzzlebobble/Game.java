package com.sfo.games.puzzlebobble;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sfo.games.puzzlebobble.stages.Stage;
import com.sfo.games.puzzlebobble.stages.StageMainMenu;

import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv.desktop.DLVDesktopService;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;

public class Game implements ApplicationListener {
	
	public static int GAME_WIDTH = 480;
	public static int GAME_HEIGHT = 800;

	public static Game i = null;
	public OrthographicCamera camera;
	public Music music;
	private SpriteBatch spriteBatch;
	private Stage stage;
	
	public void create()
	{
		Gdx.input.setCatchBackKey(true);
		this.spriteBatch = new SpriteBatch();
		this.stage = new StageMainMenu();
		this.stage.load();
	}

	public void resize(int width, int height)
	{
		this.camera = new OrthographicCamera(GAME_WIDTH, GAME_HEIGHT);
		this.camera.position.set(GAME_WIDTH / 2, GAME_HEIGHT / 2, 0.0F);
		this.camera.update();
		this.spriteBatch.setProjectionMatrix(this.camera.combined);
	}

	public void logic()
	{
		float delta = Gdx.graphics.getDeltaTime() * 1000.0F;
		this.stage.logic(delta);
	}

	public void render()
	{
		Gdx.gl.glClearColor(1.0F, 0.0F, 1.0F, 1.0F);
		Gdx.gl.glClear(16384);

		logic();
		this.spriteBatch.begin();
		this.stage.draw(this.spriteBatch);
		this.spriteBatch.end();
	}

	public void pause()
	{
	}

	public void resume()
	{
	}

	public void dispose()
	{
	}

	public void changeStage(Stage newStage)
	{
		this.stage = newStage;
		this.stage.load();
	}
}

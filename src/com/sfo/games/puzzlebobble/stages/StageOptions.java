package com.sfo.games.puzzlebobble.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.sfo.games.puzzlebobble.Game;
import com.sfo.games.puzzlebobble.Preferences;
import com.sfo.games.puzzlebobble.helpers.InputHelper;

public class StageOptions implements Stage
{
	private Sprite background;
	private Rectangle buttonVibration;
	private Rectangle buttonEffects;
	private Rectangle buttonMusic;
	private Rectangle buttonBack;
	private BitmapFont font;

	public void load()
	{
		this.background = new Sprite(new Texture(Gdx.files.internal("assets/images/options.png")));
		this.font = new BitmapFont();
		this.font.setScale(6.5F);
		this.buttonEffects 		= new Rectangle(340.0F, 400.0F, 80.0F, 70.0F);
		this.buttonMusic 		= new Rectangle(340.0F, 300.0F, 80.0F, 70.0F);
		this.buttonVibration 	= new Rectangle(340.0F, 210.0F, 80.0F, 70.0F);
		this.buttonBack 		= new Rectangle(80.0F, 115.0F, 320.0F, 78.0F);
	}

	public void dispose()
	{
	}

	public void logic(float delta)
	{
		if (InputHelper.justTouched())
		{
			if (this.buttonEffects.contains(InputHelper.touch.x, InputHelper.touch.y))
			{
				Preferences.effects(!Preferences.effects());
			}
			if (this.buttonMusic.contains(InputHelper.touch.x, InputHelper.touch.y))
			{
				Preferences.music(!Preferences.music());
			}
			if (this.buttonVibration.contains(InputHelper.touch.x, InputHelper.touch.y))
			{
				Preferences.vibrate(!Preferences.vibrate());
			}
			if (this.buttonBack.contains(InputHelper.touch.x, InputHelper.touch.y))
			{
				Game.i.changeStage(new StageMainMenu());
			}
		}
	}

	public void draw(SpriteBatch spriteBatch)
	{
		this.background.draw(spriteBatch);
		if (Preferences.effects())
		{ this.font.draw(spriteBatch, "X", 345.0F, 505.0F); }
		else 
		{this.font.draw(spriteBatch, " ", 345.0F, 505.0F); }
		
		if (Preferences.music())
		{ this.font.draw(spriteBatch, "X", 345.0F, 405.0F); }
		else 
		{ this.font.draw(spriteBatch, " ", 345.0F, 405.0F); }
		
		if (Preferences.vibrate())
		{ this.font.draw(spriteBatch, "X", 345.0F, 305.0F); }
		else
		{ this.font.draw(spriteBatch, " ", 345.0F, 305.0F); }
	}
}

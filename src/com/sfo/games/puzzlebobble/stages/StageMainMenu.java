package com.sfo.games.puzzlebobble.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.sfo.games.puzzlebobble.Game;
import com.sfo.games.puzzlebobble.helpers.InputHelper;

public class StageMainMenu implements Stage
{
	Sprite background;
	Rectangle buttonPlay;
	//Rectangle buttonOptions;
	//Rectangle buttonExit;
	Sound push;

	public void load()
	{
		this.background = new Sprite(new Texture(Gdx.files.internal("assets/images/startN.png")));
		this.buttonPlay 	= new Rectangle(0.0F, 0.0F, 480.0F, 800.0F);
		//this.buttonOptions 	= new Rectangle(78.0F, 209.0F, 320.0F, 78.0F);
		//this.buttonExit 	= new Rectangle(78.0F, 115.0F, 320.0F, 78.0F);
		this.push = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/push.wav"));
		if (Game.i.music == null)
		{
			Game.i.music = Gdx.audio.newMusic(Gdx.files.internal("assets/sounds/background.ogg"));
			Game.i.music.setLooping(true);
			InputHelper.play(Game.i.music);
		}
		else
		{
			Game.i.music.stop();
			InputHelper.play(Game.i.music);
		}
	}

	public void dispose()
	{
		this.push.dispose();
	}

	public void logic(float delta)
	{
		if (InputHelper.justTouched())
		{
			if (this.buttonPlay.contains(InputHelper.touch.x, InputHelper.touch.y))
			{
				InputHelper.play(this.push);
				Game.i.changeStage(StagePlay.getInstance());
			}
//			if (this.buttonOptions.contains(InputHelper.touch.x, InputHelper.touch.y))
//			{
//				InputHelper.play(this.push);
//				Game.i.changeStage(new StageOptions());
//			}
//			if (this.buttonExit.contains(InputHelper.touch.x, InputHelper.touch.y))
//			{
//				InputHelper.play(this.push);
//				Gdx.app.exit();
//			}
		}
	}

	public void draw(SpriteBatch spriteBatch)
	{
		this.background.draw(spriteBatch);
	}
}

 
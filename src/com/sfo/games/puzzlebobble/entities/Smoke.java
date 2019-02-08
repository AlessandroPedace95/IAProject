package com.sfo.games.puzzlebobble.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Smoke 
{
	private static int FRAME_COLS = 4;
	private static int FRAME_ROWS = 5;
	private static float FRAME_DURATION = 0.05f;
	private static float TOTAL_DURATION = 0.2f;//FRAME_DURATION * FRAME_COLS * FRAME_ROWS;
	
	private static Texture			smokeSheet;
	private static TextureRegion[]	smokeFrames;
	Vector2			position;
	Animation 		smokeAnimation;
	TextureRegion	currentFrame;
	float stateTime = 0.0f;
	
	public static void preloadImages()
	{
		smokeSheet = new Texture(Gdx.files.internal("assets/images/smoke.png"));
		TextureRegion[][] tmp = TextureRegion.split(smokeSheet, 50, 50);//this.smokeSheet.getWidth() / FRAME_ROWS,
		 //this.smokeSheet.getHeight() / FRAME_COLS);
		smokeFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) 
		{
			for (int j = 0; j < FRAME_COLS; j++) 
			{
				smokeFrames[index++] = tmp[j][i];
			}
		}
	}
	
	public Smoke(float x, float y)
	{
		this.position = new Vector2(x, y);
        this.smokeAnimation = new Animation(FRAME_DURATION, smokeFrames);
	}
	
	public void dispose()
	{
		smokeSheet.dispose();
	}
	
	public boolean update(float delta)
	{
		this.stateTime += (delta / 1000);
		this.currentFrame = this.smokeAnimation.getKeyFrame(stateTime, false);
		if (this.finished())
		{ return true; }
		return false;
	}
	
	public void draw(SpriteBatch spriteBatch)
	{
		if (this.currentFrame != null && !finished())
		{ spriteBatch.draw(this.currentFrame, this.position.x, this.position.y); }
	}
	
	public boolean finished()
	{
		if (this.stateTime >= TOTAL_DURATION)
		{ return true; }
		return false;
	}
}

package com.sfo.games.puzzlebobble.stages;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract interface Stage
{
	public abstract void dispose();

	public abstract void draw(SpriteBatch spriteBatch);

	public abstract void load();

	public abstract void logic(float delta);
}
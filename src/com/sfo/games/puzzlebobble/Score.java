package com.sfo.games.puzzlebobble;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Score 
{
	private static int ROUND_TIME = 60;
	BitmapFont font;
	private int points = 0;
	private float time = ROUND_TIME;
	private String str1 = "Punteggio: 0";
	private String str2 = "Livello: 1";
	private String str3 = "Tempo: 60s";
	private Vector2 position;
    
    public Score(float x, float y)
    {
    	this.font = new BitmapFont();
    	this.font.scale(1.2f);
    	this.position = new Vector2(x, y);
    }

    public void draw(SpriteBatch spriteBatch)
    {
    	this.font.draw(spriteBatch, this.str1, this.position.x, this.position.y - 40);
    	this.font.draw(spriteBatch, this.str3, this.position.x + 270, this.position.y);
    	this.font.draw(spriteBatch, this.str2, this.position.x, this.position.y);
    }
    
    public void addPoints(int points)
    {
    	this.points += points;
    	this.str1 = "Punteggio: " + this.points;
    }
    
    public void resetPoints()
    {
    	this.points = 0;
    	this.str1 = "Punteggio: 0";
    }
    
    public int points()
    {
    	return this.points;
    }
    
    public void resetTime()
    {
    	this.time = ROUND_TIME;
    	this.str3 = "Tempo: " + Math.round(this.time) + "s";
    }
    
    public void setRound(int round)
    {
    	this.str2 = "Livello " + round;
    }
    
    public void updateTime(float delta)
    {
    	if (this.time > 0.1f)
    	{ 
    		this.time -= delta / 1000;
    		this.str3 = "Tempo: " + Math.round(this.time) + "s";
    	}
    }
    
    public int secondsLeft()
    {
    	return Math.round(this.time);
    }
    
    public boolean timesOut()
    {
    	if (this.time < 0)
    	{ return true; }
    	return false;
    }

}

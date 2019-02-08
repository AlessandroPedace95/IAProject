package com.sfo.games.puzzlebobble;

import com.badlogic.gdx.Gdx;

public class Preferences 
{
	private static com.badlogic.gdx.Preferences prefs = Gdx.app.getPreferences("sfo.games.puzzlebobble");
	
	public static void vibrate(boolean on)
	{
		prefs.putBoolean("vibrate", on);
		prefs.flush();
	}
	
	public static void effects(boolean on)
	{
		prefs.putBoolean("effects", on);
		prefs.flush();
	}
	
	public static void music(boolean on)
	{
		prefs.putBoolean("music", on);
		prefs.flush();
	}
	
	public static void maxScore(int score)
	{
		prefs.putInteger("maxscore", score);
		prefs.flush();
	}
	
	public static boolean vibrate() { return Preferences.prefs.getBoolean("vibrate", true); }
	public static boolean effects() { return Preferences.prefs.getBoolean("effects", true); }
	public static boolean music() 	{ return Preferences.prefs.getBoolean("music", true); }
	public static int maxScore() 	{ return Preferences.prefs.getInteger("maxscore", 0); }
	
}

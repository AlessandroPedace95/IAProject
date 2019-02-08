package com.sfo.games.puzzlebobble.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector3;
import com.sfo.games.puzzlebobble.Game;
import com.sfo.games.puzzlebobble.Preferences;
import com.sfo.games.puzzlebobble.stages.DlvRun;

public class InputHelper
{
	//aggiunto per prova
	
	public static Vector3 touch = Vector3.Zero;



	public static boolean justTouched()
	{
		if (Gdx.input.justTouched())
		{
			touch.x = Gdx.input.getX();
			touch.y = Gdx.input.getY();
			
			Game.i.camera.unproject(touch);
			return true;
		}
		return false;
	}

	public static void vibrate(int milliseconds)
	{
		if (Preferences.vibrate())
		{
			Gdx.input.vibrate(milliseconds);
		}
	}

	public static void play(Sound sound)
	{
		if (Preferences.effects())
		{
			sound.play();
		}
	}

	public static void play(Music music)
	{
		if (Preferences.music())
		{
			//music.play();
		}
	}
}
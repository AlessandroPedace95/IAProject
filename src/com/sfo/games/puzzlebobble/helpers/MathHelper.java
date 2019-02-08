package com.sfo.games.puzzlebobble.helpers;

import com.badlogic.gdx.math.MathUtils;

public class MathHelper
{
	public static float offsetX(float angle, float radius)
	{
		angle *= MathUtils.degreesToRadians;
		return (float)(Math.cos(angle) * radius);
	}

	public static float offsetY(float angle, float radius)
	{
		angle *= MathUtils.degreesToRadians;
		return (float)(Math.sin(angle) * radius);
	}

	public static double angle(float x1, float y1, float x2, float y2)
	{
		return Math.atan2(x1 - x2, y2 - y1) * 180.0 / MathUtils.PI;
	}
}

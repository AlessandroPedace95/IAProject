package com.sfo.games.puzzlebobble;
 
import com.badlogic.gdx.math.Vector2;
import com.sfo.games.puzzlebobble.entities.Sphere.Colors;

public class JsonSphere
{
	public Vector2 gridPosition;
	public Colors color;
	
	public JsonSphere()
	{
	}
	
	public JsonSphere(Colors color, Vector2 position)
	{
		this.gridPosition = position;
		this.color = color;
	}
}

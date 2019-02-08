package com.sfo.games.puzzlebobble;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.badlogic.gdx.utils.Array;
import com.sfo.games.puzzlebobble.entities.Sphere.Colors;

public class Round 
{
	private Colors initialColor;
	private Array<JsonSphere> spheres;
	
	public Colors getInitialColor()
	{
		return this.initialColor;
	}
	
	public List<JsonSphere> getSpheres()
	{
		List<JsonSphere> jspheres = new ArrayList<JsonSphere>();
		Iterator<JsonSphere> it = spheres.iterator();
		while(it.hasNext())
		{
			jspheres.add(it.next());
		}
		return jspheres;
	}
	
	public void setSpheres(List<JsonSphere> spheres)
	{
		this.spheres = new Array<JsonSphere>();
		Iterator<JsonSphere> it = spheres.iterator();
		while(it.hasNext())
		{
			this.spheres.add(it.next());
		}
	}
}
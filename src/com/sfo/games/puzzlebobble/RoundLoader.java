package com.sfo.games.puzzlebobble;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.sfo.games.puzzlebobble.entities.Sphere;
import com.sfo.games.puzzlebobble.entities.Sphere.Colors;

// Not optimized
public class RoundLoader 
{
	private Json json;
	
	public RoundLoader()
	{
		this.json = new Json();
	}
	
	public Colors loadRoundColor(int roundNumber)
	{
		Round round = json.fromJson(Round.class, Gdx.files.internal("rounds/round" + roundNumber + ".json").readString());
		return round.getInitialColor();
	}
	
	public List<Sphere> loadRound(int roundNumber)
	{
		Round round = json.fromJson(Round.class, Gdx.files.internal("rounds/round" + roundNumber + ".json").readString());
		List<Sphere> spheres = new ArrayList<Sphere>();
		Iterator<JsonSphere> it = round.getSpheres().iterator();
		while (it.hasNext())
		{
			JsonSphere jsphere = it.next();
			Sphere sphere = new Sphere(jsphere.color, jsphere.gridPosition);
			spheres.add(sphere);
			
		}
		return spheres;
	}
	
	public void saveRound(List<Sphere> spheres)
	{
		List<JsonSphere> jspheres = new ArrayList<JsonSphere>();
		Iterator<Sphere> it = spheres.iterator();
		while (it.hasNext())
		{
			Sphere sphere = it.next();
			JsonSphere jsphere = new JsonSphere(sphere.color(), sphere.gridPosition());
			jspheres.add(jsphere);
		}
		Round round = new Round();
		round.setSpheres(jspheres);
		FileHandle file = Gdx.files.external("rounds/round.json");
		file.writeString(json.toJson(round, Round.class), false);
	}
}

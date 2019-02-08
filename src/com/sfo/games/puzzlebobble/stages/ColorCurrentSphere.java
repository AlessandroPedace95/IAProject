package com.sfo.games.puzzlebobble.stages;

import com.sfo.games.puzzlebobble.entities.Sphere;
import com.sfo.games.puzzlebobble.entities.Sphere.Colors;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("currentSphere")
public class ColorCurrentSphere {
	
	@Param(0)
	private String color;
	
	public ColorCurrentSphere() {}
	
	public ColorCurrentSphere(Sphere s) {
		color = s.color().toString();
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
}

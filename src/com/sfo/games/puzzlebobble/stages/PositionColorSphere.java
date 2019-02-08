package com.sfo.games.puzzlebobble.stages;

import com.sfo.games.puzzlebobble.entities.Sphere;
import com.sfo.games.puzzlebobble.entities.Sphere.Colors;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("sphere")
public class PositionColorSphere {
	
	@Param(0)
	private int x;
	@Param(1)
	private int y;
	@Param(2)
	private String color;
	
	public PositionColorSphere() {}
	
	public PositionColorSphere(Sphere s) {
		x = (int) s.YsuMatrice();
		y = (int) s.XsuMatrice();
		color = s.color().toString();
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}

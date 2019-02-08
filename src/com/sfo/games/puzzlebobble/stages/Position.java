package com.sfo.games.puzzlebobble.stages;

import com.badlogic.gdx.math.Vector2;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("position")
public class Position {

	
	@Param(0)
	private int  x;
	@Param(1)
	private int y;
	//private Vector2 position;

	public Position() {}
	
	public Position(Vector2 position ) {
		
		//this.position = position;
		x = (int) position.x;
		y = (int) position.y;
	}
	
//	public Vector2 getPosition() {
//		return position;
//	}


//	public void setPosition(Vector2 position) {
//		this.position = position;
//	}


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

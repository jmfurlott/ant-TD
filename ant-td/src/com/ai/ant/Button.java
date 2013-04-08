package com.ai.ant;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Button {

	private boolean isSelected;
	private Vector2 position = new Vector2();
	private boolean exists = false;
	private int type; //type of the tower that will be mapped
		
	
	//let 1.0f be unit size and try not to use smaller sizes
	//private float width;
	//private float height;
	Rectangle bounds = new Rectangle();
	
	
	public Button(Vector2 position, float width, float height) {
		this.position = position;
		this.bounds.width = width;
		this.bounds.height = height;
		isSelected = false; //false on default
		exists = true;
		this.type = -1;
	}
	
	public Button(Vector2 position, float width, float height, int towerType) {
		this.position = position;
		this.bounds.width = width;
		this.bounds.height = height;
		isSelected = false; //false on default
		exists = true;
		this.type = towerType;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public boolean exists() {
		return exists;
	}
	
	public int getTowerType() {
		return type;
	}
	
	
	
	
	
}

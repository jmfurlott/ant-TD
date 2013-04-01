package com.ai.ant;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Character {

	public enum State {
		IDLE, WALKING; //and maybe more
	}
	
	public static final float SPEED = 5.0f;
	static final float SIZE = 1.0f; //compared to our uniform size
	
	Vector2 position = new Vector2();
	Vector2 acceleration = new Vector2();
	Vector2 velocity = new Vector2();
	
	Rectangle bounds = new Rectangle();
	State state = State.IDLE;
	
	
	
	public Character(Vector2 position) {
		this.position = position;
		this.bounds.height = SIZE;
		this.bounds.width = SIZE;
	}



	public Vector2 getPosition() {
		return position;
	}



	public void setPosition(Vector2 position) {
		this.position = position;
	}



	public Vector2 getAcceleration() {
		return acceleration;
	}



	public void setAcceleration(Vector2 acceleration) {
		this.acceleration = acceleration;
	}



	public Vector2 getVelocity() {
		return velocity;
	}



	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}



	public Rectangle getBounds() {
		return bounds;
	}



	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}



	public State getState() {
		return state;
	}



	public void setState(State state) {
		this.state = state;
	}
	
	
	public void update(float delta) {
		position.add(velocity.tmp().mul(delta));
	}
	
}

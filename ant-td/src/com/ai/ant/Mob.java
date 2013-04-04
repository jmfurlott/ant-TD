package com.ai.ant;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Mob {
	protected int speed;
	protected Vector2 position;
	protected Rectangle rect;
	protected int health;
	protected int target; 	//represents which player this mob is attacking, needed for pathing and conversion tower
	protected int currency; //coin given based on level 1 of mob upon destruction
	protected int point; 	//points given based on level 1 of mob upon destruction
	protected int level; 	//currency/points rewarded scale based on level
	protected float distanceToEnd;
	protected World world;
	
	public Mob(Vector2 position, World world){
		this.position = position;
		rect = new Rectangle(); //default rectangle
		this.world = world;
		//world.getMobList().add(this); //add the mob to the world list.
	}
	
	public void BuildPath(){
		//A* here?
	}
	
	public void mobDeath(){
		//world.mobList.remove(this);
		//world.getPlayer(target).getCurrency() += currency;
		//world.getPlayer(target).getPoints() += points;
	}
	
	
	/*
	 **************************
	 * GETTERS/SETTERS
	 ************************** 
	 */
	
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public Vector2 getPosition() {
		return position;
	}
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	public Rectangle getRect() {
		return rect;
	}
	public void setRect(Rectangle rect) {
		this.rect = rect;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public int getCurrency() {
		return currency;
	}
	public void setCurrency(int currency) {
		this.currency = currency;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
}

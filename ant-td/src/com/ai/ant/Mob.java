package com.ai.ant;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Mob {
	protected int speed;
	public static final float SPEED = 1.0f;
	//?do we want both? one static the other is just a scaler?
	
	protected Vector2 position;
	protected Rectangle bounds = new Rectangle();
	static final float SIZE = 1f;
	protected int health;
	protected int target; 	//represents which player this mob is attacking, needed for pathing and conversion tower
	//end is based on the current target of the mob. I'm just hard coding this for now to test the tower targeting system.
	Vector2 end = new Vector2(150,200);
	protected int currency; //coin given based on level 1 of mob upon destruction
	protected int point; 	//points given based on level 1 of mob upon destruction
	protected int level; 	//currency/points rewarded scale based on level
	protected float distanceToEnd;
	protected World world;
	Vector2 direction = new Vector2();
	
	protected boolean active = true;
	
	public Mob(Vector2 position, World world){
		this.position = position;
	
		this.world = world;
		this.position = position;
		
		this.bounds.width = SIZE;
		this.bounds.height = SIZE;
		direction.x = (end.x - position.x)/5;
		direction.y = (end.y - position.y)/5;
	}
	
	public void BuildPath(){
		//A* here?
	}
	
	public void mobDeath(){
		//world.mobList.remove(this);
		//world.getPlayer(target).getCurrency() += currency;
		//world.getPlayer(target).getPoints() += points;
	}
	
	public void update(float delta){
		
		
		if(position.equals(end)){
			//TODO: do effect
			//System.out.println("bullet at the end");
			active = false;
		}
		else{
			Vector2 temp1 = new Vector2(end.x-2, end.y-2);
			Vector2 temp2 = new Vector2(end.x+2, end.y+2);
			position.add((direction.tmp().mul(delta)));
			if(position.x >= temp1.x && position.x <temp2.x  && position.y >= temp1.y && position.y <temp2.y){
				position = end;	
			}
			
			if(position.equals(end)){
				System.out.println("mob has reached end");
				active = false;
				//TODO: do effect
				//System.out.println("bullet at the end"); 
				//world.removeBullet(this);
			}
			//System.out.println("Mob.position: x: " +position.x+ "y:"+position.y);
		}
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
	public Rectangle getBounds() {
		return bounds;
	}
	public void setRect(Rectangle bounds) {
		this.bounds = bounds;
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

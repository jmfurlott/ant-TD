package com.ai.ant;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Mob {
	protected int speed;
	public static final float SPEED = 10f;
	//?do we want both? one static the other is just a scaler?
	
	protected Vector2 position;
	protected Rectangle bounds = new Rectangle();
	static final float SIZE = 1f;
	protected int health;
	protected int target; 	//represents which player this mob is attacking, needed for pathing and conversion tower
							//end is based on the current target of the mob. I'm just hard coding this for now to test the tower targeting system.
	Vector2 end = new Vector2(200,300);
	protected int currency; //coin given based on level 1 of mob upon destruction
	protected int points; 	//points given based on level 1 of mob upon destruction
	protected int level; 	//currency/points rewarded scale based on level
	protected float distanceToEnd;
	
	protected World world;
	Vector2 direction = new Vector2();
	protected int incomingDamage =0;
	protected double angle;
	
	protected boolean deathFlag = false; //did the mob die to tower damage??
	
	protected boolean active = true;	//has mob reached the end of its path??
	
	public Mob(Vector2 position, World world){
		this.position = position;
	
		this.world = world;
		this.position = position;
		
		this.bounds.width = SIZE;
		this.bounds.height = SIZE;
		angle = Math.atan2(end.y - position.y, end.x- position.x);
		direction.x = (float) Math.cos(angle);
		direction.y = (float) Math.sin(angle);
		
	}
	
	public void BuildPath(){
		//A* here?
	}
	
	public void mobDeath(){
		world.getPlayer(target).addCurrency(currency);
		world.getPlayer(target).addPoints(points);
		System.out.println("Player1 Currency: "+world.getPlayer(target).currency + " Points: "+ world.getPlayer(target).getPoints());
	}
	
	public void removeMob(){
		world.mobList.remove(this);
	}
	
	
	public void update(float delta){
		if(active){
			if(health<=0){
				deathFlag = true;
				active=false;
				System.out.println("death");
			}
//			else if(position.equals(end)){
//				System.out.println("mob at the end1");
//				active = false;
//			}
			else{
				angle = Math.atan2(end.y - position.y, end.x- position.x);
				direction.x = (float) Math.cos(angle);
				direction.y = (float) Math.sin(angle);
				Vector2 temp1 = new Vector2(end.x-2, end.y-2);
				Vector2 temp2 = new Vector2(end.x+2, end.y+2);
				position.add(SPEED*direction.x*delta,SPEED*direction.y*delta);
				if(position.x >= temp1.x && position.x <temp2.x  && position.y >= temp1.y && position.y <temp2.y){
					position = end;	
				}
				if(position.equals(end)){
					if(health <=0){
						System.out.println("flag");
						deathFlag = true;
						active = false;
					}
					System.out.println("Broken Mob Health: "+health);
					active = false;
				}
			}
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
	public int getPoints() {
		return points;
	}
	public void setPoint(int points) {
		this.points = points;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

	public int getIncomingDamage() {
		// TODO Auto-generated method stub
		return incomingDamage;
	}

	public void setIncomingDamage(int incomingDamage) {
		this.incomingDamage = incomingDamage;
	}

	public void setDeathFlag(boolean deathFlag) {
		this.deathFlag = deathFlag;
		
	}
}

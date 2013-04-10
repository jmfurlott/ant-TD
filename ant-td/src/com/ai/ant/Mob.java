package com.ai.ant;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Mob {
	protected World world;
	protected Vector2 position;
	Vector2 end;
	protected double distanceToEnd;
	
	protected Rectangle bounds = new Rectangle();
	static final float SIZE = 1f;
	protected int health;
	protected int target; 	//represents which player this mob is attacking, needed for pathing and conversion tower
	protected int currency; //coin given based on level 1 of mob upon destruction
	protected int points; 	//points given based on level 1 of mob upon destruction
	protected int level; 	//currency/points rewarded scale based on level
	protected static final float SPEED = 10f; 	//normal speed value
	protected float speedScale = 1;   //modified when slowed
	
	protected long slowStartTime;
	protected long slowEndTime;
	protected long convertStartTime;
	protected long convertEndTime;
	protected boolean convertedFlag;
	
	
	Vector2 direction = new Vector2();
	protected int incomingDamage = 0; //used in targeting system
	
	protected double angle; //used for time based movement
	protected boolean deathFlag = false;  //is the mob dead?
	protected boolean active = true;	 //has mob reached the end of its path?
	
	public Mob(Vector2 position, World world){
		this.position = position;
		this.world = world;
		this.position = position;
		this.bounds.width = SIZE;
		this.bounds.height = SIZE;
		if(target==1){ //enemy
			end = new Vector2(200,300);
		}
		else{ //friendly
			end = new Vector2(400,10);
		}
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
	
	public void setSlowStartTime(long slowStartTime){
		this.slowStartTime = slowStartTime;
	}
	
	public void update(float delta){
		if(active){
		 			
			if(speedScale<1 && System.currentTimeMillis()>slowEndTime){
				slowStartTime =0;
				slowEndTime =0;
				speedScale = 1;
			}
			if(convertedFlag == true){
				if(System.currentTimeMillis() > convertEndTime){
					convertedFlag = false;
					target=1;
				}
			}
			if(target==1){
				end = new Vector2(200,300);
			}
			else
				end = new Vector2(400,10);
			
			if(health<=0){
				deathFlag = true;
				active=false;
			}
			else{
				double aSqu = (end.x - position.x)* (end.x - position.x);
				double bSqu = (end.y - position.y)* (end.y - position.y);
				distanceToEnd = aSqu + bSqu;
				angle = Math.atan2(end.y - position.y, end.x- position.x);
				direction.x = (float) Math.cos(angle);
				direction.y = (float) Math.sin(angle);
				Vector2 temp1 = new Vector2(end.x-2, end.y-2);
				Vector2 temp2 = new Vector2(end.x+2, end.y+2);
				position.add(SPEED*speedScale*direction.x*delta,SPEED*speedScale*direction.y*delta);
				if(position.x >= temp1.x && position.x <temp2.x  && position.y >= temp1.y && position.y <temp2.y){
					position = end;	
				}
				if(position.equals(end)){
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
	
	public float getSpeedScale() {
		return speedScale;
	}
	public void setSpeed(float speedScale) {
		this.speedScale = speedScale;
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

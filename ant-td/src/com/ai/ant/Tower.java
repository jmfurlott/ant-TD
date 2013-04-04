package com.ai.ant;

import java.util.ArrayList;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Tower {
	
	protected Vector2 position;
	protected Rectangle rect;
	protected ArrayList<Effect> effect;
	protected int range;
	protected String name;
	protected int level;
	protected int fireRate;  
	/*
	 * fireRate delay(second)
	 * 1		.1
	 * 2		.2
	 * 3		.3
	 * .		 .
	 * .		 .
	 * 
	 */
	protected int damage;
	protected int cost;
	protected Mob currentTarget;
	protected World world;
	protected ArrayList<Mob> targetList;
	
	public Tower(Vector2 position, World world){
		this.world = world;
		this.position = position;
		this.rect = new Rectangle(); //default rectangle?
		effect = null;
		targetList = new ArrayList<Mob>();
		currentTarget = null;
	}
	
	//making this abstract to gain control of damage scaling across each type of tower
	abstract void levelUp();
	
	public void update(){
		towerAI();
	}
	
	public void towerAI(){
		boolean active = true;
		while(active){
			getTarget();
			if(currentTarget!=null){
				fire();
			}			
		}
	}
	
	public void getTarget(){
		double r;
		 targetList.clear();
		 for(Mob pmob: world.mobList){
			 double aSqu = (pmob.position.x - position.x)* (pmob.position.x - position.x);
			 double bSqu = (pmob.position.y - position.y)* (pmob.position.y - position.y);
			 r =  Math.sqrt(aSqu+bSqu);// r = squrt(a^2 + b^2)
		  	 if(r < range){ //if the mob is in range
		      targetList.add(pmob); //add it to the list of mobs I'm interested in
		  	 }
		  }
		  
		 //update currentTarget based on distanceToEnd. which is the path remaining using A* for a given mob.
		  for(Mob pmob: targetList){ 
		  	  if(pmob.distanceToEnd < currentTarget.distanceToEnd){
		       currentTarget = pmob;
		     }  
		  }
		 
	}
	
	public void fire(){
		if(currentTarget!=null){
			shoot();
			//use time stamp.
		}
		else{
			getTarget();
		}
	}
	
	private void shoot(){
		//fires or pulses its effects in an area around this tower;
		//check collision detection.
		System.out.println("FIRE!!!");
	}
	
	/*
	 **************************
	 * GETTERS/SETTERS
	 ************************** 
	 */
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
	public ArrayList<Effect> getEffect() {
		return effect;
	}
	public void setEffect(ArrayList<Effect> effect) {
		this.effect = effect;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getFireRate() {
		return level;
	}
	public void setFireRate(int fireRate) {
		this.fireRate = fireRate;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost= cost;
	}
}

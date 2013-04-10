package com.ai.ant;

import java.util.ArrayList;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Tower {

	protected Vector2 position;
	protected Rectangle bounds = new Rectangle();
	protected int range;
	protected String name;
	protected int level;
	static final float SIZE = 1f;
	protected int fireRate;
	protected int damage;
	protected int cost;
	protected Mob currentTarget;
	protected World world;
	protected ArrayList<Mob> targetList;
	protected int owner;
	protected int splashRange;
	
	protected double slowAmount;
	protected long slowTime;
	protected long conversionDelay;
	
	protected int type;

	protected boolean active;
	protected long firedTime;
	protected long activeTime;
	protected long delay;

	public Tower(Vector2 position, World world, int owner) {
		this.world = world;
		this.owner = owner;
		this.position = position;
		this.bounds.width = SIZE;
		this.bounds.height = SIZE;
		targetList = new ArrayList<Mob>();
		currentTarget = null;
		range = 1000;
		firedTime = 0;
		activeTime = 0;
		active = true;
		delay = 1000;
	}

	// making this abstract to gain control of damage scaling across each type
	// of tower
	abstract void levelUp();

	abstract void shoot();

	public void update() {
		towerAI();
	}

	public void towerAI() {
		getTarget();
		if (currentTarget != null) {
			fire();
		}
	}

	public void getTarget() {
		currentTarget = null;
		for (Mob pmob : world.mobList) {
			if(pmob.target==owner){
				if((pmob.getHealth() - pmob.getIncomingDamage()) > 0){
					double distanceFromTowerMag = (pmob.position.x - position.x)* (pmob.position.x - position.x) + (pmob.position.y - position.y)* (pmob.position.y - position.y);
					if (distanceFromTowerMag < (range*range)) { // if the mob is in range
						if(pmob.deathFlag){}//mob is dead don't evaluate it again
						else if (currentTarget != null) {
							if (pmob.distanceToEnd < currentTarget.distanceToEnd) {
								currentTarget = pmob;
							}
						} 
						else {
							currentTarget = pmob;
						}
					}
				}
			}
		}
	}

	public void fire() {
		checkIsReady();
		if (currentTarget != null) {
			if (active) { // tower can fire again.
				shoot();	//spawns a bullet
				isFired();  //sets active to false
			}
		} 
		else {
			getTarget();
		}
	}
	
	public void isFired() {
		if (active == false) {
		} 
		else {
			active = false;
			firedTime = System.currentTimeMillis();
			activeTime = firedTime + delay;
		}
	}

	public void checkIsReady() {
		if (active == false) {
			if (System.currentTimeMillis() > activeTime) {
				active = true;
			}
		}
	}

	/*
	 * *************************
	 * GETTERS/SETTERS*************************
	 */
	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
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
		this.cost = cost;
	}
	
	public int getDamage(){
		return damage;
	}
	
}

package com.ai.ant;

import com.badlogic.gdx.math.Vector2;

public class spawnTower extends Tower{
	private final int maxLevel;
	private int mobLevel;
	private int mobTarget;
	public spawnTower(Vector2 position, World world, int owner, int mobLevel,int mobTarget) {
		super(position, world, owner);
//		//effect = new ArrayList<Effect>(); basicTower has no effects
//		//effect.add(); 
		maxLevel = 3;
		this.mobLevel = mobLevel;
		this.mobTarget = mobTarget;
//		damage = 1;
//		level = 1;
//		range = 10; //this is just a temp. value
//		fireRate = 1; //attacks per second
		delay = 5000;
		type = 6;
	}

	void levelUp() {
		if(level == maxLevel){}
		else{
			level++;
			damage += 1;
			range +=5;
			fireRate += .25;
		}
	}
	@Override
	public void towerAI(){
			fire();	
	}
	@Override
	public void fire(){
		checkIsReady();
		if(active){
			shoot();
			isFired();
		}
	}
	
	void shoot(){
		BasicMob bMob = new BasicMob(new Vector2(this.position.x,this.position.y),mobTarget,mobLevel,world);
		world.mobList.add(bMob);
		//System.out.println("mobReleased");
	}
}

package com.ai.ant;

import com.badlogic.gdx.math.Vector2;

public class spawnTower extends Tower{
	private final int maxLevel;
	public spawnTower(Vector2 position, World world) {
		super(position, world);
//		//effect = new ArrayList<Effect>(); basicTower has no effects
//		//effect.add(); 
		maxLevel = 3;
//		damage = 1;
//		level = 1;
//		range = 10; //this is just a temp. value
//		fireRate = 1; //attacks per second
		delay = 5000;
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
		BasicMob bMob = new BasicMob(new Vector2(this.position.x,this.position.y), 1, 1,world);
		world.mobList.add(bMob);
		System.out.println("mobReleased");
	}
}

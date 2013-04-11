package com.ai.ant;

import com.badlogic.gdx.math.Vector2;

public class SpawnTower extends Tower{
	private final int maxLevel;
	private int mobLevel;
	private int mobTarget;
	public SpawnTower(Vector2 position, World world, int owner, int mobLevel,int mobTarget) {
		super(position, world, owner);
		maxLevel = 3;
		this.mobLevel = mobLevel;
		this.mobTarget = mobTarget;
		delay = 20000;
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
	}
}

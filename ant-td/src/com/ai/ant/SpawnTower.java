package com.ai.ant;

import com.badlogic.gdx.math.Vector2;

public class SpawnTower extends Tower{
	private final int maxLevel;
	private int mobLevel;
	private int mobTarget;
	private int timer = 0;
	private int timerLength = 1000/(level+1);
	public SpawnTower(Vector2 position, World world, int owner, int mobLevel,int mobTarget, int x, int y) {
		super(position, world, owner,x,y);
		maxLevel = 3;
		this.mobLevel = mobLevel;
		this.mobTarget = mobTarget;
		delay =  1000;
		type = 6;
		active = false;
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
		
		if(timer == 0){
			checkIsReady();
			if(active){
				shoot();
				isFired();
			}
			timer++;
			
		}
		else if(timer == timerLength){
			timer = 0;
		}
		else{
			timer++;
		}
		
	}
	
	void shoot(){
		BasicMob bMob = new BasicMob(new Vector2(this.position.x+13,this.position.y-10),mobTarget,mobLevel,world);
		world.mobList.add(bMob);
	}
}

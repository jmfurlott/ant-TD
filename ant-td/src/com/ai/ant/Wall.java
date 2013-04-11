package com.ai.ant;

import com.badlogic.gdx.math.Vector2;

public class Wall extends Tower{
	private final int maxLevel;
	public Wall(Vector2 position, World world, int owner) {
		super(position, world, owner);
		//effect = new ArrayList<Effect>(); basicTower has no effects
		//effect.add(); 
		maxLevel = 3;
		damage = 3;
		level = 1;
		range = 100; //this is just a temp. value
		fireRate = 1; //attacks per second
		type = 1;
	}

	void levelUp() {
		if(level == maxLevel){}
		else{
			level++;
			damage += 1;
			range += 5;
			fireRate += .25;
		}
	}
	
	@Override
	public void towerAI(){
		
	}
	
	void shoot(){
	}
}

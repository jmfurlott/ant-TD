package com.ai.ant;

import com.badlogic.gdx.math.Vector2;

public class BasicTower extends Tower{
	private final int maxLevel;
	public BasicTower(Vector2 position, World world, int owner) {
		super(position, world, owner);
		//effect = new ArrayList<Effect>(); basicTower has no effects
		//effect.add(); 
		maxLevel = 3;
		damage = 2;
		level = 1;
		range = 100; //this is just a temp. value
		fireRate = 1; //attacks per second
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
	
	void shoot(){
			currentTarget.setIncomingDamage(currentTarget.getIncomingDamage()+damage);
			Bullet bullet = new Bullet(new Vector2(this.getPosition().x,this.getPosition().y), new Vector2(currentTarget.getPosition().x,currentTarget.getPosition().y), world,currentTarget,this);
			world.bulletList.add(bullet);
	}
}

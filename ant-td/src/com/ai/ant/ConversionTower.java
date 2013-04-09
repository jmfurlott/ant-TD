package com.ai.ant;

import com.badlogic.gdx.math.Vector2;

public class ConversionTower extends Tower{
	private final int maxLevel;
	public ConversionTower(Vector2 position, World world, int owner) {
		super(position, world, owner);
		//effect = new ArrayList<Effect>(); basicTower has no effects
		//effect.add(); 
		maxLevel = 3;
		damage = 10000000;
		level = 1;
		range = 100; //this is just a temp. value
		fireRate = 1; //attacks per second
		type = 5;
	}

	void levelUp() {
		
	}
	
	void shoot(){
			currentTarget.setIncomingDamage(currentTarget.getIncomingDamage()+damage);
			Bullet bullet = new Bullet(new Vector2(this.getPosition().x,this.getPosition().y), new Vector2(currentTarget.getPosition().x,currentTarget.getPosition().y), world,currentTarget,this);
			world.bulletList.add(bullet);
	}
}
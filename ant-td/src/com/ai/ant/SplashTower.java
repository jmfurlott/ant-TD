package com.ai.ant;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class SplashTower extends Tower {
	private final int maxLevel;
	
	
	public SplashTower(Vector2 position, World world, int owner, int x, int y) {
		super(position, world, owner,x,y);

		maxLevel = 3; 
		damage = 10;
		level = 1;
		range = 100; //this is just a temp. value
		splashRange = 20;
		fireRate = 1; //attacks per second
		type = 4;
		delay = 1500;
	}

	void levelUp(){
		if(level == maxLevel){} //if maxLevel you can't upgrade any more!!
		else if(world.getPlayer(owner).getCurrency()-cost <0){
			
		}
		else{
			world.getPlayer(owner).addCurrency(-(cost));
			level++;
			damage += damage;
			range +=25;
			delay -= 250;
			fireRate += .25;
			cost+=cost;
		}
	}
	
	
	@Override
	void shoot() {
		if(currentTarget!=null){
			currentTarget.setIncomingDamage(currentTarget.getIncomingDamage()+damage);			
			Bullet bullet = new Bullet(new Vector2(this.getPosition().x,this.getPosition().y), new Vector2(currentTarget.getPosition().x,currentTarget.getPosition().y), world,currentTarget,this);
			world.bulletList.add(bullet);
		}
	}
}
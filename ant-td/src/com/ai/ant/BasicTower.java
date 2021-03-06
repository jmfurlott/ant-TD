package com.ai.ant;

import com.badlogic.gdx.math.Vector2;

public class BasicTower extends Tower{
	private final int maxLevel;
	public BasicTower(Vector2 position, World world, int owner, int x, int y) {
		super(position, world, owner,x,y);
		//effect = new ArrayList<Effect>(); basicTower has no effects
		//effect.add(); 
		maxLevel = 3;
		damage = 15;
		level = 1;
		range = 100; //this is just a temp. value
		fireRate = 1; //attacks per second
		type = 1;
		delay = 1000;
	}

	void levelUp() {
		if(level == maxLevel){
			
		}
		else if(world.getPlayer(owner).getCurrency()-cost <0){
			
		}
		else{
			world.getPlayer(owner).addCurrency(-(cost));
			level++;
			damage += damage; ///TODO: 
			range += 25;
			delay -=250;
			cost += cost;
		}
	}
	
	void shoot(){
			currentTarget.setIncomingDamage(currentTarget.getIncomingDamage()+damage);
			Bullet bullet = new Bullet(new Vector2(this.getPosition().x,this.getPosition().y), new Vector2(currentTarget.getPosition().x,currentTarget.getPosition().y), world,currentTarget,this);
			world.bulletList.add(bullet);
	}
}

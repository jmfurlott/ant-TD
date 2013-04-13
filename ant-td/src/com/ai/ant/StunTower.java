package com.ai.ant;

import com.badlogic.gdx.math.Vector2;

public class StunTower extends Tower {
	private final int maxLevel;
	
	public StunTower(Vector2 position, World world, int owner, int x, int y) {
		super(position, world, owner,x,y);

		maxLevel = 3; 
		damage = 1;
		level = 1;
		range = 50; //this is just a temp. value
		splashRange = 50;
		fireRate = 1; //attacks per second
		type = 3;
		slowTime = 1000; 
		slowAmount = 1;  //stun
		delay = 1000; //1 attack per second.
	}

	void levelUp(){
		if(level == maxLevel){} //if maxLevel you can't upgrade any more!!
		else{
			level++;
			damage += 2;
			range +=5;
			fireRate += .25;
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
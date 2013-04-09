package com.ai.ant;
import com.badlogic.gdx.math.Vector2;

public class SlowTower extends Tower {
	private final int maxLevel;
	
	
	public SlowTower(Vector2 position, World world, int owner) {
		super(position, world, owner);
		slowAmount = .50;
		maxLevel = 3; 
		damage = 1;
		level = 1;
		range = 100; //this is just a temp. value
		splashRange = 30;
		fireRate = 1; //attacks per second
		type = 2;
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
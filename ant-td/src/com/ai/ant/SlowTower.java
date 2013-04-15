package com.ai.ant;
import com.badlogic.gdx.math.Vector2;

public class SlowTower extends Tower {
	private final int maxLevel;
	
	
	public SlowTower(Vector2 position, World world, int owner, int x, int y) {
		super(position, world, owner,x,y);
		slowTime = 1000;
		slowAmount = .50;
		maxLevel = 3; 
		damage = 5;
		level = 1;
		range = 100; //this is just a temp. value
		splashRange = 30;
		fireRate = 1; //attacks per second
		type = 2;
		delay = 1500;
	}

	void levelUp(){
		if(level == maxLevel){} //if maxLevel you can't upgrade any more!!
		else if(world.getPlayer(owner).getCurrency()-cost <0){
			
		}
		else{
			world.getPlayer(owner).addCurrency(-(cost));
			level++;
			damage += damage; //double its damage
			range +=5;
			fireRate += .25;
			delay -=250;
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
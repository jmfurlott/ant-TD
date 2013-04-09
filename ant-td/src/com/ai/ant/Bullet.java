package com.ai.ant;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
	protected Vector2 position;
	protected Vector2 end;
	protected Rectangle bounds = new Rectangle();
	static final float SIZE = 1f;
	public static final float SPEED = 200.0f; //bullet speed~
	protected World world;
	protected int speed;
	Vector2 direction = new Vector2();
	boolean active = true;
	Mob target = null;
	Tower tower = null;
	
	public Bullet(Vector2 position, Vector2 end, World world,Mob target, Tower tower){
		this.target = target;
		this.tower = tower;
		this.world = world;
		this.position = position;
		this.end = end;
		this.bounds.width = SIZE;
		this.bounds.height = SIZE;
		double angle = Math.atan2(end.y - position.y, end.x- position.x);
		direction.x = (float) Math.cos(angle);
		direction.y = (float) Math.sin(angle);
		
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public void update(float delta){
		Vector2 temp1 = new Vector2(end.x-2, end.y-2);
		Vector2 temp2 = new Vector2(end.x+2, end.y+2);
		position.add(SPEED*direction.x*delta,SPEED*direction.y*delta);
		if(position.x >= temp1.x && position.x <temp2.x  && position.y >= temp1.y && position.y <temp2.y){
			position = end;	
		}
		if(position.equals(end)){
			if(!(tower instanceof SplashTower)) 
				singleDamage();
			else
				splashDamage();
		}
	}
	
	public void singleDamage(){
		active = false;
		if(target!=null){
			target.setHealth(target.getHealth() - tower.getDamage());
			target.setIncomingDamage(target.getIncomingDamage()-tower.getDamage());
		}
	}
	
	public void splashDamage(){
		target.setIncomingDamage(target.getIncomingDamage()-tower.getDamage());
		active = false;
		for (Mob pmob : world.mobList) {
			if(pmob.active){
				if(tower.currentTarget!=null){
					double aSqu = (pmob.position.x - tower.currentTarget.position.x)* (pmob.position.x - tower.currentTarget.position.x);
					double bSqu = (pmob.position.y - tower.currentTarget.position.y)* (pmob.position.y - tower.currentTarget.position.y);
					double r = Math.sqrt(aSqu + bSqu);
					if (r < tower.splashRange) {
						pmob.setHealth(pmob.getHealth()-tower.damage);
					}
				}
			}
		}
	}	
}

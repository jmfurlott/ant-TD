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
			if(tower instanceof BasicTower) 
				basicDamage();
			else if(tower instanceof SplashTower){
				splashDamage();
			}
			else if(tower instanceof SlowTower){
				slowDamage();
			}
			else if(tower instanceof ConversionTower){
				conversion();
			}
			else if(tower instanceof StunTower){
				slowDamage(); //33%chance to stun
			}
		}
	}
	
	public void conversion(){
		active = false;
		if(target!=null){
			if(target.target == 1){
				target.target = 0;
				target.convertedFlag = true;
				target.convertStartTime = System.currentTimeMillis();
				target.convertEndTime = target.convertStartTime + tower.conversionDelay;
			}
			target.setIncomingDamage(target.getIncomingDamage()-tower.getDamage());
		}
	}
	public void basicDamage(){
		active = false;
		target.setHealth(target.getHealth() - tower.getDamage());
		target.setIncomingDamage(target.getIncomingDamage()-tower.getDamage());
	}
	public void splashDamage(){
		target.setIncomingDamage(target.getIncomingDamage()-tower.getDamage());
		active = false;
		for (Mob pmob : world.mobList){
			if(pmob.active && pmob.target == tower.owner){
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
	public void slowDamage(){
		target.setIncomingDamage(target.getIncomingDamage()-tower.getDamage());
		active = false;
		for (Mob pmob : world.mobList){
			if(pmob.active && pmob.target == tower.owner){
				if(tower.currentTarget!=null){
					double aSqu = (pmob.position.x - tower.currentTarget.position.x)* (pmob.position.x - tower.currentTarget.position.x);
					double bSqu = (pmob.position.y - tower.currentTarget.position.y)* (pmob.position.y - tower.currentTarget.position.y);
					double r = Math.sqrt(aSqu + bSqu);					
					if (r < tower.splashRange) {
						pmob.setHealth(pmob.getHealth()-tower.damage);
						if(pmob.speedScale == 1){
							if(tower.slowAmount>0){
								if(tower instanceof SlowTower){
									pmob.setSlowStartTime(System.currentTimeMillis());
									pmob.slowEndTime = pmob.slowStartTime + tower.slowTime;
									pmob.speedScale -= tower.slowAmount;
								}
								else{//StunTower w/ a 33% chance to stun
									int num = (int)(Math.random()*100);
									num = num%3;
									System.out.println(num);
									if(num == 1){
										pmob.setSlowStartTime(System.currentTimeMillis());
										pmob.slowEndTime = pmob.slowStartTime + tower.slowTime;
										pmob.speedScale -= tower.slowAmount;
									}
								}
							}
						}
					}
				}
			}
		}
	}	
}

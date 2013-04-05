package com.ai.ant;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
	protected Vector2 position;
	protected Vector2 end;
	protected Rectangle bounds = new Rectangle();
	static final float SIZE = 1f;
	public static final float SPEED = 100.0f; //bullet speed~
	protected World world;
	protected int speed;
	Vector2 direction = new Vector2();
	boolean active = true;
	
	public Bullet(Vector2 position, Vector2 end, World world){
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
		
		
		if(position.equals(end)){
			//TODO: do effect
			//System.out.println("bullet at the end");
			active = false;
		}
		else{
			Vector2 temp1 = new Vector2(end.x-2, end.y-2);
			Vector2 temp2 = new Vector2(end.x+2, end.y+2);
			position.add(SPEED*direction.x*delta,SPEED*direction.y*delta);
			if(position.x >= temp1.x && position.x <temp2.x  && position.y >= temp1.y && position.y <temp2.y){
				position = end;	
			}
			
			if(position.equals(end)){
				active = false;
				//TODO: do effect
				//System.out.println("bullet at the end"); 
				//world.removeBullet(this);
			}
			//System.out.println("Bullet.position: x: " +position.x+ "y:"+position.y);
		}
	}
}

package com.ai.ant;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
	protected Vector2 position;
	protected Vector2 end;
	protected Rectangle bounds = new Rectangle();
	static final float SIZE = 1f;
	public static final float SPEED = 1.0f;
	protected World world;
	protected int speed;
	Vector2 direction = new Vector2();
	
	public Bullet(Vector2 position, Vector2 end, World world){
		this.world = world;
		this.position = position;
		this.end = end;
		this.bounds.width = SIZE;
		this.bounds.height = SIZE;
		direction.x = end.x - position.x;
		direction.y = end.y - position.y;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public void update(float delta){
		
		
		if(position.equals(end)){
			//TODO: do effect
			//System.out.println("bullet at the end");
			world.removeBullet(this);
		}
		else{
			Vector2 temp1 = new Vector2(end.x-2, end.y-2);
			Vector2 temp2 = new Vector2(end.x+2, end.y+2);
			position.add(direction.tmp().mul(delta));
			if(position.x >= temp1.x && position.x <temp2.x  && position.y >= temp1.y && position.y <temp2.y){
				position = end;	
			}
			
			if(position.equals(end)){
				//TODO: do effect
				//System.out.println("bullet at the end"); 
				//removeBullet();
			}
			System.out.println("Bullet.position: x: " +position.x+ "y:"+position.y);
		}
	}
}

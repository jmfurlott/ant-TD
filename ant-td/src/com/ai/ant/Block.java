package com.ai.ant;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Block {
	//Blocks for now will just help represent positions and grids
	//in the world.  We can set a sprite/object/anything to a block
	//and it will all be uniform size.
	//this helps the most with collision control as well

	static final float SIZE = 1f; // 1f will be the uniform size
	Vector2 position = new Vector2();
	Rectangle bounds = new Rectangle();
	
	public Block(Vector2 pos) { 
		this.position = pos;
		this.bounds.width = SIZE;
		this.bounds.height = SIZE;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
}

package com.ai.ant;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class World {

	//build a list of blocks
	Array<Block> blocks = new Array<Block>();
	
	//would define any other sprite here!!!!
	//all ants
	Character character;
	
	public Array<Block> getBlocks() {
		return blocks;
	}
	
	public World() {
		createWorld();
	}
	
	public void createWorld() {

		for(int i = 0; i < 50; i++) {
			for(int j = 0; j < 50; j++) {
				blocks.add(new Block(new Vector2(i,j))); //blocks everything for now
				
			}
		}
		
		character = new Character(new Vector2(25,25));
	}
	
	public Character getCharacter() {
		return character;
	}
	
	
}

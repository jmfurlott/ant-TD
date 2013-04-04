package com.ai.ant;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class World {

	//build a list of blocks
	Array<Block> blocks = new Array<Block>();
	ArrayList<Mob> mobList = new ArrayList<Mob>();
	ArrayList<Tower> towerList = new ArrayList<Tower>();
	ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
	//would define any other sprite here!!!!
	//all ants
	Character character;
	Character wall;
	
	
	public Array<Block> getBlocks() {
		return blocks;
	}
	
	public ArrayList<Tower> getTowers(){
		return towerList;
	}
	
	public ArrayList<Bullet> getBullets(){
		return bulletList;
	}
	
	public World() {
		createWorld();
	}
	
	public void createWorld() {
		//temp towers for testing
		Tower tempTower1 = new BasicTower(new Vector2(50, 50),this);
		placeTower(tempTower1);
		Tower tempTower2 = new BasicTower(new Vector2(100, 50),this);
		placeTower(tempTower2);
		Tower tempTower3 = new BasicTower(new Vector2(150, 50),this);
		placeTower(tempTower3);
		Tower tempTower4 = new BasicTower(new Vector2(200, 200),this);
		placeTower(tempTower4);
		Tower tempTower5 = new BasicTower(new Vector2(50, 200),this);
		placeTower(tempTower5);
		Tower tempTower6 = new BasicTower(new Vector2(100, 200),this);
		placeTower(tempTower6);
		Tower tempTower7 = new BasicTower(new Vector2(150, 200),this);
		placeTower(tempTower7);
		Tower tempTower8 = new BasicTower(new Vector2(200, 50),this);
		placeTower(tempTower8);
		
		
		
		
		for(int i = 0; i < 50; i++) {
			for(int j = 0; j < 50; j++) {
				blocks.add(new Block(new Vector2(i,j))); //blocks everything for now
				
			}
		}
		
		character = new Character(new Vector2(25,25));
		wall = new Character(new Vector2(25, 15));
	}
	
	public void placeTower(Tower tower){
		towerList.add(tower);
	}
	
	public void removeBullet(Bullet bullet){
		if(bulletList.contains(bullet)){
			//this is broken atm. the arrayList that the bullet is trying to be removed from is changing when i call .remove(bullet)
			//i need to find a way to remove the bullet from the list.
			//bulletList.remove(0);
		}
	}
	
	public Character getCharacter() {
		return character;
	}
	
	public Character getWall() {
		return wall;
	}
	
	
}

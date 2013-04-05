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
	
	public ArrayList<Mob> getMobs(){
		return mobList;
	}
	
	public World() {
		createWorld();
	}
	
	public void createWorld() {
		//temp towers for testing

		Tower bt1 = new BasicTower(new Vector2(200, 100),this);
		placeTower(bt1);
		
		Tower bt2 = new BasicTower(new Vector2(200, 150),this);
		placeTower(bt2);
		Tower bt3 = new BasicTower(new Vector2(200, 175),this);
		placeTower(bt3);
		Tower bt4 = new BasicTower(new Vector2(200, 200),this);
		placeTower(bt4);

		spawnTower st1 = new spawnTower(new Vector2(100,10), this);
		placeTower(st1);
		spawnTower st2 = new spawnTower(new Vector2(125,20), this);
		placeTower(st2);
		spawnTower st3 = new spawnTower(new Vector2(75,30), this);
		placeTower(st3);
		spawnTower st4 = new spawnTower(new Vector2(150,40), this);
		placeTower(st4);
		spawnTower st5 = new spawnTower(new Vector2(175,50), this);
		placeTower(st5);
		
		
		for(int i = 0; i < 50; i++) {
			for(int j = 0; j < 50; j++) {
				blocks.add(new Block(new Vector2(i,j))); //blocks everything for now
				
			}
		}
		
		character = new Character(new Vector2(25,25));
		wall = new Character(new Vector2(25, 15));
	}
	
	public void placeMob(Mob mob){
		mobList.add(mob);
	}
	
	
	public void placeTower(Tower tower){
		towerList.add(tower);
	}
	
	
	public Character getCharacter() {
		return character;
	}
	
	public Character getWall() {
		return wall;
	}
	
	
}

package com.ai.ant;

import java.util.ArrayList;
import java.util.Random;

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
	Menu menu;
	
	Player player1 = new Player();
	Player player2 = new Player();
	
	
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
		menu = new Menu();
//		for(int i = 100; i < 250; i+=20){
//			Tower bt0 = new BasicTower(new Vector2(i, 160),this, 1);
//			placeTower(bt0);
//		}
//		float Xmax = 300;
//		float Xmin = 200;
//		float Ymax = 200;
//		float Ymin = 150;
//		for(int i = 0;i <5;i++){
//			float rand1 = Xmin + (float)(Math.random()*(Xmax-Xmin));
//			float rand2 = Ymin + (float)(Math.random()*(Ymax-Ymin));
//			Tower st1 = new SlowTower(new Vector2(rand1,rand2), this, 1);
//			placeTower(st1);
//		}
//		
//		Xmax = 400;
//		Xmin = 100;
//		Ymax = 100;
//		Ymin = 10;
//		for(int i = 0;i <20;i++){
//			float rand1 = Xmin + (float)(Math.random()*(Xmax-Xmin));
//			float rand2 = Ymin + (float)(Math.random()*(Ymax-Ymin));
//			SpawnTower st1 = new SpawnTower(new Vector2(rand1,rand2), this,0,1,1);
//			//spawnTower st2 = new spawnTower(new Vector2(rand1,rand2), this,1,1,0);
//			placeTower(st1);
//			//placeTower(st2);
//		}
//	
		
		for(int i = 0; i < 50; i++) {
			for(int j = 0; j < 50; j++) {
				blocks.add(new Block(new Vector2(i,j))); //blocks everything for now
				
			}
		}
		
		character = new Character(new Vector2(25,25));
		wall = new Character(new Vector2(25, 15));
	}
	
	public Player getPlayer(int target){
		if(target ==1){
			return player1;
		}
		else 
			return player2;
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
	
	public Menu getMenu() {
		return menu;
	}
	
}

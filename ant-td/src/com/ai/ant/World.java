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
		
		
//		Tower bt0 = new BasicTower(new Vector2(200, 100),this,1);
//		placeTower(bt0);
//		Tower bt1 = new BasicTower(new Vector2(200, 125),this,1);
//		placeTower(bt1);		
//		Tower bt2 = new BasicTower(new Vector2(200, 150),this,1);
//		placeTower(bt2);
//		Tower bt3 = new BasicTower(new Vector2(200, 175),this,1);
//		placeTower(bt3);
//		Tower bt4 = new BasicTower(new Vector2(200, 200),this,1);
//		placeTower(bt4);
//		Tower bt5 = new BasicTower(new Vector2(205, 100),this,1);
//		placeTower(bt5);
//		Tower bt6 = new BasicTower(new Vector2(205, 125),this,1);
//		placeTower(bt6);		
//		Tower bt7 = new BasicTower(new Vector2(205, 150),this,1);
//		placeTower(bt7);
//		Tower bt8 = new BasicTower(new Vector2(205, 175),this,1);
//		placeTower(bt8);
//		Tower bt9 = new BasicTower(new Vector2(205, 200),this,1);
//		placeTower(bt9);

		SplashTower sp1 = new SplashTower(new Vector2(150, 180),this,1);
		placeTower(sp1);
		SplashTower sp2 = new SplashTower(new Vector2(110, 130),this,1);
		placeTower(sp2);
		SplashTower sp3 = new SplashTower(new Vector2(150, 160),this,1);
		placeTower(sp3);
		SplashTower sp4 = new SplashTower(new Vector2(150, 140),this,1);
		placeTower(sp4);
	
		
		
		spawnTower st1 = new spawnTower(new Vector2(100,10), this,0,1,1);
		placeTower(st1);
		spawnTower st2 = new spawnTower(new Vector2(102,10), this,0,1,1);
		placeTower(st2);
		spawnTower st3 = new spawnTower(new Vector2(104,10), this,0,1,1);
		placeTower(st3);
		spawnTower st4 = new spawnTower(new Vector2(106,10), this,0,1,1);
		placeTower(st4);
		spawnTower st5 = new spawnTower(new Vector2(108,10), this,0,1,1);
		placeTower(st5);
		spawnTower st6 = new spawnTower(new Vector2(110,10), this,0,1,1);
		placeTower(st6);
		
		spawnTower st7 = new spawnTower(new Vector2(180,20), this,0,1,1);
		placeTower(st7);
		spawnTower st8 = new spawnTower(new Vector2(182,20), this,0,1,1);
		placeTower(st8);
		spawnTower st9 = new spawnTower(new Vector2(184,20), this,0,1,1);
		placeTower(st9);
		spawnTower st10 = new spawnTower(new Vector2(186,20), this,0,1,1);
		placeTower(st10);
		spawnTower st11 = new spawnTower(new Vector2(188,20), this,0,1,1);
		placeTower(st11);
		spawnTower st12 = new spawnTower(new Vector2(190,20), this,0,1,1);
		placeTower(st12);
		
		spawnTower st13 = new spawnTower(new Vector2(140,20), this,0,1,1);
		placeTower(st13);
		spawnTower st14 = new spawnTower(new Vector2(142,20), this,0,1,1);
		placeTower(st14);
		spawnTower st15 = new spawnTower(new Vector2(144,20), this,0,1,1);
		placeTower(st15);
		spawnTower st16 = new spawnTower(new Vector2(146,20), this,0,1,1);
		placeTower(st16);
		spawnTower st17 = new spawnTower(new Vector2(148,20), this,0,1,1);
		placeTower(st17);
		spawnTower st18 = new spawnTower(new Vector2(200,20), this,0,1,1);
		placeTower(st18);
		
		
		
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

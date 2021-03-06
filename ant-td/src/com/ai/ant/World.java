package com.ai.ant;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.sun.xml.internal.bind.v2.runtime.Coordinator;

public class World {

	//build a list of blocks
	Array<Block> blocks = new Array<Block>();
	ArrayList<Mob> mobList = new ArrayList<Mob>();
	ArrayList<Tower> towerList = new ArrayList<Tower>();
	ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
	int x = 21;
	int y = 21;
	int[][] grid = new int[x][y];
	int[][] gridConverted = new int[x][y];
	
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
	
	public int getLocation(int x, int y){
		return grid[x][y];
	}
	
	public void createWorld() {
		//temp towers for testing
		menu = new Menu();
		for(int i = 0; i < 50; i++) {
			for(int j = 0; j < 50; j++) {
				blocks.add(new Block(new Vector2(i,j))); //blocks everything for now
			}
		}
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				grid[i][j] = -2;
				gridConverted[i][j]=-2;
			}
		}
		grid[0][10] = 0;
		gridConverted[17][10] = 0;
		
		character = new Character(new Vector2(25,25));
		wall = new Character(new Vector2(25, 15));
		
		initTestAnts();
		initWalls();
		fillGrid();  //grid to player1
		fillGrid2(); //grid to the AI player
	}
	
	public void initTestAnts(){
		int maxX = 590;
		int minX = 590;
		int maxY = 450;
		int minY = 300;//150
		for (int i = 0; i < 10; i++) {
			int randX = minX + (int)(Math.random() * ((maxX - minX) + 1));
			int randY = minY + (int)(Math.random() * ((maxY - minY) + 1));
			SpawnTower st = new SpawnTower(new Vector2(randX, randY),this,0,1,1,((Gdx.input.getX())/26)-5,((Gdx.input.getY())/20)-1);
			placeSpawnTower(st);
		}
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
	
	public void initWalls(){
		for (int i = 0; i < x;i++) {
			for (int j = 0; j < y; j++) {
				if((i==0 && !(j>7 && j <13))|| (j==0 &&i<17) || (i==17&& !(j>7 && j <13)) ||(j==20&&i<17)){
					grid[i][j] =-1;
					gridConverted[i][j] = -1;
					Vector2 mapPlacePosition = new Vector2(127+(i*26),454-(j*20));
					Tower wall = new Wall(mapPlacePosition, this, 0,i,j);
					towerList.add(wall);
				}
			}
		}
	}
	
	public void fillGrid(){
		Queue<Coordinate> q = new LinkedList<Coordinate>();
		q.offer(new Coordinate(0, 10,0));
		Coordinate eval = new Coordinate(0,0,0);
		boolean zeroChecked=false;
		while(!q.isEmpty()){
			eval = q.poll();
			if(!((eval.x<0 || eval.x>19) || (eval.y<0 || eval.y>19)) &&(grid[eval.x][eval.y]==-2 ||(!zeroChecked&&eval.value==0))){
				zeroChecked = true;
				grid[eval.x][eval.y]=eval.value;
				ArrayList<Coordinate> neighbor = getNeighbor(eval);
				for (Coordinate valid : neighbor) {
					q.add(valid);
				}
			}
		}
	}
	
	public void fillGrid2(){
		Queue<Coordinate> q = new LinkedList<Coordinate>();
		q.offer(new Coordinate(17, 10,0));
		Coordinate eval = new Coordinate(0,0,0);
		boolean zeroChecked2=false;
		while(!q.isEmpty()){
			eval = q.poll();
			if(!((eval.x<0 || eval.x>19) || (eval.y<0 || eval.y>19)) &&(gridConverted[eval.x][eval.y]==-2 ||(!zeroChecked2 && eval.value==0))){
				zeroChecked2 = true;
				gridConverted[eval.x][eval.y]=eval.value;
				ArrayList<Coordinate> neighbor = getNeighbor(eval);
				for (Coordinate valid : neighbor) {
					q.add(valid);
				}
			}
		}
	}
	
	public void resetGrid(){
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if(grid[i][j] >0 || grid[i][j]==-3){
					grid[i][j]=-2;
					gridConverted[i][j]=-2;
				}
			}
		}
	}
	
	public ArrayList<Coordinate> getNeighbor(Coordinate eval){
		ArrayList<Coordinate>  list= new ArrayList<Coordinate>();
			list.add(new Coordinate(eval.x, eval.y+1,eval.value+1));
			list.add(new Coordinate(eval.x, eval.y-1,eval.value+1));
			list.add(new Coordinate(eval.x+1, eval.y,eval.value+1));
			list.add(new Coordinate(eval.x-1, eval.y,eval.value+1));
		return list;
	}
	
	public void towerSell(Tower tower){
		if(tower.owner == 1){
			player1.addCurrency(tower.cost -3);
//			System.out.println("towerSold");
		}
	}
	
	public void removeTower(int x,int y){
		Tower removeTower = selectTower(x, y);
		if(removeTower!=null){
			towerSell(removeTower);
	//		System.out.println("towerPos: "+tower.mapCoordX+","+tower.mapCoordY);
	//		System.out.println("guess: "+x+","+y);
			removeTower.remove = true;
			resetGrid();
			grid[x][y] = -2;
			gridConverted[x][y]=-2;
			fillGrid();
			fillGrid2();
		}
	}
	
	public void levelUpTower(int x, int y){
		Tower levelTower = selectTower(x, y);
		if(levelTower!=null){
			levelTower.levelUp();
		}
	}
	
	public Tower selectTower(int x, int y){
		if(grid[x][y]==-1){
			Tower returnTower = null;
			for (Tower tower : towerList) {
				if(tower instanceof Wall){}
				else{
					if(tower.mapCoordX == x && tower.mapCoordY == y){
						returnTower = tower;
					}
				}
			}
			return returnTower;
		}
		return null;
	}
	
	public void placeTower(int x,int y,Tower tower){
//		System.out.println(x+","+y);
		if(x<=0 || y<0 || x>=17 || y>20){
			fillGrid();
			fillGrid2();
//			System.out.println("DO NOTHING");
		}
		else if(getPlayer(tower.owner).getCurrency()-tower.cost < 0 ){
			System.out.println("not enough coin");
			fillGrid();
			fillGrid2();
		}
		else{
			if(grid[x][y] == -1){
//				System.out.println("DO NOTHING");
			}
			else if(!checkBlocking(x,y)){
				getPlayer(tower.owner).addCurrency((-tower.cost));
				towerList.add(tower);
				grid[x][y]= -1;
				gridConverted[x][y]=-1;
				fillGrid();
				fillGrid2();
			}
		}
	}
	
	public void placeSpawnTower(Tower tower){
		towerList.add(tower);
	}
	
	public boolean checkBlocking(int x,int y){ //only need to check one grid for this.
		resetGrid();
		grid[x][y] = -3;
		fillGrid();
		if(grid[18][10]==-2){
			grid[x][y] = -2;
			resetGrid();
			fillGrid();
			fillGrid2();
			return true;
		}
		grid[x][y] = -2;
		resetGrid();
		return false;
	}
	
	public String toString(){
		String t ="";
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if(gridConverted[j][i]<10 && gridConverted[j][i]>=0){
					t +=" "+ gridConverted[j][i]+" ";
				}
				else
					t += gridConverted[j][i]+" ";
			}
			t+="\n";
		}
		return t;
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

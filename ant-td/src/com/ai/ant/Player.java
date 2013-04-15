package com.ai.ant;

import java.util.ArrayList;

public class Player {
	protected ArrayList<Tower> towersAvailable; //(gives the ability to lock towers)
	protected int health;   //(health decrements when enemy mobs reach player base.)
	protected int currency; //(towers cost coin of some type)
	protected int points; 	//(accumulated over time, rewarded upon killing a mob or a player)
	protected int level;
	
	public Player(){
		towersAvailable  = new ArrayList<Tower>();
		health = 20;
		currency = 100;
		points = 0;		
		level = 1;
	}
	
	public boolean place(Tower tower){
		boolean safe = true; //call some method to see if placing an object there is valid. 
		if(safe){
			//System.out.println("placed");
			currency -= tower.getCost();
			return true;
		}
		else{
			//System.out.println("Blocking");
			return false;
		}
	}
	
	public void addPoints(int points){
		this.points += points;
	}
	public void addCurrency(int cur){
		currency += cur;
	}
	
	/*
	 **************************
	 * GETTERS/SETTERS
	 ************************** 
	 */
	public ArrayList<Tower> getTowersAvailable() {
		return towersAvailable;
	}
	public void setTowersAvailable(ArrayList<Tower> towersAvailable) {
		this.towersAvailable = towersAvailable;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getCurrency() {
		return currency;
	}
	public void setCurrency(int currency) {
		this.currency = currency;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
}

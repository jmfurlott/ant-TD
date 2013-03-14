package com.ai.ant;

import java.util.ArrayList;

public class Player {
	protected ArrayList<Tower> towersAvailable; //(gives the ability to lock towers)
	protected int health;   //(health decrements when enemy mobs reach player base.)
	protected int currency; //(towers cost coin of some type)
	protected int points; 	//(accumulated over time, rewarded upon killing a mob or a player)

	
	public Player(){
		towersAvailable  = new ArrayList<Tower>();
		health = 20;
		currency = 100;
		points = 0;		
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
	
	public void addPoints(Mob mob){
		points += mob.getPoint();
	}
	public void addCurrency(Mob mob){
		currency += mob.getCurrency();
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
}

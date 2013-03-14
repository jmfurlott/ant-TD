package com.ai.ant;

import java.util.ArrayList;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Tower {
	
	protected Vector2 position;
	protected Rectangle rect;
	protected ArrayList<Effect> effect;
	protected int range;
	protected String name;
	protected int level;
	protected double fireRate;
	protected int damage;
	protected int cost;
	private Mob target;
	//TODO: AI targeting
	
	public Tower(Vector2 position){
		this.position = position;
		this.rect = new Rectangle(); //default rectangle?
		effect = null;
		target = null;
	}
	
	//making this abstract to gain control of damage scaling across each type of tower
	abstract void levelUp();
	
	public void TowerAI(){
		boolean active = true;
		while(active){
			if(target!=null){
				fire();
			}
			else{
				getTarget();
			}
		}
	}
	
	public Mob getTarget(){
		/* TODO: implement AI needed for targeting the farthest mob in the range of the tower.
		 * scans mobs in ranage and assigns the mob that is nearest to the base.
		 * "nearest to base" - mob that is the most progressed along the path it was assigned.
		 */
		return null;
	}
	
	public void fire(){
		if(target!=null){
			shoot();
		}
		else{}
	}
	
	private void shoot(){
		//fires or pulses its effects in an area around this tower;
		System.out.println("FIRE!!!");
	}
	
	/*
	 **************************
	 * GETTERS/SETTERS
	 ************************** 
	 */
	public Vector2 getPosition() {
		return position;
	}
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	public Rectangle getRect() {
		return rect;
	}
	public void setRect(Rectangle rect) {
		this.rect = rect;
	}
	public ArrayList<Effect> getEffect() {
		return effect;
	}
	public void setEffect(ArrayList<Effect> effect) {
		this.effect = effect;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getFireRate() {
		return level;
	}
	public void setFireRate(int fireRate) {
		this.fireRate = fireRate;
	}
	public int getCost() {
		return level;
	}
	public void setCost(int cst) {
		this.cost= cost;
	}
}

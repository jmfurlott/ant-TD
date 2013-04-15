package com.ai.ant;

import com.badlogic.gdx.math.Vector2;
import java.util.Random;

public class SpawnTower extends Tower{
	private final int maxLevel;
	private int mobLevel;
	private int mobTarget;
	private int timer = 0;
	private int timerLength = 1000/(level+1);
	private int oldScore = 1;
	public SpawnTower(Vector2 position, World world, int owner, int mobLevel,int mobTarget, int x, int y) {
		super(position, world, owner,x,y);
		maxLevel = 3;
		this.mobLevel = mobLevel;
		this.mobTarget = mobTarget;
		delay =  1000;
		type = 6;
		active = false;
	}

	void levelUp() {
		if(level == maxLevel){}
		else if(world.getPlayer(owner).getCurrency()-cost <0){
			
		}
		else{
			world.getPlayer(owner).addCurrency(-(cost));
			level++;
			damage += 1;
			range +=5;
			fireRate += .25;
		}
	}
	@Override
	public void towerAI(){
			int currentScore = world.getPlayer(1).points;
			if(( currentScore % 25 == 0)&&(currentScore!= oldScore)){
				oldScore = currentScore;
				level++;
				timerLength = 1000/level;
				timer = 0;
			}
			fire();
			
	}
	@Override
	public void fire(){
		
		if(timer == 0){
			checkIsReady();
			if(active){
				shoot();
				isFired();
			}
			timer++;
			
		}
		else if(timer == timerLength){
			timer = 0;
		}
		else{
			timer++;
		}
		
	}
	
	void shoot(){
		if(this.owner != 1){
			fireProportion();
		}
		else if(this.owner == 1){//this is player
			BasicMob bMob = new BasicMob(new Vector2(this.position.x+13,this.position.y-10),mobTarget,level,world);
			world.mobList.add(bMob);
		}
		
	}

	private void fireProportion() {
		Random rand = new Random();
		int i = (int) (rand.nextGaussian()*100 + (world.getPlayer(1).points%1000));//gaussian distribution with mean at current score and standard deviation of 100
		if(i<200){
			BasicMob tMob = new BasicMob(new Vector2(this.position.x+13,this.position.y-10),mobTarget,level,world);
			world.mobList.add(tMob);
		}
		else if((i>200)&&(i<400)){
			RedAnt tMob = new RedAnt(new Vector2(this.position.x+13,this.position.y-10),mobTarget,level,world);
			world.mobList.add(tMob);
		}
		else if((i>400)&&(i<600)){
			FlyingAnt tMob = new FlyingAnt(new Vector2(this.position.x+13,this.position.y-10),mobTarget,level,world);
			world.mobList.add(tMob);
		}
		else if((i>600)&&(i<800)){
			CarpenterAnt tMob = new CarpenterAnt(new Vector2(this.position.x+13,this.position.y-10),mobTarget,level,world);
			world.mobList.add(tMob);
		}
		else if(i>800){
			QueenAnt tMob = new QueenAnt(new Vector2(this.position.x+13,this.position.y-10),mobTarget,level,world);
			world.mobList.add(tMob);
		}
		
	}
}

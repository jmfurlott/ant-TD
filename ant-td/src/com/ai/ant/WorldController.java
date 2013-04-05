package com.ai.ant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ai.ant.Character.State;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class WorldController {
	//all input control is here
	
	enum Keys {
		LEFT, RIGHT, UP, DOWN, A, SPACE
	}
	
	
	private World world;
	private WorldRenderer renderer;
	private Character character;
	private Character wall; //collision
	ArrayList<Bullet> tempBullet = new ArrayList<Bullet>();
	
	
	static Map<Keys, Boolean> keys = new HashMap<WorldController.Keys, Boolean>();
	static {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.UP, false);
		keys.put(Keys.DOWN, false);
		keys.put(Keys.A, false);
	};
	
	public WorldController(World world, WorldRenderer renderer) {
		this.world = world;
		this.character = world.getCharacter();
		this.wall = world.getWall();
		this.renderer = renderer.getRenderer();
	}
	
	//pressed controls
	public void leftPressed() {
		keys.get(keys.put(Keys.LEFT, true));
	}
	public void rightPressed() {
		keys.get(keys.put(Keys.RIGHT, true));
	}
	public void upPressed() {
		keys.get(keys.put(Keys.UP, true));
	}
	public void downPressed() {
		keys.get(keys.put(Keys.DOWN, true));
	}
	public void aPressed() {
		keys.get(keys.put(Keys.A, true));
	}
	public void spacePressed()
	{
		keys.get(keys.put(Keys.SPACE, true));
	}
	
	
	//released controls
	public void leftReleased() {
		keys.get(keys.put(Keys.LEFT, false));
	}
	public void rightReleased() {
		keys.get(keys.put(Keys.RIGHT, false));
	}
	public void upReleased() {
		keys.get(keys.put(Keys.UP, false));
	}
	public void downReleased() {
		keys.get(keys.put(Keys.DOWN, false));
	}
	public void aReleased() {
		keys.get(keys.put(Keys.A, false));
	}
	public void spaceReleased()
	{
		keys.get(keys.put(Keys.SPACE, false));
	}
	
	
	
	
	public void update(float delta) {
		processInput();
		character.update(delta);
		
		ArrayList<Bullet> temp = new ArrayList<Bullet>();
		temp = world.bulletList;
		for(Bullet bullet: temp) {
			bullet.update(delta);
		}
		
		for(Tower tower: world.getTowers()){
			tower.update();
		}
		for(Mob mob: world.getMobs()){
			mob.update(delta);
		}
	}
	
	public void processInput() {
		
		
		//Screen debugging
		//if (keys.get(Keys.SPACE))
		//{
		//	Gdx.app.log("intro", "space has been entered");
		//}
		
		//horizontal first
		if(keys.get(Keys.LEFT)) {
			
			//testing collision
			if(!checkForCollision(character.getBounds(), wall.getBounds(), false, true)) {
				character.setState(State.WALKING);
				character.getVelocity().x = -Character.SPEED;
			}
		}
		if(keys.get(Keys.RIGHT)) {
			character.setState(State.WALKING);
			character.getVelocity().x = Character.SPEED;
		}
		
		if ((keys.get(Keys.LEFT) && keys.get(Keys.RIGHT)) || (!keys.get(Keys.LEFT) && !(keys.get(Keys.RIGHT)))) {
			character.setState(State.IDLE);
			character.getAcceleration().x = 0;
			character.getVelocity().x = 0;
		
		}
		
		
		//vertical next
		if(keys.get(Keys.UP)) {
			character.setState(State.WALKING);
			character.getVelocity().y = Character.SPEED;
		}
		if(keys.get(Keys.DOWN)) {
			character.setState(State.WALKING);
			character.getVelocity().y = -Character.SPEED;
		}
		if((keys.get(Keys.UP) && keys.get(Keys.DOWN)) || (!keys.get(Keys.UP) && !(keys.get(Keys.DOWN)))) {
			character.setState(State.IDLE);
			character.getAcceleration().y = 0;
			character.getVelocity().y = 0;
		}
		
		
		//path finding based on pressing A - just some testing
		//going to have the current figure go to some specified point
		boolean madeFlag = false;
		if(keys.get(Keys.A)) {
			Gdx.app.log("input", "Key A has been pressed");
			
			//define the position I want to reach
			Vector2 goal = new Vector2(48,48);
			
			//step size...how fast it can move
			float STEPSIZE = 0.0001f;
					
			character.setState(State.WALKING);

			
			while(!madeFlag) {
				//begin steps to getting to the location
				//need to calculate how far away we are away from 
				float currentX = character.getPosition().x;
				float currentY = character.getPosition().y;
				
				Gdx.app.log("input", "Currentx: " + currentX + " Currenty: " + currentY);
				
				float diffX = Math.abs(goal.x - currentX);
				float diffY = Math.abs(currentY - goal.y);
				
				if(diffX > 0.5f && diffY > 0.5f) {
				
					
					
					if(currentX < goal.x) {
						character.getPosition().x = character.getPosition().x + STEPSIZE;
					} else {
						character.getPosition().x = character.getPosition().x - STEPSIZE;
					}
					
					if(currentY < goal.y) {
						character.getPosition().y = character.getPosition().y - STEPSIZE;
					} else {
						character.getPosition().y = character.getPosition().y + STEPSIZE;
					}
					
					
					
				} else {
					madeFlag = true;
				}
				
				
				
			}
		
		}
		
		
		
		//mouse/touch
		if(Gdx.input.isTouched()) {
			Gdx.app.log("input", "Touched at X: " + Gdx.input.getX() + " Y: " + Gdx.input.getY());
			Gdx.app.log("input", "ppux: " + renderer.getScreenWidth() + " ppuy: " + renderer.getScreenHeight());
			//Vector3 touchPosition = new Vector3();
			//touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			character.setState(State.WALKING);
			float ppuX = renderer.getScreenWidth();
			float ppuY = renderer.getScreenHeight();
			float cameraWidth = renderer.getCameraWidth();
			float cameraHeight = renderer.getCameraHeight();
			
			/* Notes:
			 * in order to find what position you are on the map when you click down look at the character.getPosition()..lines 
			 * If the 50x50 map gets changed around this will break so be careful.  I have it being rounded to the closest square so that
			 * we can remain within uniform units -> this will help with all that path finding
			 */
			
			
			
			Gdx.app.log("input", "charNewPosX: " + Math.round( 50*Gdx.input.getX()/(ppuX*cameraWidth)) + " charNewPosY: " + Math.round(50*Gdx.input.getY()/(ppuY*cameraHeight)));
			character.getPosition().y =50 - Math.round( 50*Gdx.input.getY()/(ppuY*cameraHeight) - 0.5f);
			character.getPosition().x = (int) Math.round(50*Gdx.input.getX()/(ppuX*cameraWidth) - 0.5f);
		}
	}
	

	
	public boolean checkForCollision(Rectangle r1, Rectangle r2, boolean up, boolean left) {
		
		//let r1 always be your current figure 
		//let r2 be the object nearby
		//up and left are the cardinal positions...ONE and ONLY ONE can be true. 
		//if left = true, check left. if false check right rest would be null etc
		
		
		Intersector intersector = new Intersector();
		Rectangle temp = r1;
		
		if(up == true) {
			temp.y = temp.y + 1.0f;
		} else if (up == false) {
			temp.y = temp.y - 1.0f;
		} else if (left == true) {
			temp.x = temp.x - 1.0f;
		} else if (left == false ) {
			temp.x = temp.x + 1.0f;
		} else {
			return false; //error and do nothing
		}
		
		
		boolean allowed =  intersector.intersectRectangles(temp, r2);
		
		Gdx.app.log("Collision", "movement allowed: " + allowed);
		return allowed;
		
		
	}
	
	
}

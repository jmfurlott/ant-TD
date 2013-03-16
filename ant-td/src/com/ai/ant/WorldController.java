package com.ai.ant;

import java.util.HashMap;
import java.util.Map;

import com.ai.ant.Character.State;
import com.ai.ant.WorldController.Keys;

public class WorldController {
	//all input control is here
	
	enum Keys {
		LEFT, RIGHT, UP, DOWN
	}
	
	
	private World world;
	private Character character;
	
	static Map<Keys, Boolean> keys = new HashMap<WorldController.Keys, Boolean>();
	static {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.UP, false);
		keys.put(Keys.DOWN, false);
	};
	
	public WorldController(World world) {
		this.world = world;
		this.character = world.getCharacter();
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
	
	
	public void update(float delta) {
		processInput();
		character.update(delta);
	}
	
	public void processInput() {
		
		//horizontal first
		if(keys.get(Keys.LEFT)) {
			character.setState(State.WALKING);
			character.getVelocity().x = -Character.SPEED;
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
	}
	
	
}

package com.ai.ant;

import java.util.HashMap;
import java.util.Map;

import com.ai.ant.Character.State;
import com.badlogic.gdx.Gdx;

public class WorldController {
	//all input control is here
	
	enum Keys {
		LEFT, RIGHT, UP, DOWN
	}
	
	
	private World world;
	private WorldRenderer renderer;
	private Character character;
	
	static Map<Keys, Boolean> keys = new HashMap<WorldController.Keys, Boolean>();
	static {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.UP, false);
		keys.put(Keys.DOWN, false);
	};
	
	public WorldController(World world, WorldRenderer renderer) {
		this.world = world;
		this.character = world.getCharacter();
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
			
			Gdx.app.log("input", "charNewPosX: " + (int) 50*Gdx.input.getX()/(ppuX*cameraWidth) + " charNewPosY: " + (int) 50*Gdx.input.getY()/(ppuY*cameraHeight));
			character.getPosition().y =50 - (int) 50*Gdx.input.getY()/(ppuY*cameraHeight);
			character.getPosition().x = (int) 50*Gdx.input.getX()/(ppuX*cameraWidth);
		}
	}
	

	
	
}

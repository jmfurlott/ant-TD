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
	

	
	
}

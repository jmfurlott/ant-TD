package com.ai.ant;

import java.util.HashMap;
import java.util.Map;

import com.ai.ant.WorldController.Keys;
import com.badlogic.gdx.Gdx;

public class IntroScreenController {

	
	enum Keys{
		SPACE
	}
	
	private IntroScreen intro;
	private IntroScreenRenderer renderer;
	
	
	static Map<Keys, Boolean> keys = new HashMap<IntroScreenController.Keys, Boolean>();
	
	static{
		keys.put(Keys.SPACE, false);
	};
	
	public IntroScreenController(IntroScreen intro, IntroScreenRenderer renderer)
	{
		this.intro = intro;
		this.renderer = renderer.getRenderer();
	}
	
	public void spacePressed()
	{
		Gdx.app.log("intro", "anythinghere");
		
		keys.get(keys.put(Keys.SPACE, true));
	}
	
	public void spaceReleased()
	{
		Gdx.app.log("intro", "released space");
		
		keys.get(keys.put(Keys.SPACE, false));
	}
	
	public void update(float delta) {
		processInput();
	}
	
	public void processInput()
	{
		
		if (keys.get(Keys.SPACE))
		{
			Gdx.app.log("intro", "space has been entered");
			
			//We want to call the start of the game here?
			
			
		}		
		
	}
}

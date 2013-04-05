package com.ai.ant;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class IntroScreen {
	
	private Character logo; //character but really just a default template
	
	public IntroScreen()
	{
		createWorld();
	}
	
	public void createWorld()
	{
		Gdx.app.log("intro", "inside the createWorld method");
		logo = new Character(new Vector2(25,25));
	}
	
	
	public Character getLogo() {
		return logo;
	}
}

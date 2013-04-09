package com.ai.ant;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;

public class EndGameScreen implements Screen {
	
	private Character logo; //character but really just a default template
	private EndGameScreen endScreen;
	private EndGameScreenRenderer renderer;
	private int height, width;
	
	public EndGameScreen()
	{
		
	}
	
	public void createWorld()
	{
		Gdx.app.log("intro", "inside the createWorld method");
		logo = new Character(new Vector2(25,25));
	}
	
	
	public Character getLogo() {
		return logo;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		//for when we have sprites
		
		renderer.render();		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		this.width = width;
		this.height = height;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

		
		//endScreen = new EndGameScreen();
		renderer = new EndGameScreenRenderer();
		
		//Gdx.input.setInputProcessor(this);
		
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}

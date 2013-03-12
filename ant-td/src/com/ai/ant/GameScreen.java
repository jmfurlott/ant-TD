package com.ai.ant;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;


public class GameScreen implements Screen {

	private World world;
	private WorldRenderer renderer;
	private WorldController controller;
	
	private int width, height;
	
	@Override
	public void resize(int width, int height) {
		renderer.setSize(width, height);
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.0f, .392f, 0.0f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		//for when we have sprites
		//controller.update(delta);
		
		renderer.render();
	}
	
	@Override
	public void pause() {
		
	}
	
	@Override
	public void resume() {
		
	}
	
	@Override
	public void dispose() {
		//Gdx.input.setInputProcessor(null);
	}

	@Override
	public void show() {
		world = new World();
		renderer = new WorldRenderer(world);
		controller = new WorldController(world);
		//Gdx.input.setInputProcessor(this);
	
	}

	@Override
	public void hide() {
		//Gdx.input.setInputProcessor(null);
	}
	
	
}

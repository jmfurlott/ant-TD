package com.ai.ant;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;

public class MenuScreen implements Screen, InputProcessor  {

	private IntroScreen intro;
	private IntroScreenRenderer renderer;
	private IntroScreenController controller;
	
	private Game g;
	
	private int gameplay;
	
	private int width, height;
	
	public MenuScreen(Game g) {
		this.g = g;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.SPACE)
		{
			controller.spacePressed();
			gameplay = 1;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.SPACE)
		{
			controller.spaceReleased();
			gameplay = 1;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void render(float delta) {
		//Gdx.app.log("intro", "trying to render");
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		//for when we have sprites
		controller.update(delta);
		
		renderer.render();
		if(Gdx.input.justTouched())
            g.setScreen(new GameScreen(g));

		
	}

	@Override
	public void resize(int width, int height) {
		//renderer.setSize(width, height);
		this.width = width;
		this.height = height;
	}

	@Override
	public void show() {
		//This method has to be different
		//Will display the menu
		gameplay = 0;
		intro = new IntroScreen();
		renderer = new IntroScreenRenderer(intro);
		controller = new IntroScreenController(intro, renderer);
		
		Gdx.input.setInputProcessor(this);
		
		
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
		
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
		Gdx.input.setInputProcessor(null);
		
	}
	
	public int getSwitch() {
		if (keyDown(32))
		{
			gameplay = 1;
		}
		
		return gameplay;
	}

}

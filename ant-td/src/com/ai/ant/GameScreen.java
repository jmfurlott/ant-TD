package com.ai.ant;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;


public class GameScreen implements Screen, InputProcessor {

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
		controller.update(delta);
		
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
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void show() {
		world = new World();
		renderer = new WorldRenderer(world);
		controller = new WorldController(world, renderer);
		Gdx.input.setInputProcessor(this);
	
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.LEFT) {
			controller.leftPressed();
		}
		if(keycode == Keys.RIGHT) {
			controller.rightPressed();
		}
		if(keycode == Keys.UP) {
			controller.upPressed();
		}
		if(keycode == Keys.DOWN) {
			controller.downPressed();
		}
		
		
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		if(keycode == Keys.RIGHT) {
			controller.rightReleased();
		}
		if(keycode == Keys.LEFT) {
			controller.leftReleased();
		}
		if(keycode == Keys.UP) {
			controller.upReleased();
		}
		if(keycode == Keys.DOWN) {
			controller.downReleased();
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
		//Gdx.app.log("input", "X: " + screenX + "  Y: " + screenY);
		//controller.moveOnClick(screenX, screenY);
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
	
	// input stuff..mouse click here maybe?
	
	
	
	
}

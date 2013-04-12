package com.ai.ant;

import com.badlogic.gdx.Game;
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
	private Game g;
	
	public GameScreen(Game g) {
		this.g = g;
	}
	
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
		controller = new WorldController(world, renderer, g);
		Gdx.input.setInputProcessor(this);
	
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public boolean keyDown(int keycode) {

		if(keycode == Keys.NUM_1) {
			controller.onePressed();
		}
		if(keycode == Keys.NUM_2) {
			controller.twoPressed();
		}
		if(keycode == Keys.NUM_3) {
			controller.threePressed();
		}
		if(keycode == Keys.NUM_4) {
			controller.fourPressed();
		}
		if(keycode == Keys.NUM_5) {
			controller.fivePressed();
		}
		if(keycode == Keys.NUM_6) {
			controller.sixPressed();
		}
		if(keycode == Keys.NUM_7) {
			controller.sevenPressed();
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {

		if(keycode == Keys.NUM_1) {
			controller.oneReleased();
		}
		if(keycode == Keys.NUM_2) {
			controller.twoReleased();
		}
		if(keycode == Keys.NUM_3) {
			controller.threeReleased();
		}
		if(keycode == Keys.NUM_4) {
			controller.fourReleased();
		}
		if(keycode == Keys.NUM_5) {
			controller.fiveReleased();
		}
		if(keycode == Keys.NUM_6) {
			controller.sixReleased();
		}
		if(keycode == Keys.NUM_7) {
			controller.sevenReleased();
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

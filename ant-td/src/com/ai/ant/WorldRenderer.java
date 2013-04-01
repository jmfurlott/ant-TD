package com.ai.ant;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;



/*
 * Block x = 0:6 (on the left side are left for the menu derek)
 * Actual game play will be from x = 6:49
 * 
 */



public class WorldRenderer {

	private static final float CAMERA_WIDTH = 10f;
	private static final float CAMERA_HEIGHT = 10f; //unit control
	
	private World world;
	private OrthographicCamera cam; //not sure 
	
	private int width;
	private int height;
	
	private float ppuX;
	private float ppuY;
	
	ShapeRenderer debugRenderer = new ShapeRenderer();
	
	public WorldRenderer(World world) {
		this.world = world;
		this.cam = new OrthographicCamera(50,50);
		this.cam.position.set(25, 25, 0);
		this.cam.update();
	}
	
	public void render() {
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Rectangle);
		
		for(Block block : world.getBlocks()) {
			Rectangle rect = block.getBounds();
			float x1 = block.getPosition().x + rect.x;
			float y1 = block.getPosition().y + rect.y;
			if (x1 > 6) {
				debugRenderer.setColor(new Color(0,0,0,1));
			} else if (x1 <= 6) {
				debugRenderer.setColor(new Color(1,1,0,1));
			}
			debugRenderer.rect(x1, y1, rect.width, rect.height);
		}
		
		
		//blocks are now rendered so continue forward with the sprites
		//...
		//otherwise end
		
		Character character = world.getCharacter();
		Rectangle rect = character.getBounds();
		float x1 = character.getPosition().x + rect.x;
		float y1 = character.getPosition().y + rect.y;
		debugRenderer.setColor(new Color(1,0,0,1));
		debugRenderer.rect(x1,  y1, rect.width, rect.height);
		
		debugRenderer.end();
	}
	
	public void setSize(int w, int h) {
		this.width = w;
		this.height = h;
		ppuX = (float) width/ CAMERA_WIDTH;
		ppuY = (float) height/ CAMERA_HEIGHT;
	}
	
	public WorldRenderer getRenderer() {
		return this;
	}
	
	public float getScreenWidth() {
		return ppuX;
	}
	
	public float getScreenHeight() {
		return ppuY;
	}
	
	public float getCameraWidth() {
		return CAMERA_WIDTH;
		
	}
	public float getCameraHeight() {
		return CAMERA_HEIGHT;
	}
	
}

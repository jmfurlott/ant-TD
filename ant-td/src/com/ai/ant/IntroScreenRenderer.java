package com.ai.ant;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class IntroScreenRenderer {
	private static final float CAMERA_WIDTH = 10f;
	private static final float CAMERA_HEIGHT = 10f; //unit control

	private OrthographicCamera cam; //not sure 
	
	private int width;
	private int height;
	
	private float ppuX;
	private float ppuY;
	
	private IntroScreen intro;

	public IntroScreenRenderer(IntroScreen intro)
	{
		this.intro = intro;
		this.cam = new OrthographicCamera(50,50);
		this.cam.position.set(25, 25, 0);
		this.cam.update();
	}
	
	public void render()
	{
		//Draws the menu screen
		//TODO
	}
	
	public IntroScreenRenderer getRenderer()
	{
		return this;
	}
	
	
	
	
	public void setSize(int w, int h) {
		this.width = w;
		this.height = h;
		ppuX = (float) width/ CAMERA_WIDTH;
		ppuY = (float) height/ CAMERA_HEIGHT;
	}
}

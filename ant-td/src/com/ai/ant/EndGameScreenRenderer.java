package com.ai.ant;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class EndGameScreenRenderer {
	private static final float CAMERA_WIDTH = 10f;
	private static final float CAMERA_HEIGHT = 10f; //unit control

	private OrthographicCamera cam; //not sure 
	
	private int width;
	private int height;
	
	private float ppuX;
	private float ppuY;
	
	private World world;
	
	public int score;
	public int level;
	BitmapFont font;
	
	
	ShapeRenderer debugRenderer = new ShapeRenderer();
	SpriteBatch batch = new SpriteBatch();
	private Texture logoTexture;

	public EndGameScreenRenderer(int score, int level)
	{
		//this.endGame = endGame;
		this.cam = new OrthographicCamera(50,50);
		this.cam.position.set(25, 25, 0);
		this.cam.update();
		font = new BitmapFont();
		this.score = score;
		this.level = level;
		loadTextures();
		batch = new SpriteBatch();
	}
	
	public void render()
	{

		float p = (float) Gdx.graphics.getWidth();
		
		batch.begin();
			batch.draw(logoTexture, 0, 0, 640, 480);
			drawPlayerStats();
			Gdx.app.log("into", "rendering");
		batch.end();
	}	
	
	public EndGameScreenRenderer getRenderer()
	{
		return this;
	}
	
	public void loadTextures() {
		logoTexture = new Texture(Gdx.files.internal("GameOverScreen.png"));
		Gdx.app.log("into", "loaded texture");

	}
	
	public void drawPlayerStats() {
		font.setScale(2.0f);
		font.draw(batch, "" + score, 360, 235);
		font.draw(batch, "" + level, 365, 150);

	}
	
	
	public void setSize(int w, int h) {
		this.width = w;
		this.height = h;
		ppuX = (float) width/ CAMERA_WIDTH;
		ppuY = (float) height/ CAMERA_HEIGHT;
	}
}


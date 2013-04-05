package com.ai.ant;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	SpriteBatch batch = new SpriteBatch();
	private Texture soldierAnt;
	private Texture grass;
	private Texture basicTower;
	private Texture basicBullet;
	
	public WorldRenderer(World world) {
		this.world = world;
		this.cam = new OrthographicCamera(50,50);
		this.cam.position.set(25, 25, 0);
		this.cam.update();
		batch = new SpriteBatch();
		loadTextures();
	}
	
	public void render() {


		batch.begin();
		drawBlocks();
		drawTowers();
		drawBullets();
		loadCharacter();	
		batch.end();
		drawDebug();
		
		
	}
	
	public void setSize(int w, int h) {
		this.width = w;
		this.height = h;
		ppuX = (float) width/ CAMERA_WIDTH;
		ppuY = (float) height/ CAMERA_HEIGHT;
	}
	
	
	public void loadTextures() {
		soldierAnt = new Texture(Gdx.files.internal("QueenAnt.png"));
		grass = new Texture(Gdx.files.internal("grassTexture.png"));
		basicTower = new Texture(Gdx.files.internal("stunFreezeHill.png"));
		basicBullet = new Texture(Gdx.files.internal("basicBullet.png"));
		
	}
	
	public void loadCharacter() {
		Character character = world.getCharacter();
		Rectangle rect = character.getBounds();
		float x1 = character.getPosition().x + rect.x;
		float y1 = character.getPosition().y + rect.y;
		
		batch.draw(soldierAnt, x1 , y1 , character.SIZE * ppuX, character.SIZE *ppuY);
		//batch.draw(soldierAnt, character.getPosition().x * ppuX  , character.getPosition().y * ppuY, character.SIZE * ppuX, character.SIZE * ppuY);
	}
	
	public void drawBlocks() {
		for(Block block : world.getBlocks()) {	
			batch.draw(grass, block.getPosition().x * ppuX, block.getPosition().y * ppuY, Block.SIZE * ppuX, Block.SIZE * ppuY);
		}
	}
	
	public void drawTowers() {
		for(Tower tower : world.getTowers()) {
			//System.out.println("x: "+ (tower.getPosition().x*ppuX) +" y:"+ (tower.getPosition().y*ppuY));
			//batch.draw(basicTower, tower.getPosition().x * ppuX, tower.getPosition().y* ppuY, Block.SIZE * ppuX, Block.SIZE * ppuY);
			batch.draw(basicTower, tower.getPosition().x, tower.getPosition().y, Tower.SIZE * 25, Tower.SIZE * 25);			
		}
	}
	
	public void drawBullets() {
		ArrayList<Bullet> temp = new ArrayList<Bullet>();
		for(Bullet bullet: world.getBullets()){
			if(!bullet.active){
				temp.add(bullet);
			}
			else{
				batch.draw(basicBullet, bullet.getPosition().x+10, bullet.getPosition().y+10, Bullet.SIZE*5, Bullet.SIZE*5);
			}
		}
		
		for(Bullet bullet: temp){
				world.bulletList.remove(bullet);
		}
	}
	
	public void drawDebug() {
		
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
	
	
		Character character = world.getCharacter();
		Rectangle rect = character.getBounds();
		float x1 = character.getPosition().x + rect.x;
		float y1 = character.getPosition().y + rect.y;
		
		debugRenderer.setColor(new Color(1,0,0,1));
		debugRenderer.rect(x1,  y1, rect.width, rect.height);
		
		Character wall = world.getWall();
		Rectangle rect1 = character.getBounds();
		float xwall = wall.getPosition().x + rect1.x;
		float ywall = wall.getPosition().y + rect1.y;
		debugRenderer.setColor(new Color(1,0,0,1));
		debugRenderer.rect(xwall,  ywall, rect1.width, rect1.height);
		debugRenderer.end();

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

package com.ai.ant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
	private Texture queenAnt;
	private Texture soldierAnt;
	private Texture grass;
<<<<<<< HEAD
	
	//Towers and bullets
	private Texture spawnTower;
	private Texture basicTower;
	private Texture basicBullet;
	private Texture splashTower;
	private Texture splashBullet;
	
	
=======
	private Texture basicTower, slowTower, stunTower, splashTower, conversionTower, spawnTower, puddle;
	private Texture basicBullet;
>>>>>>> d991ea2c8c7a304cad111c732c0dd8c584aeb39a
	private Texture menuBackground;
	private Texture quitButton;
	
	Map<Integer, Texture> towerMapTexture = new HashMap<Integer, Texture>();
	
	public WorldRenderer(World world) {
		this.world = world;
		this.cam = new OrthographicCamera(50,50);
		this.cam.position.set(25, 25, 0);
		this.cam.update();
		batch = new SpriteBatch();
		loadTextures();
		buildTextureMap();
		
	}
	
	public void render() {

		batch.begin();
		drawBlocks();
		drawTowers();
		drawBullets();
		drawMobs();
		loadCharacter();	
		drawMenu();
		batch.end();
		//drawDebug();
<<<<<<< HEAD
		
=======

>>>>>>> d991ea2c8c7a304cad111c732c0dd8c584aeb39a
	}
	
	public void setSize(int w, int h) {
		this.width = w;
		this.height = h;
		ppuX = (float) width/ CAMERA_WIDTH;
		ppuY = (float) height/ CAMERA_HEIGHT;
	}
	
	
	public void loadTextures() {
		//ants
		queenAnt = new Texture(Gdx.files.internal("QueenAnt.png"));
<<<<<<< HEAD
		soldierAnt = new Texture(Gdx.files.internal("soldierAnt.png"));
		
		//Towers and bullets
		spawnTower = new Texture(Gdx.files.internal("hill_spawning.png"));
		basicTower = new Texture(Gdx.files.internal("stunFreezeHill.png"));
		basicBullet = new Texture(Gdx.files.internal("basicBullet.png"));	
		splashTower = new Texture(Gdx.files.internal("splashTower.png"));
		splashBullet = new Texture(Gdx.files.internal("splashBullet.png"));
=======
		spawnTower = new Texture(Gdx.files.internal("hill_spawning.png"));
		soldierAnt = new Texture(Gdx.files.internal("soldierAnt.png"));
		grass = new Texture(Gdx.files.internal("grassTexture.png"));
		
		//TODO put correct images in for each tower
		basicTower = new Texture(Gdx.files.internal("stunFreezeHill.png")); //wrong
		slowTower = new Texture(Gdx.files.internal("stunFreezeHill.png"));
		stunTower = new Texture(Gdx.files.internal("stunFreezeHill.png")); //wrong currently
		splashTower = new Texture(Gdx.files.internal("stunFreezeHill.png")); //wrong
		conversionTower = new Texture(Gdx.files.internal("conversionHill.png"));
		puddle = new Texture(Gdx.files.internal("puddle.png"));
		basicBullet = new Texture(Gdx.files.internal("basicBullet.png"));
>>>>>>> d991ea2c8c7a304cad111c732c0dd8c584aeb39a
		
		//menu and background
		grass = new Texture(Gdx.files.internal("grassTexture.png"));
		menuBackground= new Texture(Gdx.files.internal("woodMenuBackground.png"));
<<<<<<< HEAD
=======
		quitButton= new Texture(Gdx.files.internal("quit.png"));	
>>>>>>> d991ea2c8c7a304cad111c732c0dd8c584aeb39a
		
	}
	
	public void loadCharacter() {
		Character character = world.getCharacter();
		Rectangle rect = character.getBounds();
		float x1 = character.getPosition().x + rect.x;
		float y1 = character.getPosition().y + rect.y;
		
		batch.draw(queenAnt, x1 , y1 , character.SIZE * ppuX, character.SIZE *ppuY);
		//batch.draw(soldierAnt, character.getPosition().x * ppuX  , character.getPosition().y * ppuY, character.SIZE * ppuX, character.SIZE * ppuY);
	}
	
	public void drawBlocks() {
		for(Block block : world.getBlocks()) {	
			batch.draw(grass, block.getPosition().x * ppuX, block.getPosition().y * ppuY, Block.SIZE * ppuX, Block.SIZE * ppuY);
		}
	}
	
	public void drawTowers() {
		for(Tower tower : world.getTowers()) {
			if(tower instanceof BasicTower)
				batch.draw(basicTower, tower.getPosition().x, tower.getPosition().y, Tower.SIZE*25 , Tower.SIZE*25);
			else if(tower instanceof spawnTower)
				batch.draw(spawnTower, tower.getPosition().x, tower.getPosition().y, Tower.SIZE*25 , Tower.SIZE*25);
			else if(tower instanceof SplashTower)
				batch.draw(splashTower, tower.getPosition().x, tower.getPosition().y, Tower.SIZE*25 , Tower.SIZE*25);
		}
	}
	
	public void drawMobs() {
		ArrayList<Mob> temp = new ArrayList<Mob>();
		for(Mob mob :world.getMobs()){
			if(!mob.active  || mob.deathFlag){
				temp.add(mob);
			}
			else{
				if(mob instanceof BasicMob){
					batch.draw(soldierAnt, mob.getPosition().x, mob.getPosition().y, Mob.SIZE*20, Mob.SIZE*20);
				}
			}
		}
		for(Mob mob: temp){
			if(mob.deathFlag){
				mob.mobDeath();
			}
			world.mobList.remove(mob);
		}
	}
	
	
	
	public void drawBullets() {
		ArrayList<Bullet> temp = new ArrayList<Bullet>();
		for(Bullet bullet: world.getBullets()){
			if(!bullet.active){
				temp.add(bullet);
			}
			else{
				if(bullet.tower instanceof BasicTower)
					batch.draw(basicBullet, bullet.getPosition().x+10, bullet.getPosition().y+10, Bullet.SIZE*5, Bullet.SIZE*5);
				if(bullet.tower instanceof SplashTower)
/*TODO:FIX IMAGE*/	batch.draw(basicBullet, bullet.getPosition().x+10, bullet.getPosition().y+10, Bullet.SIZE*5, Bullet.SIZE*5);
			}
		}
		for(Bullet bullet: temp){
				world.bulletList.remove(bullet);
		}
	}
	
	
	public void drawMenu() {
		batch.draw(menuBackground, -38,0);
		
		//batch.draw(basicTower, 48, 25, 25, 25);
		
		Menu menu = world.getMenu();
		for(Button button : menu.getButtons()) {
			batch.draw(towerMapTexture.get(button.getTowerType()), button.getPosition().x*(640/50), button.getPosition().y*(480/50)-10, 30, 30);
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
	
	
	
	
	//configure hashmap to know which textures to map to
	
	/*
	   0. null
	   1. Normal Tower- simply attacks ants
	   2. Slow Tower - movement speed debuff
	   3. Stun Tower - prevents all movement, or other actions
	   4. Splash Tower-
	   5. Conversion Tower- converts to attack opponent
	   6. Spawn Tower - mob factory
	   7. Puddle
	 */
	
	public void buildTextureMap() {
		
		towerMapTexture.put(0, quitButton);
		towerMapTexture.put(1, basicTower);
		towerMapTexture.put(2, slowTower); //no class made for this yet
		towerMapTexture.put(3, stunTower); //no class for this yet
		towerMapTexture.put(4, splashTower);  
		towerMapTexture.put(5, conversionTower); //no class for this yet
		towerMapTexture.put(6, spawnTower);
		towerMapTexture.put(7, puddle); //no class for this yet 
		
		
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
	
	public Map<Integer, Texture> getTextureMap() {
		return towerMapTexture;
	}
}

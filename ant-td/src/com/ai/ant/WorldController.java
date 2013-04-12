package com.ai.ant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ai.ant.Character.State;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class WorldController {
	// all input control is here

	//grid.boxSize = 20x24
	
	
	
	enum Keys {
		LEFT, RIGHT, UP, DOWN, A, SPACE, ONE
	}
	
	static Map<Keys, Boolean> keys = new HashMap<WorldController.Keys, Boolean>();
	static {
		keys.put(Keys.LEFT, false);
	}

	private World world;
	private WorldRenderer renderer;
	private Character character;
	private Character wall; // collision
	private Menu menu;
	int selection;
	int side; // 0 means left side clickable, 1 means right side clickable
	Game g;
	int type;

	ArrayList<Bullet> tempBullet = new ArrayList<Bullet>();

	Button clicked;

	public WorldController(World world, WorldRenderer renderer, Game g) {
		this.world = world;
		this.character = world.getCharacter();
		this.wall = world.getWall();
		this.menu = world.getMenu();
		this.renderer = renderer.getRenderer();
		side = 0;
		clicked = null;
		this.g = g;
	}


	public void update(float delta) {
		processInput();
		character.update(delta);

		ArrayList<Bullet> temp = new ArrayList<Bullet>();
		temp = world.bulletList;
		
		for (Bullet bullet : temp) {
			bullet.update(delta);
		}

		for (Tower tower : world.getTowers()) {
			tower.update();
		}
		for (Mob mob : world.getMobs()) {
			mob.update(delta);
		}
	}
	public void processInput() {
		
		if (Gdx.input.isTouched()) {
			Vector2 debug = calculatePosition2();
			System.out.println("Pix:"+Gdx.input.getX()+","+Gdx.input.getY()+" Debug: "+debug.x+","+debug.y);
			Vector2 click = calculatePosition(Gdx.input.getX(),	Gdx.input.getY());
			
			if (click.x < 9 && side == 0) {
				clicked = menu.selectButton(click);

				
				if (clicked != null) {
					//Gdx.app.log("input", "Found a button");
					selection = 1;
					side = 1;
					if(clicked.getTowerType() == 0) {
						addTowerToMap(clicked);
						//seems like I am adding a new tower but 
						//just the quit button on first press
					}
				} else if (clicked == null) {
					//Gdx.app.log("input", "Didn't find anything");
				}
			} else if (click.x > 9 && selection == 1 && side == 1) {
				addTowerToMap(clicked);
				clicked = null;
				//Gdx.app.log("input", "Made new button");
				selection = 0;
				side = 0;
			}
		}
		
		
		
		
	}
	
	public Vector2 calculatePosition(int xPixel, int yPixel) {
		Vector2 position = new Vector2();

		float ppuX = renderer.getScreenWidth();
		float ppuY = renderer.getScreenHeight();
		float cameraWidth = renderer.getCameraWidth();
		float cameraHeight = renderer.getCameraHeight();

		int posX = (int) Math.round(50 * Gdx.input.getX()/ (ppuX * cameraWidth) - 0.5f);
		int posY = 50 - Math.round(50 * Gdx.input.getY()/ (ppuY * cameraHeight) - 0.5f);
		position.set(posX, posY);
		return position;

	}
	
	public Vector2 calculatePosition2() {
		Vector2 position = new Vector2(((Gdx.input.getX())/26)-5,((Gdx.input.getY())/20)-1);
		return position;
	}
	
	public Vector2 mapPlacePosition(){
		Vector2 temp = calculatePosition2();
//		System.out.println("mouse: "+Gdx.input.getX()+","+Gdx.input.getY());
		//Vector2 mapPlacePosition = new Vector2(127+(temp.x*26),444-(temp.y*20));
		
		Vector2 mapPlacePosition = new Vector2(127+(temp.x*26),454-(temp.y*20));
//		System.out.println("mapPlace: "+mapPlacePosition.x+","+mapPlacePosition.y);
		return mapPlacePosition;
	}
	
	public void addTowerToMap(Button clicked) {
		int type = clicked.getTowerType();	
		Vector2 temp = calculatePosition2();
		Vector2 temp1 = mapPlacePosition();
		
		System.out.println("mouse: "+Gdx.input.getX()+","+Gdx.input.getY());
//		System.out.println("temp1: "+ temp1.x+","+temp1.y);
//		System.out.println("temp: "+ temp.x+","+temp.y);
		if(type == 1) {
			world.resetGrid();
			Tower tower = new BasicTower(temp1, world,1,((Gdx.input.getX())/26)-5,((Gdx.input.getY())/20)-1);
			//Tower tower = new Wall(temp1,world,1);
			world.placeTower((int)temp.x,(int)temp.y,tower);
			System.out.println(world.toString());
		} 
		else if (type == 2) {
			//System.out.println("placing SlowTower");
			world.resetGrid();
			Tower tower = new SlowTower(temp1, world, 1,((Gdx.input.getX())/26)-5,((Gdx.input.getY())/20)-1);
			world.placeTower((int)temp.x,(int)temp.y,tower);
		}
//		else if (type == 3) {
//			System.out.println("placing StunTower");
//			Tower tower = new StunTower(new Vector2(Gdx.input.getX(), 480 - Gdx.input.getY()), world, 1);
//			world.placeTower(tower);
//		}
		else if (type == 4) {
			//System.out.println("placing SplashTower");
			world.resetGrid();
			Tower tower = new SplashTower(temp1,world, 1,((Gdx.input.getX())/26)-5,((Gdx.input.getY())/20)-1);
			world.placeTower((int)temp.x,(int)temp.y,tower);
		}
		else if (type == 5) {
		//System.out.println("placing ConversionTower");
			world.resetGrid();
			Tower tower = new ConversionTower(temp1, world, 1,((Gdx.input.getX())/26)-5,((Gdx.input.getY())/20)-1);
			world.placeTower((int)temp.x,(int)temp.y,tower);
		}
		else if (type == 6) {
			//System.out.println("placing SpawnTower");
			world.resetGrid();
			Tower tower = new SpawnTower(temp1, world, 0, 1, 1,((Gdx.input.getX())/26)-5,((Gdx.input.getY())/20)-1);
			world.placeTower((int)temp.x,(int)temp.y,tower);
		} 
		else if (type == 7) {
			System.out.println("Sell");
			world.removeTower(((Gdx.input.getX())/26)-5,((Gdx.input.getY())/20)-1);
		}		
		else if (type == 0){
			Gdx.app.log("quit", "Trying to quit");
			g.setScreen(new EndGameScreen());
		}
		
		System.out.println(world.toString()); 
	}
	
	
	
	//---------------------------------------------HOT KEYS
	
	public void leftPressed() {
		keys.get(keys.put(Keys.LEFT, true));
		selection = 1;
		side = 1;
		clicked = menu.selectButtonFromType(2);
	}
	public void leftReleased() {
		keys.get(keys.put(Keys.LEFT, false));

	}
	
	
	
}

package com.ai.ant;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class AntTD extends Game {
	public boolean start;
	@Override
	public void create() {
		start = false;
		
		MenuScreen menu = new MenuScreen();
		GameScreen game = new GameScreen();		
		setScreen(menu);
		
		try {
			Thread.sleep(500);// <----------------------------------------------Change this to vary menu screen wait time
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Gdx.app.log("intro", "end of create");
		setScreen(game);
	}
	
}

package com.ai.ant;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;

public class AntTD extends Game {

	
	@Override
	public void create() {
		
		MenuScreen menu = new MenuScreen(this);
		//GameScreen game = new GameScreen();	

		
		setScreen(menu);

		
		Gdx.app.log("intro", "end of create");
	}
	
}

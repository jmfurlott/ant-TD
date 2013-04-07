package com.ai.ant;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;

public class AntTD extends Game {

	
	@Override
	public void create() {
		
		MenuScreen menu = new MenuScreen(this);
		setScreen(menu);
		
		Gdx.app.log("intro", "End of create, should not get here :/");
	}
	
}

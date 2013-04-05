package com.ai.ant;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;

public class AntTD extends Game {

	
	@Override
	public void create() {
		
		MenuScreen menu = new MenuScreen();
		//GameScreen game = new GameScreen();	


		
		setScreen(menu);
//		DelayAction delay = new DelayAction();
//		delay.setDuration(5000);
//		delay.act(5000);
		

		try {
			Thread.sleep(3000);// <----------------------------------------------Change this to vary menu screen wait time
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Gdx.app.log("intro", "end of create");
		setScreen(new GameScreen());
	}
	
}

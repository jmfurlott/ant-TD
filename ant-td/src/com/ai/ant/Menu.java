package com.ai.ant;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class Menu {

	ArrayList<Button> buttons;
	
	public Menu() {
		//make new buttons

		buttons = new ArrayList<Button>();
		
		buttons.add(new Button(new Vector2(2,48), 2.0f, 2.0f, 0));
		buttons.add(new Button(new Vector2(4,4), 2.0f, 2.0f, 1));
		buttons.add(new Button(new Vector2(4,8), 2.0f, 2.0f, 2));
		buttons.add(new Button(new Vector2(4,12), 2.0f, 2.0f, 3));
		buttons.add(new Button(new Vector2(4,16), 2.0f, 2.0f, 4));
		buttons.add(new Button(new Vector2(4,20), 2.0f, 2.0f, 5));
		buttons.add(new Button(new Vector2(4,24), 2.0f, 2.0f, 6));
		buttons.add(new Button(new Vector2(4,28), 2.0f, 2.0f, 7));

		
	}
	
	public Button selectButton(Vector2 position) {
		//need to iterate through list of buttons and select that button
		Button selection = null;
		
		for(int i = 0; i < buttons.size(); i++) {
			//only works for 1x1 buttons right now
			if((Math.abs((position.x - buttons.get(i).getPosition().x)) <= 1) && (Math.abs(position.y- buttons.get(i).getPosition().y) <= 1)) {
				selection = buttons.get(i);
				break;
			}
		}
		
		return selection;
		
		
	}
	
	public Menu getMenu() {
		return this;
	}
	
	public ArrayList<Button> getButtons() {
		return buttons;
	}
	
}

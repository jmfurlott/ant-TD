package com.ai.ant;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class Menu {

	ArrayList<Button> buttons;
	
	
	public Menu() {
		//make new buttons

		buttons = new ArrayList<Button>();
		
		buttons.add(new Button(new Vector2(4,4), 1.0f, 1.0f));
		
		
	}
	
	public Button selectButton(Vector2 position) {
		//need to iterate through list of buttons and select that button
		Button selection = null;
		
		for(int i = 0; i < buttons.size(); i++) {
			//only works for 1x1 buttons right now
			if(position.x == buttons.get(i).getPosition().x && position.y == buttons.get(i).getPosition().y) {
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

package com.ai.ant;

import com.badlogic.gdx.math.Vector2;

public class CarpenterAnt extends Mob{

	public CarpenterAnt(Vector2 position, int target, int level, World world) {
		super(position, world);
		//Strong ant
		health = 50 + level* 100;
		this.target = target;		//represents which player this mob is attacking, needed for pathing and conversion tower
		this.level = level; 		//currency/points rewarded scale based on level
		currency = level * 2; 		//coin given based on level 1 of mob upon destruction
		points = level * 1; 		//points given based on level 1 of mob upon destruction
	}
}

package com.ai.ant;

public class Coordinate {
	public final Integer x;
	public final Integer y;
	public int value=0;
	public Boolean isChecked=false;
	
	public Coordinate(Integer x, Integer y,int value){
		this.value = value;
		this.x = x;
		this.y = y;
	}
}

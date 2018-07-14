package com.github.tomcrawleyy.Snake.AbstractClasses;

import java.awt.Graphics;

import com.github.tomcrawleyy.Snake.Snake;
import com.github.tomcrawleyy.Snake.Enums.ID;
import com.github.tomcrawleyy.Snake.GameState.Game.Location;
import com.github.tomcrawleyy.Snake.Handlers.Handler;

public abstract class GameObject {
	
	protected Location loc;
	protected ID id;
	protected int velX, velY;
	
	public GameObject(int x, int y, ID id){
		loc = new Location(x, y);
		this.id = id;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public void setLocation(Location loc){
		this.loc = loc;
	}
	
	public Location getLocation(){
		return loc;
	}
	
	public void setId(ID id){
		this.id = id;
	}
	
	public ID getID(){
		return id;
	}
	
	public void setVelX(int velX){
		this.velX = velX;
	}
	
	public void setVelY(int velY){
		this.velY = velY;
	}
	
	public int getVelX(){
		return velX;
	}
	
	public int getVelY(){
		return velY;
	}

}

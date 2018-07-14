package com.github.tomcrawleyy.Snake.GameState.Game;

import java.awt.Color;
import java.awt.Graphics;

import com.github.tomcrawleyy.Snake.AbstractClasses.GameObject;
import com.github.tomcrawleyy.Snake.Enums.ID;

public class Tail extends GameObject {
	
	private boolean justAdded = true;

	public Tail(int x, int y, ID id, Player player) {
		super(x, y, id);
		loc.setY(y);
		loc.setX(x);
	}

	public void tick() {
	}
	
	public boolean justAdded(){
		return justAdded;
	}
	
	public void justAdded(boolean justAdded){
		this.justAdded = justAdded;
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(loc.getX() + 1, loc.getY() + 1, 14, 14);
		return;
	}
	
	public void setLocation(Location loc){
		this.loc = loc;
	}

}

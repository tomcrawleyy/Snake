package com.github.tomcrawleyy.Snake.GameState.Menu;

import java.awt.Color;
import java.awt.Graphics;

public class AnimationSquare {
	
	private int x;
	private int y;
	private boolean isVisible = false;
	
	public AnimationSquare(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setVisible(boolean isVisible){
		this.isVisible = isVisible;
	}
	
	public boolean isVisible(){
		return isVisible;
	}
	
	public void render(Graphics g){
		g.setColor(Color.white);
		g.fillRect(x + 1, y + 1, 14, 14);
	}

}

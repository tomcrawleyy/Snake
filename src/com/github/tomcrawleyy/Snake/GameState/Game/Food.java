package com.github.tomcrawleyy.Snake.GameState.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.github.tomcrawleyy.Snake.Snake;
import com.github.tomcrawleyy.Snake.AbstractClasses.GameObject;
import com.github.tomcrawleyy.Snake.Enums.ID;

public class Food extends GameObject {
	
	private Random r = new Random();
	private static Food food;

	public Food(int x, int y, ID id) {
		super(x, y, id);
		loc.setX(x);
		loc.setY(y);
		food = this;
	}

	public void tick() {
	}
	
	public static Food getFood(){
		return food;
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(loc.getX() + 1, loc.getY() + 1, 14, 14);
	}
	
	public void randomiseLocation(){
		int x = r.nextInt((Snake.WIDTH - 64) / 16) * 16;
		int y = r.nextInt((Snake.HEIGHT - 64) / 16) * 16;
		Player player = Snake.getSnake().getHandler().player;
		if (player.getLocation().getX() == x + 32 && player.getLocation().getY() == y + 32){
			randomiseLocation();
			return;
		}
		for (Tail tail : player.getTail()){
			if (tail.getLocation().getX() == x + 32 && tail.getLocation().getY() == y + 32){
				randomiseLocation();
				return;
			}
		}
		loc.setX(x + 32);
		loc.setY(y + 32);
	}

}

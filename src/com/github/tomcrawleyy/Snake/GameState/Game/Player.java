package com.github.tomcrawleyy.Snake.GameState.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.github.tomcrawleyy.Snake.Snake;
import com.github.tomcrawleyy.Snake.AbstractClasses.GameObject;
import com.github.tomcrawleyy.Snake.Audio.AudioPlayer;
import com.github.tomcrawleyy.Snake.Enums.Direction;
import com.github.tomcrawleyy.Snake.Enums.EGameState;
import com.github.tomcrawleyy.Snake.Enums.ID;

public class Player extends GameObject {

	private int tickCount = 0;
	private Direction dir;
	private List<Direction> queuedDir = new ArrayList<Direction>();
	private Location lastLocation;
	private List<Tail> tail = new ArrayList<Tail>();
	private int currentScore = 0;
	private int lastScore = 0;
	private int sessionScore = 0;
	private int highScore = 0;

	public Player(int x, int y, ID id) {
		super(x, y, id);
		dir = Direction.North;
	}
	
	public int getLastScore(){
		return lastScore;
	}
	
	public int getSessionHighScore(){
		return sessionScore;
	}
	
	public int getHighScore() {
		return highScore;
	}

	public void tick() {
		tickCount++;
		if (tickCount == 4) {
			tickCount = 0;
			handleDirection();
			updateLocation();
		}
	}

	private void updateLocation() {
		lastLocation = new Location(loc.getX(), loc.getY());
		if (dir == Direction.North) {
			loc.setY(loc.getY() - 16);
		} else if (dir == Direction.South) {
			loc.setY(loc.getY() + 16);
		} else if (dir == Direction.West) {
			loc.setX(loc.getX() - 16);
		} else if (dir == Direction.East) {
			loc.setX(loc.getX() + 16);
		}
		handleLocation();
	}

	public void handleLocation() {
		if (loc.getX() == 16 | loc.getX() == Snake.WIDTH - 32 | loc.getY() == 16 | loc.getY() == Snake.HEIGHT - 32) {
			resetLocation();
			return;
		}
		Iterator<Tail> tailIter = tail.iterator();
		while (tailIter.hasNext()) {
			Tail tail = tailIter.next();
			if (tail.getLocation().getX() == loc.getX() && tail.getLocation().getY() == loc.getY()) {
				resetLocation();
				return;
			}
		}

		Food food = Food.getFood();
		if (loc.getX() == food.getLocation().getX() && loc.getY() == food.getLocation().getY()) {
			food.randomiseLocation();
			tail.add(0, new Tail(lastLocation.getX(), lastLocation.getY(), ID.Tail, this));
			AudioPlayer.getSound("eat").play();
			currentScore++;
			return;
		} else {
			if (tail.size() > 0) {
				for (int i = tail.size() - 1; i > -1; i--) {
					if (i == 0) {
						tail.get(i).setLocation(lastLocation);
					} else {
						tail.get(i).setLocation(tail.get(i - 1).getLocation());
					}
				}
			}	
		}
	}

	private Direction getOppositeDirection(Direction direction) {
		HashMap<Direction, Direction> oppDir = new HashMap<Direction, Direction>();
		oppDir.put(Direction.North, Direction.South);
		oppDir.put(Direction.South, Direction.North);
		oppDir.put(Direction.East, Direction.West);
		oppDir.put(Direction.West, Direction.East);
		return oppDir.get(direction);
	}

	public List<Tail> getTail() {
		return tail;
	}

	private void handleDirection() {
		if (queuedDir.size() != 0) {
			dir = queuedDir.get(0);
			queuedDir.remove(0);
		}
	}

	public void setDirection(Direction direction) {
		if (queuedDir.size() == 0) {
			if (direction != getOppositeDirection(dir)) {
				queuedDir.add(direction);
			}
		} else {
			if (direction != getOppositeDirection(queuedDir.get(queuedDir.size() - 1))) {
				queuedDir.add(direction);
			}
		}
	}

	public void resetLocation() {
		if (currentScore > sessionScore)
			sessionScore = currentScore;
		if (currentScore > highScore)
			highScore = currentScore;
		lastScore = currentScore;
		currentScore = 0;
		loc.setX(Snake.WIDTH / 2 - 16);
		loc.setY(Snake.HEIGHT / 2 - 16);
		Food.getFood().randomiseLocation();
		tail.clear();
		AudioPlayer.getSound("song").stop();
		AudioPlayer.getSound("menuSong").loop();
		Snake.getState().changeState(EGameState.Menu);
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(31, 31, Snake.WIDTH - 62, Snake.HEIGHT - 62);
		g.fillRect(loc.getX() + 1, loc.getY() + 1, 14, 14);
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		g.drawString("Current Score: " + currentScore, 32, 16);
		g.drawString("Session Best: " + sessionScore, Snake.WIDTH / 2 - 64, 16);
		g.drawString("All Time Best: " + highScore, Snake.WIDTH - 150, 16);
		return;
	}

}

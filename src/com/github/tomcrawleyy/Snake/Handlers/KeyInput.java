package com.github.tomcrawleyy.Snake.Handlers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.github.tomcrawleyy.Snake.Snake;
import com.github.tomcrawleyy.Snake.Enums.Direction;
import com.github.tomcrawleyy.Snake.Enums.EGameState;
import com.github.tomcrawleyy.Snake.GameState.GameState;
import com.github.tomcrawleyy.Snake.GameState.Game.Player;

public class KeyInput extends KeyAdapter {

	private Handler handler;

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		GameState state = Snake.getState();
		if (state.getCurrentState() == EGameState.Game) {
			Player player = handler.player;
			if (key == KeyEvent.VK_W) {
				player.setDirection(Direction.North);
			}
			if (key == KeyEvent.VK_A) {
				player.setDirection(Direction.West);
			}
			if (key == KeyEvent.VK_S) {
				player.setDirection(Direction.South);
			}
			if (key == KeyEvent.VK_D) {
				player.setDirection(Direction.East);
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
	}

}

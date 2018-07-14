package com.github.tomcrawleyy.Snake.Handlers;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.Random;

import com.github.tomcrawleyy.Snake.Snake;
import com.github.tomcrawleyy.Snake.Audio.AudioPlayer;
import com.github.tomcrawleyy.Snake.Enums.EGameState;
import com.github.tomcrawleyy.Snake.Enums.ID;
import com.github.tomcrawleyy.Snake.GameState.GameState;
import com.github.tomcrawleyy.Snake.GameState.Game.Food;
import com.github.tomcrawleyy.Snake.GameState.Game.Player;
import com.github.tomcrawleyy.Snake.GameState.Game.Tail;
import com.github.tomcrawleyy.Snake.GameState.Menu.MainMenu;
import com.github.tomcrawleyy.Snake.GameState.Settings.SettingsMenu;

public class Handler {

	public Player player;
	public Food food;
	public MainMenu menu;
	public SettingsMenu setMen;
	public AudioPlayer audioPlayer;
	private Random r = new Random();

	public void loadHandler() {
		player = new Player(Snake.WIDTH / 2 - 16, Snake.HEIGHT / 2 - 16, ID.Player);
		int x = r.nextInt((Snake.WIDTH - 64) / 16) * 16;
		int y = r.nextInt((Snake.HEIGHT - 64) / 16) * 16;
		food = new Food(x + 32, y + 32, ID.Food);
		menu = new MainMenu(this);
		setMen = new SettingsMenu(this);
	}

	public void tick() {
		GameState state = Snake.getState();
		if (audioPlayer != null)
			audioPlayer.tick();
		if (state.getCurrentState() == EGameState.Game) {
			player.tick();
			food.tick();
			Iterator<Tail> tailIterator = player.getTail().iterator();
			while (tailIterator.hasNext()) {
				Tail tail = tailIterator.next();
				tail.tick();
			}
		} else if (state.getCurrentState() == EGameState.Menu) {
			menu.tick();
		} else if (state.getCurrentState() == EGameState.Settings) {
			setMen.tick();
		}
	}

	public void render(Graphics g) {
		GameState state = Snake.getState();
		if (audioPlayer != null)
			audioPlayer.render(g);
		if (state.getCurrentState() == EGameState.Game) {
			player.render(g);
			food.render(g);
			Iterator<Tail> tailIterator = player.getTail().iterator();
			while (tailIterator.hasNext()) {
				Tail tail = tailIterator.next();
				tail.render(g);
			}
		} else if (state.getCurrentState() == EGameState.Menu) {
			menu.render(g);
		} else if (state.getCurrentState() == EGameState.Settings) {
			setMen.render(g);
		}
	}

}

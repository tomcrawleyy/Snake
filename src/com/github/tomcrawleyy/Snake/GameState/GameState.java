package com.github.tomcrawleyy.Snake.GameState;

import java.awt.Menu;
import java.util.Random;

import com.github.tomcrawleyy.Snake.Snake;
import com.github.tomcrawleyy.Snake.Audio.AudioPlayer;
import com.github.tomcrawleyy.Snake.Enums.EGameState;
import com.github.tomcrawleyy.Snake.Enums.ID;
import com.github.tomcrawleyy.Snake.GameState.Game.Food;
import com.github.tomcrawleyy.Snake.GameState.Game.Player;
import com.github.tomcrawleyy.Snake.GameState.Menu.MainMenu;
import com.github.tomcrawleyy.Snake.Handlers.Handler;

public class GameState {
	
	private EGameState state;
	private Handler handler;
	
	public GameState(EGameState state, Handler handler){
		changeState(state);
		this.handler = handler;
	}
	
	public void changeState(EGameState state){
		this.state = state;
		if (state != EGameState.Menu)
			handler.menu.reloadAnimation();
	}
	
	public EGameState getCurrentState(){
		return state;
	}

}

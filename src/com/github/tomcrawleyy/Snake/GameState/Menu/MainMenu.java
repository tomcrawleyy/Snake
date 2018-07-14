package com.github.tomcrawleyy.Snake.GameState.Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import com.github.tomcrawleyy.Snake.Snake;
import com.github.tomcrawleyy.Snake.Audio.AudioPlayer;
import com.github.tomcrawleyy.Snake.Enums.EGameState;
import com.github.tomcrawleyy.Snake.Handlers.Handler;
import com.github.tomcrawleyy.Snake.Interface.IMenu;

public class MainMenu extends MouseAdapter implements IMenu {

	private List<Button> buttons = new ArrayList<Button>();
	private Handler handler;

	private Button highlightedButton;
	private List<AnimationSquare> squares = new ArrayList<AnimationSquare>();
	private int tickCount = 0;

	public MainMenu(Handler handler) {
		this.handler = handler;
		buttons.add(new Button(64, 300, Snake.WIDTH - 128, 64, "Play Game", 20, 270, 340));
		buttons.add(new Button(64, 400, Snake.WIDTH - 128, 64, "Settings", 20, 280, 440));
		buttons.add(new Button(64, 500, Snake.WIDTH - 128, 64, "Quit", 20, 300, 540));
		loadAnimation();
	}

	public void tick() {
		for (Button button : buttons) {
			button.tick();
		}
		tickCount++;
		if (tickCount == 4){
			playAnimation();
			tickCount = 0;
		}
	}
	
	public void playAnimation(){
		for (AnimationSquare square : squares){
			if (!square.isVisible()){
				square.setVisible(true);
				return;
			}
		}
	}
	
	public void reloadAnimation(){
		for (AnimationSquare square : squares){
			square.setVisible(false);
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		g.drawString("Last Score: " + handler.player.getLastScore(), 64, 280);
		g.drawString("Session High Score: " + handler.player.getSessionHighScore(), 236, 280);
		g.drawString("Best Score: " + handler.player.getHighScore(), 475, 280);
		for (Button button : buttons) {
			button.render(g);
		}
		for (AnimationSquare square : squares){
			if (square.isVisible()){
				square.render(g);
			}
		}
	}

	public void mousePressed(MouseEvent e) {
		if (Snake.getState().getCurrentState() != EGameState.Menu)
			return;
		for (Button button : buttons) {
			if (button.isButton(e.getX(), e.getY())) {
				if (button.getText().equalsIgnoreCase("Play Game")){
					Snake.getState().changeState(EGameState.Game);
					AudioPlayer.getSound("menuSong").stop();
					AudioPlayer.getSound("song").loop();
				}
				if (button.getText().equalsIgnoreCase("Quit"))
					System.exit(1);
				if (button.getText().equalsIgnoreCase("Settings")) {
					Snake.getState().changeState(EGameState.Settings);
				}
			}
		}
	}

	public void mouseMoved(MouseEvent e) {
		if (Snake.getState().getCurrentState() != EGameState.Menu)
			return;
		if (highlightedButton != null){
			if (!highlightedButton.isButton(e.getX(), e.getY())){
				highlightedButton.dehighlight();
				highlightedButton = null;
			}
		}
		for (Button button : buttons) {
			if (button.isButton(e.getX(), e.getY())) {
				if (highlightedButton == button)
					return;
				highlightedButton = button;
				highlightedButton.highlight();
				AudioPlayer.getSound("buttonHover").play();
			}
		}
	}

	public void mouseReleased(MouseEvent e) {

	}
	
	
	public void loadAnimation(){
		//S
		squares.add(new AnimationSquare(144 + 32, 32));
		squares.add(new AnimationSquare(128 + 32, 32));
		squares.add(new AnimationSquare(112 + 32, 32));
		squares.add(new AnimationSquare(96 + 32, 32));
		squares.add(new AnimationSquare(80 + 32, 32));
		squares.add(new AnimationSquare(80 + 32, 48));
		squares.add(new AnimationSquare(80 + 32, 64));
		squares.add(new AnimationSquare(96 + 32, 64));
		squares.add(new AnimationSquare(112 + 32, 64));
		squares.add(new AnimationSquare(128 + 32, 64));
		squares.add(new AnimationSquare(144 + 32, 64));
		squares.add(new AnimationSquare(144 + 32, 80));
		squares.add(new AnimationSquare(144 + 32, 96));
		squares.add(new AnimationSquare(128 + 32, 96));
		squares.add(new AnimationSquare(112 + 32, 96));
		squares.add(new AnimationSquare(96 + 32, 96));
		squares.add(new AnimationSquare(80 + 32, 96));
		//N
		squares.add(new AnimationSquare(176 + 32, 96));
		squares.add(new AnimationSquare(176 + 32, 80));
		squares.add(new AnimationSquare(176 + 32, 64));
		squares.add(new AnimationSquare(176 + 32, 48));
		squares.add(new AnimationSquare(176 + 32, 32));
		squares.add(new AnimationSquare(192 + 32, 48));
		squares.add(new AnimationSquare(208 + 32, 64));
		squares.add(new AnimationSquare(224 + 32, 80));
		squares.add(new AnimationSquare(240 + 32, 96));
		squares.add(new AnimationSquare(240 + 32, 80));
		squares.add(new AnimationSquare(240 + 32, 64));
		squares.add(new AnimationSquare(240 + 32, 48));
		squares.add(new AnimationSquare(240 + 32, 32));
		//A
		squares.add(new AnimationSquare(272 + 32, 96));
		squares.add(new AnimationSquare(272 + 32, 80));
		squares.add(new AnimationSquare(272 + 32, 64));
		squares.add(new AnimationSquare(272 + 32, 48));
		squares.add(new AnimationSquare(272 + 32, 32));
		squares.add(new AnimationSquare(288 + 32, 32));
		squares.add(new AnimationSquare(304 + 32, 32));
		squares.add(new AnimationSquare(320 + 32, 32));
		squares.add(new AnimationSquare(320 + 32, 48));
		squares.add(new AnimationSquare(320 + 32, 64));
		squares.add(new AnimationSquare(320 + 32, 80));
		squares.add(new AnimationSquare(320 + 32, 96));
		squares.add(new AnimationSquare(288 + 32, 64));
		squares.add(new AnimationSquare(304 + 32, 64));
		//K
		squares.add(new AnimationSquare(352 + 32, 96));
		squares.add(new AnimationSquare(352 + 32, 80));
		squares.add(new AnimationSquare(352 + 32, 64));
		squares.add(new AnimationSquare(352 + 32, 48));
		squares.add(new AnimationSquare(352 + 32, 32));
		squares.add(new AnimationSquare(368 + 32, 64));
		squares.add(new AnimationSquare(384 + 32, 48));
		squares.add(new AnimationSquare(384 + 32, 80));
		squares.add(new AnimationSquare(400 + 32, 96));
		squares.add(new AnimationSquare(400 + 32, 32));
		//E
		squares.add(new AnimationSquare(432 + 32, 96));
		squares.add(new AnimationSquare(432 + 32, 80));
		squares.add(new AnimationSquare(432 + 32, 64));
		squares.add(new AnimationSquare(432 + 32, 48));
		squares.add(new AnimationSquare(432 + 32, 32));
		squares.add(new AnimationSquare(448 + 32, 32));
		squares.add(new AnimationSquare(464 + 32, 32));
		squares.add(new AnimationSquare(480 + 32, 32));
		squares.add(new AnimationSquare(448 + 32, 64));
		squares.add(new AnimationSquare(464 + 32, 64));
		squares.add(new AnimationSquare(480 + 32, 64));
		squares.add(new AnimationSquare(448 + 32, 96));
		squares.add(new AnimationSquare(464 + 32, 96));
		squares.add(new AnimationSquare(480 + 32, 96));
	}

}

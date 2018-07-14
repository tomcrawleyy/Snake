package com.github.tomcrawleyy.Snake.GameState.Settings;

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
import com.github.tomcrawleyy.Snake.GameState.Menu.AnimationSquare;
import com.github.tomcrawleyy.Snake.GameState.Menu.Button;
import com.github.tomcrawleyy.Snake.Handlers.Handler;
import com.github.tomcrawleyy.Snake.Interface.IMenu;

public class SettingsMenu extends MouseAdapter implements IMenu {
	
	private Handler handler;
	private List<Button> buttons = new ArrayList<Button>();
	private Button highlightedButton;

	public SettingsMenu(Handler handler) {
		this.handler = handler;
		buttons.add(new Button(64, 200, Snake.WIDTH - 128, 64, "Back", 20, 280, 240));
	}

	public void tick() {
		for (Button button : buttons) {
			button.tick();
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 48));
		g.drawString("Coming Soon", 150, 128);
		for (Button button : buttons) {
			button.render(g);
		}
	}
	
	public void mousePressed(MouseEvent e) {
		if (Snake.getState().getCurrentState() != EGameState.Settings)
			return;
		for (Button button : buttons) {
			if (button.isButton(e.getX(), e.getY())) {
				if (button.getText().equalsIgnoreCase("Back")){
					Snake.getState().changeState(EGameState.Menu);
				}
			}
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		if (Snake.getState().getCurrentState() != EGameState.Settings)
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

}

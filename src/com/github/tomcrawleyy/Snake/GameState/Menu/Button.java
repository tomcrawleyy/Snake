package com.github.tomcrawleyy.Snake.GameState.Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Button {

	private int x;
	private int y;
	private int xSize;
	private int ySize;
	private String text;
	private int textSize;
	private int textPosX;
	private int textPosY;
	private boolean highlight = false;
	private int highlightTicks = 0;

	public Button(int x, int y, int xSize, int ySize, String text, int textSize, int textPosX, int textPosY) {
		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
		this.text = text;
		this.textSize = textSize;
		this.textPosX = textPosX;
		this.textPosY = textPosY;
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(x - highlightTicks, y - highlightTicks, xSize + (highlightTicks *2), ySize + (highlightTicks * 2));
		g.setFont(new Font(Font.DIALOG, Font.BOLD, textSize + (highlightTicks/2)));
		g.drawString(text, textPosX - highlightTicks, textPosY);
	}

	public String getText() {
		return text;
	}

	public boolean isButton(int x, int y) {
		if (x >= this.x && x <= this.x + xSize && y >= this.y && y <= this.y + ySize) {
			return true;
		}
		return false;
	}

	public void highlight() {
		highlight = true;
	}

	public void dehighlight() {
		highlight = false;
	}

	public void tick() {
		if (highlight) {
			if (highlightTicks < 15)
				highlightTicks++;
		} else {
			if (highlightTicks > 0) {
				highlightTicks--;
			}
		}
	}

}

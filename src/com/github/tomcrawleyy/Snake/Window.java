package com.github.tomcrawleyy.Snake;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {

	private static final long serialVersionUID = -9070525456659393902L;

	public Window(int width, int height, String title, Snake snake) {
		JFrame frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width + 6, height + 28));
		frame.setMaximumSize(new Dimension(width + 6, height + 28));
		frame.setMinimumSize(new Dimension(width + 6, height + 28));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(snake);
		frame.setVisible(true);
		snake.start();
	}

}

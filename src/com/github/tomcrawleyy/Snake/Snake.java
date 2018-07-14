package com.github.tomcrawleyy.Snake;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import com.github.tomcrawleyy.Snake.Audio.AudioPlayer;
import com.github.tomcrawleyy.Snake.Enums.EGameState;
import com.github.tomcrawleyy.Snake.GameState.GameState;
import com.github.tomcrawleyy.Snake.GameState.Menu.MainMenu;
import com.github.tomcrawleyy.Snake.Handlers.Handler;
import com.github.tomcrawleyy.Snake.Handlers.KeyInput;

public class Snake extends Canvas implements Runnable {

	private static final long serialVersionUID = 8739950447357828227L;
	public static final int WIDTH = 640, HEIGHT = 640;
	private static Snake snake;

	private Thread thread;
	private boolean running = false;
	private Handler handler;
	public static GameState state;

	public Snake() {
		this.handler = new Handler();
		new AudioPlayer(handler);
		state = new GameState(EGameState.Menu, handler);
		AudioPlayer.getSound("menuSong").loop();
		handler.loadHandler();
		new Window(WIDTH, HEIGHT, "Snake V1.0", this);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(handler.menu);
		this.addMouseMotionListener(handler.menu);
		this.addMouseListener(handler.setMen);
		this.addMouseMotionListener(handler.setMen);
	}
	
	public static GameState getState(){
		return state;
	}

	public static void main(String[] args) {
		snake = new Snake();
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		// ns = 1/60th of a second
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			// delta = currenttime - timestamp from last time the iteration was
			// run devided by 1/60th of a second, 1 in terms of delta = 60th of
			// a second
			delta += (now - lastTime) / ns;
			lastTime = now;
			/*
			 * checking whether enough time has passed for the refresh to take
			 * place (1/60 sec) once delta = 1, enough time will have passed to
			 * go forward by one tick
			 */
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			// frames will be incremented every time this iteration is run
			frames++;
			// Check to see if a second has passed to figure out the FPS
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
				// resetting the frames for the next iteration to get the fps
				// next second
			}
		}
		stop();
	}

	private void tick() {
		handler.tick();
	}
	
	public static Snake getSnake(){
		return snake;
	}
	
	public Handler getHandler(){
		return handler;
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g);
		g.dispose();
		bs.show();
	}

}

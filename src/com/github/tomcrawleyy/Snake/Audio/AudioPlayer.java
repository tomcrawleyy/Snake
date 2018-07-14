package com.github.tomcrawleyy.Snake.Audio;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import com.github.tomcrawleyy.Snake.Snake;
import com.github.tomcrawleyy.Snake.Handlers.Handler;

public class AudioPlayer {
	
	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	private Handler handler;
	private static int renderTicks = 0;
	private static String songName = "";
	private static int colorValue = 240;
	
	public AudioPlayer(Handler handler){
		this.handler = handler;
		init();
		handler.audioPlayer = this;
	}
	
	public void tick(){
		if (renderTicks > 0){
			renderTicks--;
			colorValue -= 1;
		}
	}
	
	public void render(Graphics g){
		if (renderTicks != 0){
			g.setColor(new Color(colorValue, colorValue, colorValue));
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
			g.drawString("Now Playing: " + songName, 8, Snake.HEIGHT - 10);
		}
	}
	
	public static void init(){
		try {
			soundMap.put("song", new Sound("res/Song.wav"));
			soundMap.put("menuSong", new Sound("res/MenuSong.wav"));
			soundMap.put("eat", new Sound("res/Eat.wav"));
			soundMap.put("buttonHover", new Sound("res/ButtonHover.wav"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static Sound getSound(String key){
		if (!key.equalsIgnoreCase("eat") && !key.equalsIgnoreCase("buttonHover")){
			if (key.equals("menuSong"))
				songName = "Take On Me [8Bit Tribute to A-HA] - 8BitUniverse";
			if (key.equals("song"))
				songName = "Evil Morty's Theme [8Bit Tribute to Blonde Redhead & Rick & Morty] - 8BitUniverse";
			renderTicks = 240;
			colorValue = 240;
		}
		return soundMap.get(key);
	}
}

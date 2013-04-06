package com.neodem.combat.logic;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.neodem.combat.Constants;
import com.neodem.graphics.dd.engine.wotlas.Drawable;
import com.neodem.graphics.dd.engine.wotlas.GraphicsDirector;
import com.neodem.graphics.dd.engine.wotlas.ImageLibrary;
import com.neodem.graphics.dd.engine.wotlas.drawable.MotionlessRectangle;

public class GameEngine implements Constants {
	
	private GraphicsDirector g;
	private ImageLibrary imageLibrary;

	private Drawable backgroundImage;
	private Wallset walls;
	private ScoreManager score;
	private TankManager tanks;


	public GameEngine(GraphicsDirector director, ImageLibrary imageLib) {
		g = director;
		imageLibrary = imageLib;
		backgroundImage = (Drawable) new MotionlessRectangle("background", 0, 80, 640, 386, Color.gray, PRIORITY_BG, false);
		
		//	ImageIdentifier id = new ImageIdentifier("walls-2/all-12.png");
		//	backgroundImage = (Drawable) new MotionlessSprite(0, 0, id, PRIORITY_BG);
	
	}

	public void eventDispatched(KeyEvent event) {
		tanks.eventDispatched(event);
	}

	public void tick() {
		tanks.tick();
	}

	public void init() {
		walls = new Wallset(g, imageLibrary);
		score = new ScoreManager(g, imageLibrary);
		tanks = new TankManager(g, imageLibrary, 2, walls);
	}

	/**
	 * @return Returns the backgroundImage.
	 */
	public Drawable getBackgroundImage() {
		return backgroundImage;
	}

	/**
	 * @param backgroundImage
	 *            The backgroundImage to set.
	 */
	public void setBackgroundImage(Drawable backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

}

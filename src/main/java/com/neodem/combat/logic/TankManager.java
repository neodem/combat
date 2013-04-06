package com.neodem.combat.logic;

import java.awt.event.KeyEvent;

import com.neodem.combat.Tank;
import com.neodem.graphics.dd.engine.wotlas.GraphicsDirector;
import com.neodem.graphics.dd.engine.wotlas.ImageLibrary;
import com.neodem.graphics.dd.engine.wotlas.drawable.Sprite;
import com.neodem.graphics.dd.engine.wotlas.filter.ColorImageFilter;

public class TankManager {

	private GraphicsDirector g;
	private ImageLibrary imageLibrary;
	private Wallset walls;
	
	private Tank[] tanks;
	private int numberOfTanks;
	

	public TankManager(GraphicsDirector director, ImageLibrary imageLib, int numberOfTanks, Wallset walls) {
		g = director;
		imageLibrary = imageLib;
		this.walls = walls;
		//TODO: fix for variable # of tanks
		this.numberOfTanks = 2;
		tanks = new Tank[numberOfTanks];
		init();
	}

	public void eventDispatched(KeyEvent event) {
		for (int i = 0; i < numberOfTanks; i++) {
			tanks[i].eventDispatched(event);
		}
	}

	public void tick() {
		for (int i = 0; i < numberOfTanks; i++) {
			tanks[i].tick();
		}
	}

	public void init() {

		tanks[0] = new Tank(true, imageLibrary, this);
		tanks[1] = new Tank(false, imageLibrary, this);

		setupTanks();
	}

	private void setupTanks() {
		tanks[0].setup(g, 42, 260, 0, ColorImageFilter.red);
		tanks[1].setup(g, 566, 260, (float) Math.PI, ColorImageFilter.green);
	}

	/** determine if the tank sprite is in contact with a wall or other barrier
	 * 
	 * @param newX
	 * @param newY
	 * @param newO
	 * @param sprite
	 * @return
	 */
	public boolean noContact(float newX, float newY, float newO, Sprite sprite) {
		// first check bounding rectangle overlap if there is none, we return true
		
		
		// next check pixel perfect
		
		// all clear?
		return true;
	}

}

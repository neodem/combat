package com.neodem.combat.logic;

import com.neodem.combat.Constants;
import com.neodem.graphics.dd.engine.wotlas.Drawable;
import com.neodem.graphics.dd.engine.wotlas.GraphicsDirector;
import com.neodem.graphics.dd.engine.wotlas.ImageIdentifier;
import com.neodem.graphics.dd.engine.wotlas.ImageLibrary;
import com.neodem.graphics.dd.engine.wotlas.drawable.MotionlessImage;


public class Wallset implements Constants {

	private static final int NUM_WALLS = 15;
	
	private GraphicsDirector g;
	private ImageLibrary imageLibrary;

	private Drawable[] walls;


	public Wallset(GraphicsDirector director, ImageLibrary imageLib) {
		g = director;
		imageLibrary = imageLib;
		walls = new Drawable[NUM_WALLS];	
		init();
	}


	public void init() {
		// setup drawables
		setupWalls();
	}


	private void setupWalls() {
		// main circular wall
		addWall(0,0,80,WALL_0_LOCATION, PRIORITY_WALL);
		
		// circular wall buttresses
		addWall(1,304,98, WALL_2_LOCATION, PRIORITY_WALL);
		addWall(2,304,417, WALL_2_LOCATION, PRIORITY_WALL);		
		
		// the main barriers
		addWall(3,80,210,WALL_1_LOCATION, PRIORITY_WALL);
		addWall(4,528,210,WALL_6_LOCATION, PRIORITY_WALL);
		
		// the boxes in front of the barriers
		addWall(5,160,258, WALL_2_LOCATION, PRIORITY_WALL);
		addWall(6,448,258, WALL_2_LOCATION, PRIORITY_WALL);	
		
		// flat bars
		addWall(7,96,146, WALL_5_LOCATION, PRIORITY_WALL);
		addWall(8,496,146, WALL_5_LOCATION, PRIORITY_WALL);	
		addWall(9,96,386, WALL_5_LOCATION, PRIORITY_WALL);
		addWall(10,496,386, WALL_5_LOCATION, PRIORITY_WALL);
		
		// center bars
		addWall(11,368,178, WALL_3_LOCATION, PRIORITY_WALL);
		addWall(12,224,178, WALL_7_LOCATION, PRIORITY_WALL);
		addWall(13,224,338, WALL_4_LOCATION, PRIORITY_WALL);
		addWall(14,368,338, WALL_8_LOCATION, PRIORITY_WALL);
	}

	/**
	 * helper
	 * 
	 * @param x
	 * @param y
	 * @param location
	 * @param priority
	 */
	private void addWall(int index, int x, int y, String location, short priority) {
		ImageIdentifier id = new ImageIdentifier(location);
		walls[index] = new MotionlessImage(x, y, id, priority);
		g.addDrawable(walls[index]);
	}

}

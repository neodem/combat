package com.neodem.combat.logic;

import com.neodem.graphics.dd.engine.wotlas.GraphicsDirector;
import com.neodem.graphics.dd.engine.wotlas.ImageLibrary;


public class ScoreManager {

	private GraphicsDirector g;
	private ImageLibrary imageLibrary;
	
	private int player1;
	private int player2;
	
	public ScoreManager(GraphicsDirector director, ImageLibrary imageLib) {
		g = director;
		imageLibrary = imageLib;
		init();
	}


	public void init() {
		player1 = 0;
		player2 = 0;
		
	}

}

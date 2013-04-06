package com.neodem.combat;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.neodem.combat.logic.GameEngine;
import com.neodem.graphics.dd.engine.wotlas.EnhancedGraphicsDirector;
import com.neodem.graphics.dd.engine.wotlas.FontFactory;
import com.neodem.graphics.dd.engine.wotlas.GraphicsDirector;
import com.neodem.graphics.dd.engine.wotlas.ImageLibrary;
import com.neodem.graphics.dd.engine.wotlas.policy.CenterWindowPolicy;

public class Combat extends JFrame implements AWTEventListener, Constants {

	private static final long serialVersionUID = 1L;
	private GraphicsDirector gDirector;
	private ImageLibrary imageLib;
	private GameEngine game;


	/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

	public Combat() {
		super("Combat");

		// create factories and engine
		FontFactory.createDefaultFontFactory(FONT_DIRECTORY);
		imageLib = ImageLibrary.createImageLibrary(IMAGE_DIRECTORY);
		gDirector = new EnhancedGraphicsDirector(new CenterWindowPolicy(), imageLib);

		// create game engine
		game = new GameEngine(gDirector, imageLib);
		
		// init the graphics engine 
		gDirector.init(game.getBackgroundImage(), null, new Dimension(GAME_WIDTH, GAME_HEIGHT));

		// setup game engine and init graphics
		game.init();

		// add listeners
		Toolkit.getDefaultToolkit().addAWTEventListener(this, KeyEvent.KEY_EVENT_MASK);
		
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				gDirector.removeAllDrawables(); 
				System.exit(0);
			}
		});

		// add engine to the window and set it up
		getContentPane().add(gDirector, BorderLayout.CENTER);
		pack();
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screensize.width - getWidth()) / 2, (screensize.height - getHeight()) / 2);
		setVisible(true);
	}

	
	/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

	/**
	 * We transmit Key events to our Woman character.
	 */
	public void eventDispatched(AWTEvent e) {
		if (e instanceof KeyEvent) {
			game.eventDispatched((KeyEvent) e);
		}
	}

	/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

	/**
	 * Our tick method. The game time is composed of ticks where we update
	 * positions, refresh the screen, etc... We create a thread to call these
	 * tick() regularly.
	 */
	public void tick() {
		game.tick();
		gDirector.tick();
	}

	/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

	public static void main(String argv[]) {

		// 1 - We create our GraphicsDemo Frame...
		Combat app = new Combat();

		// 2 - ... and we tick it regularly
		Object lock = new Object();

		while (true) {
			// 2.1 - tick on our demo
			app.tick();

			// 2.2 - MANDATORY : we wait 20ms minimum to let eventual other
			// tasks be processed
			// If you don't wait some time your application might become VERY
			// slow ( task scheduler
			// saturated ).
			synchronized (lock) {
				try {
					lock.wait(20);
				} catch (Exception e) {
				}
			}
		}
	}
}

/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */


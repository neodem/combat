package com.neodem.combat;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import com.neodem.combat.logic.TankManager;
import com.neodem.graphics.dd.engine.wotlas.Animation;
import com.neodem.graphics.dd.engine.wotlas.Drawable;
import com.neodem.graphics.dd.engine.wotlas.GraphicsDirector;
import com.neodem.graphics.dd.engine.wotlas.ImageIdentifier;
import com.neodem.graphics.dd.engine.wotlas.ImageLibrary;
import com.neodem.graphics.dd.engine.wotlas.drawable.Sprite;
import com.neodem.graphics.dd.engine.wotlas.drawable.SpriteDataSupplier;
import com.neodem.graphics.dd.engine.wotlas.filter.ColorImageFilter;
import com.neodem.graphics.dd.engine.wotlas.filter.ColorType;

public class Tank implements SpriteDataSupplier {

	private Animation animation;
	private boolean spinning = false;
	private Sprite sprite;
	private boolean isPlayerControlled;
	private float x, y, orientation;
	private boolean isMoving;
	private Dimension backgroundDim;
	private TankManager manager;

	public Tank(boolean isPlayerControlled, ImageLibrary imageLib, TankManager manager) {
		this.isPlayerControlled = isPlayerControlled;
		this.manager = manager;

		animation = new Animation(new ImageIdentifier("players-1/tank-0"), imageLib);

		sprite = new Sprite((SpriteDataSupplier) this, ImageLibRef.PLAYER_PRIORITY);
		sprite.useAntialiasing(true);
	}

	public void setup(GraphicsDirector director, int x, int y, float orientation, ColorType c) {
		backgroundDim = director.getBackgroundDimension();
		this.x = x;
		this.y = y;
		this.orientation = orientation;

		ColorImageFilter filter = new ColorImageFilter();
		sprite.setDynamicImageFilter(filter);

		filter.addColorChangeKey(ColorImageFilter.red, c);

		director.addDrawable(sprite);
	}

	public void eventDispatched(KeyEvent key) {
		if (isPlayerControlled) {
			float newX = x;
			float newY = y;
			float newO = orientation;

			switch (key.getKeyCode()) {
			case KeyEvent.VK_UP:
				isMoving = true;
				newX = (float) (x + 2 * Math.cos(orientation));
				newY = (float) (y + 2 * Math.sin(orientation));
				break;

			case KeyEvent.VK_DOWN:
				isMoving = true;
				newX = (float) (x - 2 * Math.cos(orientation));
				newY = (float) (y - 2 * Math.sin(orientation));
				break;

			case KeyEvent.VK_LEFT:
				isMoving = true;
				newO -= 0.1;
				break;

			case KeyEvent.VK_RIGHT:
				isMoving = true;
				newO += 0.1;
				break;
			}

			// are we touching a wall?
			if(manager.noContact(newX, newY, newO, sprite)) {
				x = newX;
				y = newY;
				orientation = newO;
			}
		}
	}

	public void tick() {

		if (spinning)
			animation.tick(); // select next image
		else
			animation.reset(); // return to first image

		spinning = false;
	}


	/**
	 * To get the X image position.
	 * 
	 * @return x image coordinate
	 */
	public int getX() {
		return (int) x;
	}

	/**
	 * To get the Y image position.
	 * 
	 * @return y image cordinate
	 */
	public int getY() {
		return (int) y;
	}

	/**
	 * To get the image identifier to use.
	 * 
	 * @return image identifier.
	 */
	public ImageIdentifier getImageIdentifier() {
		return animation.getCurrentImage();
	}

	/**
	 * To get the eventual rotation angle. 0 means no rotation.
	 * 
	 * @return angle in radians.
	 */
	public double getAngle() {
		return orientation;
	}

	/**
	 * To get the X factor for scaling... 1.0 means no X scaling
	 * 
	 * @return X scale factor
	 */
	public double getScaleX() {
		return 1.0;
	}

	/**
	 * To get the Y factor for scaling... 1.0 means no Y scaling
	 * 
	 * @return Y scale factor
	 */
	public double getScaleY() {
		return 1.0;
	}

	/**
	 * To get the image's transparency ( 0.0 means invisible, 1.0 means fully
	 * visible ).
	 * 
	 * @return alpha
	 */
	public float getAlpha() {
		return 1.0f;
	}

	/**
	 * To get the drawable of this woman.
	 */
	public Drawable getDrawable() {
		return sprite;
	}

}

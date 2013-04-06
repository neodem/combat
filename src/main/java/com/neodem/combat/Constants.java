package com.neodem.combat;


public interface Constants {
	public static final String FONT_DIRECTORY = "data/fonts";
	public static final String IMAGE_DIRECTORY = "data/graphics";
	
	public static final String WALL_0_LOCATION = "walls-0/wall-0.png";
	public static final String WALL_1_LOCATION = "walls-0/wall-1.png";
	public static final String WALL_2_LOCATION = "walls-0/wall-2.png";
	public static final String WALL_3_LOCATION = "walls-0/wall-3.png";
	public static final String WALL_4_LOCATION = "walls-0/wall-4.png";
	public static final String WALL_5_LOCATION = "walls-0/wall-5.png";
	public static final String WALL_6_LOCATION = "walls-0/wall-6.png";
	public static final String WALL_7_LOCATION = "walls-0/wall-7.png";
	public static final String WALL_8_LOCATION = "walls-0/wall-8.png";
	
	
	public static final int GAME_WIDTH = 640;
	public static final int GAME_HEIGHT = 480;
	
    public final static short PRIORITY_BG      = 0;      // lowest priority, drawn first
    public final static short PRIORITY_WALL     = 5;      // lowest priority, drawn first
    public final static short PRIORITY_SHADOW   = 8;      // shadows
    public final static short PRIORITY_PLAYER   = 50;     // players
    public final static short PRIORITY_TEXT     = 150;    // text displayed
}

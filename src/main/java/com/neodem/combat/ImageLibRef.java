package com.neodem.combat;


public interface ImageLibRef {
    public final static short MAP_PRIORITY      = 0;      // lowest priority, drawn first
    public final static short SHADOW_PRIORITY   = 5;      // shadows
    public final static short AURA_PRIORITY     = 20;     // small player auras
    public final static short PLAYER_PRIORITY   = 50;     // players
    public final static short TEXT_PRIORITY     = 150;    // text displayed
}

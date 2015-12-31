package com.deviotion.terraria.gfx;

import uk.co.nozzer.engine.gfx.Bitmap;
import uk.co.nozzer.engine.gfx.Spritesheet;

public class Art {
	public static final Spritesheet TILES = new Spritesheet(new Bitmap("/tiles.png"), 8);
	public static final Spritesheet FONT = new Spritesheet(new Bitmap("/font.png"), 6, 9);
	public static final Spritesheet PLAYERSHEET = new Spritesheet(new Bitmap("/player.png"), 8, 16);
	
	public static final Bitmap TILE_DIRT = Art.TILES.getSprite(0, 0);
	public static final Bitmap TILE_GRASS = Art.TILES.getSprite(1, 0);
	public static final Bitmap TILE_STONE = Art.TILES.getSprite(2, 0);
	public static final Bitmap TILE_GRASS_TOP = Art.TILES.getSprite(3, 0);
	
	public static final Bitmap PLAYER = Art.PLAYERSHEET.getSprite(0, 0);
}

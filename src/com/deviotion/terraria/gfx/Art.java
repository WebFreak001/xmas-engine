package com.deviotion.terraria.gfx;

import uk.co.nozzer.gfx.Bitmap;
import uk.co.nozzer.gfx.Spritesheet;

public class Art {
	public static final Spritesheet TILES = new Spritesheet(new Bitmap("/tiles.png"), 8);
	
	public static final Bitmap TILE_DIRT = Art.TILES.getSprite(0, 0);
	public static final Bitmap TILE_GRASS = Art.TILES.getSprite(1, 0);
}

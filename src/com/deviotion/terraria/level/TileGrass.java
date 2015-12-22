package com.deviotion.terraria.level;

import com.deviotion.terraria.gfx.Art;

import uk.co.nozzer.gfx.Bitmap;
import uk.co.nozzer.maths.Vector2f;

public class TileGrass extends Tile {

	public TileGrass(Vector2f position) {
		super(position);
	}

	@Override
	public void update(double delta) {
		
	}

	@Override
	public void render(Bitmap screen) {
		screen.blit(Art.TILE_GRASS, (int) position.getX() * Level.TILE_SIZE, (int) position.getY() * Level.TILE_SIZE);		
	}

}

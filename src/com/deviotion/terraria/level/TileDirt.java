package com.deviotion.terraria.level;

import com.deviotion.terraria.gfx.Art;

import uk.co.nozzer.gfx.Bitmap;
import uk.co.nozzer.maths.Vector2f;

public class TileDirt extends Tile {

	public TileDirt(Vector2f position) {
		super(position);
	}

	@Override
	public void update(double delta) {
		
	}

	@Override
	public void render(Bitmap screen) {
		screen.blit(Art.TILE_DIRT, (int) position.getX() * Level.TILE_SIZE, (int) position.getY() * Level.TILE_SIZE);
	}

}

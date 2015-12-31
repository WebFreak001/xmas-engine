package com.deviotion.terraria.level;

import com.deviotion.terraria.gfx.Art;

import uk.co.nozzer.engine.gfx.Bitmap;
import uk.co.nozzer.engine.maths.Vector2f;

public class TileStone extends Tile {

	public TileStone(Vector2f position, Level level) {
		super(position, level);
	}

	@Override
	public void update(double delta) {
		
	}

	@Override
	public void render(Bitmap screen) {
		screen.blit(Art.TILE_STONE, (int) position.getX() * Level.TILE_SIZE, (int) position.getY() * Level.TILE_SIZE);
	}

	// --- getters and setters
	public boolean isSolid() {
		return true;
	}
}

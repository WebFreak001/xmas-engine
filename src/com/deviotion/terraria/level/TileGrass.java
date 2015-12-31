package com.deviotion.terraria.level;

import com.deviotion.terraria.gfx.Art;

import uk.co.nozzer.engine.gfx.Bitmap;
import uk.co.nozzer.engine.maths.Vector2f;

public class TileGrass extends Tile {

	public TileGrass(Vector2f position, Level level) {
		super(position, level);
	}

	@Override
	public void update(double delta) {

	}

	@Override
	public void render(Bitmap screen) {
		if (level.getTile((int) this.position.getX(), (int) this.position.getY() + 1) instanceof TileDirt) {
			screen.blit(Art.TILE_GRASS_TOP, (int) position.getX() * Level.TILE_SIZE, (int) position.getY() * Level.TILE_SIZE);
		} else {
			screen.blit(Art.TILE_GRASS, (int) position.getX() * Level.TILE_SIZE, (int) position.getY() * Level.TILE_SIZE);
		}
	}

	// --- getters and setters
	public boolean isSolid() {
		return true;
	}
}

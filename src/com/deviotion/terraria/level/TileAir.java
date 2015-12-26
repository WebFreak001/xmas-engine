package com.deviotion.terraria.level;

import uk.co.nozzer.gfx.Bitmap;
import uk.co.nozzer.gfx.ColourUtils;
import uk.co.nozzer.maths.Vector2f;

public class TileAir extends Tile {

	public TileAir(Vector2f position, Level level) {
		super(position, level);
	}

	@Override
	public void update(double delta) {

	}

	@Override
	public void render(Bitmap screen) {
		int colour = ColourUtils.lighten(0x0000FF, (int) position.getY() * 5);
		if (colour > ColourUtils.lighten(0x0000FF, 30 * 5)) colour  = ColourUtils.lighten(0x0000FF, 30 * 5);
		screen.fillRectangle((int) position.getX() * Level.TILE_SIZE, (int) position.getY() * Level.TILE_SIZE,
				Level.TILE_SIZE, Level.TILE_SIZE, colour);
	}

	@Override
	public boolean isSolid() {
		return false;
	}
}

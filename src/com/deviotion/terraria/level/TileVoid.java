package com.deviotion.terraria.level;

import uk.co.nozzer.gfx.Bitmap;
import uk.co.nozzer.maths.Vector2f;

public class TileVoid extends Tile{

	public TileVoid(Vector2f position, Level level) {
		super(position, level);
	}

	@Override
	public void update(double delta) {
		
	}

	@Override
	public void render(Bitmap screen) {
		
	}

	@Override
	public boolean isSolid() {
		return true;
	}

}

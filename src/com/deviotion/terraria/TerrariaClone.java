package com.deviotion.terraria;

import com.deviotion.terraria.level.Level;

import uk.co.nozzer.Game;
import uk.co.nozzer.gfx.Bitmap;
import uk.co.nozzer.gfx.Window;
import uk.co.nozzer.maths.Dimension2f;

public class TerrariaClone extends Game {

	private Level level;
	
	public TerrariaClone(Window window) {
		super(window);
		
		this.level = new Level(new Dimension2f(16, 16));
	}

	@Override
	public void update(double delta) {
		level.update(delta);
	}

	@Override
	public void render(Bitmap screen) {
		level.render(screen);
	}

}

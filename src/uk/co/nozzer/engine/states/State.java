package uk.co.nozzer.engine.states;

import uk.co.nozzer.engine.gfx.Bitmap;

public abstract class State {
	public abstract void update(double delta);
	public abstract void render(Bitmap screen);
}

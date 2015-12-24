package uk.co.nozzer.states;

import uk.co.nozzer.gfx.Bitmap;

public abstract class State {
	public abstract void update(double delta);
	public abstract void render(Bitmap screen);
}

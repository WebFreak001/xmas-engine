package uk.co.nozzer.engine.entities;

import uk.co.nozzer.engine.gfx.Bitmap;

public abstract class Entity {
	
	public Entity() {
		
	}
	
	public abstract void update(double delta);
	public abstract void render(Bitmap screen);
}

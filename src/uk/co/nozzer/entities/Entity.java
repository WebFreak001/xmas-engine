package uk.co.nozzer.entities;

import uk.co.nozzer.gfx.Bitmap;

public abstract class Entity {
	
	public Entity() {
		
	}
	
	public abstract void update(double delta);
	public abstract void render(Bitmap screen);
}

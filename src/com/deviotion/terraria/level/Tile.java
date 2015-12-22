package com.deviotion.terraria.level;

import uk.co.nozzer.gfx.Bitmap;
import uk.co.nozzer.maths.Vector2f;

public abstract class Tile {

	protected Vector2f position;

	public Tile(Vector2f position) {
		this.position = position;
	}
	
	public abstract void update(double delta);
	public abstract void render(Bitmap screen);
	
	// --- getters and setters
	public Vector2f getPosition() {
		return this.position;
	}
	
	public void setPosition(Vector2f position) {
		this.position = position;
	}
}

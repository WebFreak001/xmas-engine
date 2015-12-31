package uk.co.nozzer.engine.entities.gfx;

import uk.co.nozzer.engine.entities.Entity;
import uk.co.nozzer.engine.gfx.Bitmap;
import uk.co.nozzer.engine.gfx.ColourUtils;
import uk.co.nozzer.engine.maths.Vector2f;

public class Light extends Entity {

	protected Vector2f position;
	protected int radius;
	protected int colour;
	protected int intensity;
	
	public Light(Vector2f position, int radius, int colour, int intensity) {
		this.position = position;
		this.radius = radius;
		this.colour = colour;
		this.intensity = intensity;
	
	}
	public Light(Vector2f position, int radius, int colour) {
		this.position = position;
		this.radius = radius;
		this.colour = colour;
		this.intensity = 50;
	}
	public Light(Vector2f position, int radius) {
		this.position = position;
		this.radius = radius;
		this.colour = 0xFFFFFF;
	}
	public Light(Vector2f position) {
		this.position = position;
		this.radius = 15;
		this.colour = 0xFFFFFF;
	}

	@Override
	public void update(double delta) {}

	@Override
	public void render(Bitmap screen) {
		for (int x = -radius; x <= radius; x++) {
			int xp = (int) position.getX() + x;
			for (int y = -radius; y <= radius; y++) {
				int yp = (int) position.getY() + y;
				if (x * x + y * y < radius * radius) {
					screen.setPixel(xp, yp, ColourUtils.add(screen.getPixel(xp, yp), 0xFFFFFF, 50));
				}
			}
		}
	}
	
	// --- getters and setters
	public Vector2f getPosition() {
		return this.position;
	}
	public int getRadius() {
		return this.radius;
	}
	public int getColour() {
		return this.colour;
	}
	public int getIntensity() {
		return this.intensity;
	}
	
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public void setColour(int colour) {
		this.colour = colour;
	}
	public void setIntensity(int intensity) {
		this.intensity = intensity;
	}
}

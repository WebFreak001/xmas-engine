package uk.co.nozzer.engine.entities;

import uk.co.nozzer.engine.gfx.Bitmap;
import uk.co.nozzer.engine.maths.Dimension2f;
import uk.co.nozzer.engine.maths.Vector2f;

public class EntityRenderable extends Entity {

	protected Vector2f position;
	protected Dimension2f size;
	protected int colour;
	
	public EntityRenderable(Vector2f position, Dimension2f size) {
		this.position = position;
		this.size = size;
	}
	
	@Override
	public void update(double delta) {
		
	}

	@Override
	public void render(Bitmap screen) {
		screen.fillRectangle(position, size, colour);
	}

	// --- getters and setters
	public Vector2f getPosition() {
		return this.position;
	}
	public Dimension2f getSize() {
		return this.size;
	}
	
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	public void setSize(Dimension2f size) {
		this.size = size;
	}
	
	public void setColour(int colour) {
		this.colour = colour;
	}
}

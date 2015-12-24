package uk.co.nozzer.entities;

import uk.co.nozzer.gfx.Bitmap;
import uk.co.nozzer.maths.Dimension2f;
import uk.co.nozzer.maths.Vector2f;

public class EntityRenderable extends Entity {

	protected Vector2f position;
	protected Dimension2f size;
	
	public EntityRenderable(Vector2f position, Dimension2f size) {
		this.position = position;
		this.size = size;
	}
	
	@Override
	public void update(double delta) {
		
	}

	@Override
	public void render(Bitmap screen) {
		screen.fillRectangle(position, size, 0xFF00FF);
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
}

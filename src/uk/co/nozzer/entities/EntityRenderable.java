package uk.co.nozzer.entities;

import uk.co.nozzer.gfx.Bitmap;
import uk.co.nozzer.maths.Dimension2f;
import uk.co.nozzer.maths.Vector2f;

public class EntityRenderable extends Entity {

	private Vector2f position;
	private Dimension2f size;
	
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

}

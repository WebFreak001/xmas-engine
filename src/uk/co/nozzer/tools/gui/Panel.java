package uk.co.nozzer.tools.gui;

import uk.co.nozzer.engine.gfx.Bitmap;
import uk.co.nozzer.engine.maths.Dimension2f;
import uk.co.nozzer.engine.maths.Vector2f;

public class Panel {

	private Vector2f position;
	private Dimension2f size;
	
	private int backgroundColour = 0x222222;
	
	public Panel(Vector2f position, Dimension2f size) {
		this.position = position;
		this.size = size;
	}
	
	public void update(double delta) {
		
	}
	
	public void render(Bitmap screen) {
		screen.fillRectangle(position, size, backgroundColour);
	}
	
	// --- getters and setters
	public int getBackgroundColour() {
		return this.backgroundColour;
	}
	public Vector2f getPosition() {
		return this.position;
	}
	public Dimension2f getSize() {
		return this.size;
	}
	
	public void setBackgroundColour(int colour) {
		this.backgroundColour = colour;
	}
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	public void setSize(Dimension2f size) {
		this.size = size;
	}
}

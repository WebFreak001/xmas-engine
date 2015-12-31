package uk.co.nozzer.tools.gui;

import uk.co.nozzer.engine.gfx.Bitmap;
import uk.co.nozzer.engine.maths.Dimension2f;
import uk.co.nozzer.engine.maths.Vector2f;

public class PanelMap extends Panel {

	private int scale = 10;
	
	private Bitmap test = new Bitmap("/tiles.png");
	
	public PanelMap(Vector2f position, Dimension2f size) {
		super(position, size);
	}
	
	@Override
	public void update(double delta) {
		
	}
	
	@Override
	public void render() {
		screen.blit(test, 0, 0, scale);
	}

}

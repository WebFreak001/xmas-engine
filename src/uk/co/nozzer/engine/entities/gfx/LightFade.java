package uk.co.nozzer.engine.entities.gfx;

import uk.co.nozzer.engine.gfx.Bitmap;
import uk.co.nozzer.engine.gfx.ColourUtils;
import uk.co.nozzer.engine.maths.Vector2f;
import uk.co.nozzer.engine.utils.Utils;

public class LightFade extends Light {

	protected int spread;
	
	public LightFade(Vector2f position, int radius, int colour, int intensity, int spread) {
		super(position, radius, colour, intensity);
		
		this.spread = spread;
	}

	@Override
	public void render(Bitmap screen) {
		for (int x = -radius; x <= radius; x++) {
			int xp = (int) position.getX() + x;
			for (int y = -radius; y <= radius; y++) {
				int yp = (int) position.getY() + y;
				if (x * x + y * y < radius * radius) {
					screen.setPixel(xp, yp, ColourUtils.add(screen.getPixel(xp, yp), colour,
							100 - (int) (Utils.distance(position.getX(), position.getY(), xp, yp) * spread)));
				}
			}
		}
	}

	// --- getters and setters
	public int getSpread() {
		return this.spread;
	}
	
	public void setSpread(int spread) {
		this.spread = spread;
	}
}

module tools.gui.panelmap;

import tools.gui.panel;

import engine.gfx.bitmap;
import engine.maths.vector;

class PanelMap : Panel
{
public:
	this(vec2i position, vec2i size, Color backgroundColor = 0xFF222222)
	{
		super(position, size, backgroundColor);
		scale = 10;
		test = Bitmap("res/tiles.png");
	}

	override void update(double delta)
	{
	}

	override void render()
	{
		screen.blit(test, 0, 0, scale);
	}

private:
	int scale;
	Bitmap test;
}

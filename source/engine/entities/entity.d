module engine.entities.entity;

import engine.gfx.bitmap;

interface Entity
{
	void update(double delta);
	void render(Bitmap screen);
}

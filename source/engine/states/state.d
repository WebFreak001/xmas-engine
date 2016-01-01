module engine.states.state;

import engine.gfx.bitmap;

interface State
{
	void update(float delta);
	void render(Bitmap screen);
}

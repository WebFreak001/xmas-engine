module tools.gui.panel;

import engine.gfx.bitmap;
import engine.maths.vector;

class Panel
{
public:
	this(vec2i position, vec2i size, Color backgroundColor = 0xFF222222)
	{
		_position = position;
		_size = size;
		_backgroundColor = backgroundColor;
		_screen = Bitmap(size.x, size.y);
	}

	void update(double delta)
	{
	}

	void render()
	{
		screen.fill(_backgroundColor);
	}

	@property ref auto position()
	{
		return _position;
	}

	@property ref auto size()
	{
		return _size;
	}

	@property ref auto backgroundColor()
	{
		return _backgroundColor;
	}
	
	@property ref auto screen() { return _screen; }

private:
	Bitmap _screen;
	vec2i _position, _size;
	Color _backgroundColor = 0xFF222222;
}

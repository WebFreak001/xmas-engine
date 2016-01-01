module engine.entities.entityrenderable;

import engine.gfx.bitmap;
import engine.maths.vector;
import engine.entities.entity;

class EntityRenderable : Entity
{
public:
	this(vec2i position, vec2i size, Color color = 0)
	{
		_position = position;
		_size = size;
		_color = color;
	}

	void update(double delta)
	{
	}

	void render(Bitmap screen)
	{
		screen.fillRectangle(position, size, color);
	}

	@property ref auto position()
	{
		return _position;
	}

	@property ref auto size()
	{
		return _size;
	}

	@property ref auto color()
	{
		return _color;
	}

protected:
	vec2i _position, _size;
	Color _color;
}

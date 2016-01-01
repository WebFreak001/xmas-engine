module engine.entities.gfx.light;

import engine.entities.entity;
import engine.gfx.bitmap;
import engine.maths.vector;

class Light : Entity
{
public:
	this(vec2i position, int radius = 15, Color color = 0xFFFFFF, float intensity = 0.5)
	{
		_position = position;
		_radius = radius;
		_color = color;
		_intensity = intensity;
	}

	void update(double delta)
	{
	}

	void render(Bitmap screen)
	{
		Color addColor = (_color & 0xFFFFFF) | (cast(ubyte)(0xFF * _intensity) << 24);
		for (int x = -radius; x <= radius; x++)
		{
			int xp = position.x + x;
			for (int y = -radius; y <= radius; y++)
			{
				int yp = position.y + y;
				if (x * x + y * y < radius * radius)
				{
					screen[xp, yp] += addColor;
				}
			}
		}
	}

	@property ref auto position()
	{
		return _position;
	}

	@property ref auto radius()
	{
		return _radius;
	}

	@property ref auto intensity()
	{
		return _intensity;
	}

	@property ref auto color()
	{
		return _color;
	}

protected:
	vec2i _position;
	int _radius;
	float _intensity;
	Color _color;
}

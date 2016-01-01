module engine.entities.gfx.lightfade;

import engine.gfx.bitmap;
import engine.maths.vector;
import engine.entities.gfx.light;

import std.algorithm;

class LightFade : Light
{
	this(vec2i position, int radius = 15, Color color = 0xFFFFFF, float intensity = 0.5)
	{
		super(position, radius, color, intensity);
	}

	override void render(Bitmap screen)
	{
		float invRadius = 1.0f / radius;
		for (int x = -radius; x <= radius; x++)
		{
			int xp = position.x + x;
			for (int y = -radius; y <= radius; y++)
			{
				int yp = position.y + y;
				if (x * x + y * y < radius * radius)
				{
					immutable float strength = 1 - vec2(x * invRadius, y * invRadius).lengthSquared;
					Color addColor = (_color & 0xFFFFFF) | (
						cast(ubyte)(0xFF * strength) << 24);
					screen[xp, yp] += addColor;
				}
			}
		}
	}
}

class LinearLightFade : Light
{
	this(vec2i position, int radius = 15, Color color = 0xFFFFFF, float intensity = 0.5)
	{
		super(position, radius, color, intensity);
	}

	override void render(Bitmap screen)
	{
		float invRadius = 1.0f / radius;
		for (int x = -radius; x <= radius; x++)
		{
			int xp = position.x + x;
			for (int y = -radius; y <= radius; y++)
			{
				int yp = position.y + y;
				if (x * x + y * y < radius * radius)
				{
					immutable float strength = 1 - vec2(x * invRadius, y * invRadius).length;
					Color addColor = (_color & 0xFFFFFF) | (
						cast(ubyte)(0xFF * strength) << 24);
					screen[xp, yp] += addColor;
				}
			}
		}
	}
}

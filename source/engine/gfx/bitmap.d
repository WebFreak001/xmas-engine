module engine.gfx.bitmap;

import engine.gfx.font;
import engine.gfx.spritesheet;
import engine.maths.vector;
import imageformats;

import std.math;
import std.string;
import std.algorithm;

alias Color = uint;
alias ColorBytes = ubyte[4];
alias ColorFloatBytes = float[4];

struct Bitmap
{
	immutable int width, height;
	Color[] pixels;

	this(in string path)
	{
		IFImage im = read_image(path, ColFmt.RGBA);
		width = cast(int) im.w;
		height = cast(int) im.h;
		pixels = (cast(Color*) im.pixels.ptr)[0 .. width * height];
	}

	void save(in string path) const
	{
		write_image(path, width, height,
			(cast(ubyte*) pixels.ptr)[0 .. width * height * 4], ColFmt.RGBA);
	}

	void drawString(in string str, in Font font, in int x, in int y, in Color color)
	{
		auto sheet = font.sheet;
		foreach (i, c; str)
		{
			int index = cast(int) font.map.indexOf(c);
			Bitmap character = sheet[index % sheet.spriteCountX, index / sheet.spriteCountX];
			blit(character, x + (cast(int) i * sheet.spriteWidth), y);
		}
	}

	this(in int width, in int height)
	{
		this.width = width;
		this.height = height;
		pixels = new Color[width * height];
	}

	this(in vec2 size)
	{
		this.width = width;
		this.height = height;
		pixels = new Color[width * height];
	}

	void fill(in Color color)
	{
		pixels[] = color;
	}

	void blit()(auto ref in Bitmap source, in vec2i position)
	{
		blit(source, position.x, position.y);
	}

	void blit()(auto ref in Bitmap source, in int startX, in int startY)
	{
		for (int y = 0; y < source.height; y++)
		{
			immutable int yp = startY + y;
			for (int x = 0; x < source.width; x++)
			{
				this[startX + x, yp] = source[x, y];
			}
		}
	}

	void blit()(auto ref in Bitmap source, in int startX, in int startY, int scale)
	in
	{
		assert(scale > 0);
	}
	body
	{
		for (int y = 0; y < source.height * scale; y++)
		{
			immutable int yp = startY + y;
			for (int x = 0; x < source.width * scale; x++)
			{
				this[startX + x, yp] = source[x / scale, y / scale];
			}
		}
	}

	void fillRectangle(in vec2i start, in vec2i size, in Color color)
	{
		fillRectangle(start.x, start.y, size.x, size.y, color);
	}

	void fillRectangle(in int startX, in int startY, in int width, in int height, in Color color)
	{
		for (int y = 0; y < height; y++)
		{
			immutable int yp = startY + y;
			for (int x = 0; x < width; x++)
			{
				this[x + startX, yp] = color;
			}
		}
	}

	void fillCircle(in vec2i start, in int radius, in Color color)
	{
		fillCircle(start.x, start.y, radius, color);
	}

	void fillCircle(in int startX, in int startY, in int radius, in Color color)
	{
		for (int y = -radius; y <= radius; y++)
		{
			immutable int yp = startY + y;
			for (int x = -radius; x <= radius; x++)
			{
				if (x * x + y * y < radius * radius)
					this[x + startX, yp] = color;
			}
		}
	}

	void fillEllipse(in vec2i start, in vec2i radius, in Color color)
	{
		fillEllipse(start.x, start.y, radius.x, radius.y, color);
	}

	void fillEllipse(in int startX, in int startY, in int radiusWidth,
		in int radiusHeight, in Color color)
	{
		for (int y = -radiusHeight; y <= radiusHeight; y++)
		{
			immutable int yp = startY + y;
			for (int x = -radiusWidth; x <= radiusWidth; x++)
			{
				if (x * x + y * y < radiusWidth * radiusHeight)
					this[x + startX, yp] = color;
			}
		}
	}

	void vignette()
	{
		int radiusWidth = width;
		int radiusHeight = height;

		for (int x = -radiusWidth; x <= radiusWidth; x++)
		{
			for (int y = -radiusHeight; y <= radiusHeight; y++)
			{
				if (x * x + y * y < radiusWidth * radiusHeight)
				{
					int xp = width / 2 + x;
					int yp = height / 2 + y;
					this[xp, yp] += [0, 0, 0,
						cast(ubyte)(vec2(x / cast(float) radiusWidth,
						y / cast(float) radiusHeight).lengthSquared * 0xFF)].hex;
				}
			}
		}
	}

	void drawString(in string str, in Font font, in vec2i pos, in Color color)
	{
		drawString(str, font, pos.x, pos.y, color);
	}

	void drawLine(in vec2i from, in vec2i to, in Color color)
	{
		drawLine(from.x, from.y, to.x, to.y, color);
	}

	void drawLine(int x, int y, in int x2, in int y2, in Color color)
	{
		int w = x2 - x;
		int h = y2 - y;
		int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0;
		if (w < 0)
			dx1 = -1;
		else if (w > 0)
			dx1 = 1;
		if (h < 0)
			dy1 = -1;
		else if (h > 0)
			dy1 = 1;
		if (w < 0)
			dx2 = -1;
		else if (w > 0)
			dx2 = 1;
		int longest = abs(w);
		int shortest = abs(h);
		if (longest <= shortest)
		{
			longest = abs(h);
			shortest = abs(w);
			if (h < 0)
				dy2 = -1;
			else if (h > 0)
				dy2 = 1;
			dx2 = 0;
		}
		int numerator = longest >> 1;
		for (int i = 0; i <= longest; i++)
		{
			this[x, y] = color;
			numerator += shortest;
			if (!(numerator < longest))
			{
				numerator -= longest;
				x += dx1;
				y += dy1;
			}
			else
			{
				x += dx2;
				y += dy2;
			}
		}
	}

	Color opIndex(in int x, in int y) const
	{
		if (x < 0 || y < 0 || x >= width || y >= height)
			return cast(Color)-1;
		return pixels[x + y * width];
	}

	void opIndexAssign(in Color color, in int x, in int y)
	{
		if (x < 0 || y < 0 || x >= width || y >= height)
			return;
		if ((color & 0xFF000000) == 0)
			return;
		else
			pixels[x + y * width] = color;
	}

	Color opIndexOpAssign(string op)(in Color color, in int x, in int y)
	{
		static if (op == "+")
		{
			if (x < 0 || y < 0 || x >= width || y >= height)
				return cast(Color)-1;
			ColorFloatBytes a = color.rgba.f;
			ColorFloatBytes b = pixels[x + y * width].rgba.f;
			ColorFloatBytes mix;

			enum A = 0;
			enum R = 1;
			enum G = 2;
			enum B = 3;

			if (a[A] == 0)
				mix = b;
			else if (b[A] == 0)
				mix = a;
			else if (a[A] == 1)
				mix = a;
			else if (b[A] == 1)
			{
				mix[0] = 1;
				mix[1] = a[R] * a[A] + b[R] * (1 - a[A]);
				mix[2] = a[G] * a[A] + b[G] * (1 - a[A]);
				mix[3] = a[B] * a[A] + b[B] * (1 - a[A]);
			}
			else
			{
				mix[0] = a[A] + b[A] * (1 - a[A]);
				immutable float invAlpha = 1 / mix[0];
				mix[1] = (a[R] * a[A] + b[R] * (1 - a[A])) * invAlpha;
				mix[2] = (a[G] * a[A] + b[G] * (1 - a[A])) * invAlpha;
				mix[3] = (a[B] * a[A] + b[B] * (1 - a[A])) * invAlpha;
			}
			return pixels[x + y * width] = mix.ub.hex;
		}
		else
			static assert("Can only add colors");
	}
}

private:

inout(ColorBytes) rgba(inout(Color) value)
{
	return [(value >> 24) & 0xFF, (value >> 16) & 0xFF, (value >> 8) & 0xFF, (value >> 0) & 0xFF];
}

inout(Color) hex(inout(ColorBytes) color)
{
	return (color[0] << 24) | (color[1] << 16) | (color[2] << 8) | color[3];
}

enum iColorConst = 1.0 / 255;

inout(ColorFloatBytes) f(inout(ColorBytes) arr)
{
	return [arr[0] * iColorConst, arr[1] * iColorConst, arr[2] * iColorConst, arr[3] * iColorConst];
}

inout(ColorBytes) ub(inout(ColorFloatBytes) arr)
{
	return [cast(ubyte)(arr[0] * 255), cast(ubyte)(arr[1] * 255),
		cast(ubyte)(arr[2] * 255), cast(ubyte)(arr[3] * 255)];
}

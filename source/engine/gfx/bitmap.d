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
	int width, height;
	Color[] pixels;

	this(int width, int height)
	{
		this.width = width;
		this.height = height;
		pixels = new Color[width * height];
	}

	this(vec2 size)
	{
		this.width = width;
		this.height = height;
		pixels = new Color[width * height];
	}

	this(string path)
	{
		IFImage im = read_image(path, ColFmt.RGBA);
		width = cast(int) im.w;
		height = cast(int) im.h;
		pixels = (cast(Color*) im.pixels.ptr)[0 .. width * height];
	}

	void fill(Color color)
	{
		pixels[] = color;
	}

	void blit(Bitmap source, vec2i position)
	{
		blit(source, position.x, position.y);
	}

	void blit(Bitmap source, int startX, int startY)
	{
		for (int x = 0; x < source.width; x++)
			for (int y = 0; y < source.height; y++)
				this[startX + x, startY + y] += source[x, y];
	}

	void blit(Bitmap source, int startX, int startY, int scale)
	{
		if (scale <= 0)
			scale = 1;
		for (int x = 0; x < source.width * scale; x++)
			for (int y = 0; y < source.height * scale; y++)
				this[startX + x, startY + y] += source[x / scale, y / scale];
	}

	void fillRectangle(vec2i start, vec2i size, Color color)
	{
		fillRectangle(start.x, start.y, size.x, size.y, color);
	}

	void fillRectangle(int startX, int startY, int width, int height, Color color)
	{
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				this[x + startX, y + startY] += color;
	}

	void fillCircle(vec2i start, int radius, Color color)
	{
		fillCircle(start.x, start.y, radius, color);
	}

	void fillCircle(int startX, int startY, int radius, Color color)
	{
		for (int x = -radius; x <= radius; x++)
			for (int y = -radius; y <= radius; y++)
				if (x * x + y * y < radius * radius)
					this[x + startX, y + startY] += color;
	}

	void fillEllipse(vec2i start, vec2i radius, Color color)
	{
		fillEllipse(start.x, start.y, radius.x, radius.y, color);
	}

	void fillEllipse(int startX, int startY, int radiusWidth, int radiusHeight, Color color)
	{
		for (int x = -radiusWidth; x <= radiusWidth; x++)
			for (int y = -radiusHeight; y <= radiusHeight; y++)
				if (x * x + y * y < radiusWidth * radiusHeight)
					this[x + startX, y + startY] += color;
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

	void drawString(string str, Font font, vec2i pos, Color color)
	{
		drawString(str, font, pos.x, pos.y, color);
	}

	void drawString(string str, Font font, int x, int y, Color color)
	{
		Spritesheet sheet = font.sheet;
		foreach (i, c; str)
		{
			int index = cast(int) font.map.indexOf(c);
			Bitmap character = sheet[index % sheet.spriteCountX, index / sheet.spriteCountX];
			blit(character, x + (cast(int) i * sheet.spriteWidth), y);
		}
	}

	void drawLine(vec2i from, vec2i to, Color color)
	{
		drawLine(from.x, from.y, to.x, to.y, color);
	}

	void drawLine(int x, int y, int x2, int y2, Color color)
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
			this[x, y] += color;
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

	void save(string path)
	{
		write_image(path, width, height,
			(cast(ubyte*) pixels.ptr)[0 .. width * height * 4], ColFmt.RGBA);
	}

	Color opIndex(int x, int y)
	{
		if (x < 0 || y < 0 || x >= width || y >= height)
			return cast(Color)-1;
		return pixels[x + y * width];
	}

	Color opIndexAssign(Color color, int x, int y)
	{
		if (x < 0 || y < 0 || x >= width || y >= height)
			return cast(Color)-1;
		return pixels[x + y * width] = color;
	}

	Color opIndexOpAssign(string op)(Color color, int x, int y)
	{
		static if (op == "+")
		{
			if (x < 0 || y < 0 || x >= width || y >= height)
				return cast(Color)-1;
			ColorFloatBytes a = color.rgba.f;
			ColorFloatBytes b = pixels[x + y * width].rgba.f;
			ColorFloatBytes mix;

			enum A = 0;
			enum R = 3;
			enum G = 2;
			enum B = 1;

			mix[0] = a[A] + b[A] * (1 - a[A]);
			immutable float invAlpha = 1 / mix[0];
			mix[1] = (a[R] * a[A] + b[R] * (1 - a[A])) * invAlpha;
			mix[2] = (a[G] * a[A] + b[G] * (1 - a[A])) * invAlpha;
			mix[3] = (a[B] * a[A] + b[B] * (1 - a[A])) * invAlpha;
			return pixels[x + y * width] = mix.ub.hex;
		}
		else
			static assert("Can only add colors");
	}
}

private:

ColorBytes rgba(Color value)
{
	return [(value >> 24) & 0xFF, (value >> 16) & 0xFF, (value >> 8) & 0xFF, (value >> 0) & 0xFF];
}

Color hex(ColorBytes color)
{
	return (color[0] << 24) | (color[1] << 16) | (color[2] << 8) | color[3];
}

enum iColorConst = 1.0 / 255;

ColorFloatBytes f(ColorBytes arr)
{
	return [arr[0] * iColorConst, arr[1] * iColorConst, arr[2] * iColorConst, arr[3] * iColorConst];
}

ColorBytes ub(ColorFloatBytes arr)
{
	return [cast(ubyte)(arr[0] * 255), cast(ubyte)(arr[1] * 255),
		cast(ubyte)(arr[2] * 255), cast(ubyte)(arr[3] * 255)];
}

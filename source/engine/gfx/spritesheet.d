module engine.gfx.spritesheet;

import engine.gfx.bitmap;

class Spritesheet
{
public:
	this(Bitmap sheet, int spriteSize)
	{
		this(sheet, spriteSize, spriteSize);
	}

	this(Bitmap sheet, int spriteWidth, int spriteHeight)
	{
		_sheet = sheet;
		_spriteWidth = spriteWidth;
		_spriteHeight = spriteHeight;

		_spriteCountX = sheet.width / spriteWidth;
		_spriteCountY = sheet.height / spriteHeight;
	}

	Bitmap opIndex(int xPos, int yPos)
	{
		assert(xPos >= 0 && yPos >= 0 && xPos < _spriteCountX && yPos < _spriteCountY);
		Bitmap sprite = Bitmap(_spriteWidth, _spriteHeight);
		for (int x = 0; x < _spriteWidth; x++)
		{
			int xp = x + (xPos * _spriteWidth);
			for (int y = 0; y < _spriteHeight; y++)
			{
				int yp = y + (yPos * _spriteHeight);
				sprite[x, y] = sheet[xp, yp];
			}
		}
		return sprite;
	}
	
	auto sheet() @property { return _sheet; }
	auto spriteWidth() @property { return _spriteWidth; }
	auto spriteHeight() @property { return _spriteHeight; }
	auto spriteCountX() @property { return _spriteCountX; }
	auto spriteCountY() @property { return _spriteCountY; }

private:
	Bitmap _sheet;
	int _spriteWidth, _spriteHeight;
	int _spriteCountX, _spriteCountY;
}

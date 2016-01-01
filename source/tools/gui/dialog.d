module tools.gui.dialog;

import engine.gfx.bitmap;
import engine.gfx.font;
import engine.gfx.spritesheet;
import engine.game;
import engine.maths.vector;
import tools.gui.component;

import std.string;

class Dialog : Component
{
public:
	this(Game game, string title, string message, int width = 200, int height = 150)
	{
		_font = Font(new Spritesheet(Bitmap("res/font.png"), 6, 9));

		_position = vec2i(
			(game.width - width) / 2,
			(game.height - height) / 2);
		_size = vec2i(width, height);

		_title = title;
		_message = message;
		_words = _message.split(' ');

		_screen = Bitmap(_size.x, _size.y);
	}

	override void update(double delta)
	{
	}

	override void render()
	{
		_screen.fill(0xFF444444);
		_screen.fillRectangle(0, 0, _size.x, 20, 0xFF4F4F4F);
		_screen.drawString(_title, _font, 4, 4, 0xFFFFFFFF);

		int line = 0;
		int x = leading - 1;
		foreach (word; _words)
		{
			if (x * font.sheet.spriteWidth + wordWidth(word) > _size.x - 8)
			{
				x = hanging;
				line++;
			}
			else
				x++;
			renderWord(word, x, line, 0xFFFFFFFF);
		}
	}

	int wordWidth(string word)
	{
		return (cast(int) word.length) * font.sheet.spriteWidth;
	}

	void renderWord(string word, ref int x, ref int y, int color)
	{
		foreach (c; word)
		{
			if (c == '\n')
			{
				x = 0;
				y++;
			}
			else
			{
				_screen.drawString([c], _font, x * font.sheet.spriteWidth,
					20 + y * font.sheet.spriteHeight, color);
				x++;
			}
		}
	}

	@property ref auto title()
	{
		return _title;
	}

	@property ref auto message()
	{
		return _message;
	}

	@property ref auto position()
	{
		return _position;
	}

	@property ref auto size()
	{
		return _size;
	}

	@property ref auto screen()
	{
		return _screen;
	}

	@property ref auto font()
	{
		return _font;
	}

private:
	int hanging = 3;
	int leading = 1;
	string _title;
	string _message;
	string[] _words;
	vec2i _position, _size;
	Bitmap _screen;
	Font _font;
}

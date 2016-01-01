module engine.gfx.font;

import engine.gfx.spritesheet;

struct Font
{
	Spritesheet sheet;
	string map = `ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz[]/\{}|~<>=_*,.!?1234567890+-"':;#@ `;
}

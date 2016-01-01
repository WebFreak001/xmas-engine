module engine.input.mouse;

import engine.maths.vector;

struct MouseState
{
	vec2i position;
	bool[8] buttons;
}

static MouseState state;
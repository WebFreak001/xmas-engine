module engine.input.keyboard;

import engine.maths.vector;

struct KeyboardState
{
	bool[uint] keys;

	bool opIndex(size_t i)
	{
		if (((cast(uint) i) in keys) is null)
			return false;
		return keys[cast(uint) i];
	}

	bool opIndexAssign(bool value, size_t i)
	{
		return keys[cast(uint) i] = value;
	}
}

static KeyboardState state;

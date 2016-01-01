module utils;

import std.math;
import std.traits;

T distance(T)(T x1, T y1, T x2, T y2) if (isNumeric!T)
{
	return cast(T) sqrt((y2 - y1) ^^ 2 + (x2 - x1) ^^ 2);
}

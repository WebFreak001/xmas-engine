module engine.maths.vector;

import std.conv;
import std.math;

alias vec2 = Vector!(2, float);
alias vec3 = Vector!(3, float);
alias vec2i = Vector!(2, int);
alias vec3i = Vector!(3, int);

private template ElementWise(string s, size_t i = 0)
{
	static if (s.length > 0)
		enum ElementWise = "arr[" ~ i.to!string ~ "] = this.opDispatch!(['" ~ s[0] ~ "']);\n" ~ ElementWise!(
				s[1 .. $], i + 1);
	else
		enum ElementWise = "";
}

private template TupleConstructor(size_t datalen, size_t cur = 0)
{
	static if(cur < datalen)
		enum TupleConstructor = "data[" ~ cur.to!string ~ "], " ~ TupleConstructor!(datalen, cur + 1);
	else
		enum TupleConstructor = "";
}

struct Vector(int n, T)
{
	alias Type = T;
	alias Size = n;

	static assert(n > 1, "A vector needs at least 2 elements");
	T[n] data;

	this(T[n] values...)
	{
		data = values[];
	}

	typeof(this) opBinary(string op, S)(S rhs)
	{
		T[n] arr;
		static if (op == "+")
		{
			arr = data[] + rhs.data[];
			return Vector!(n, T)(arr);
		}
		else static if (op == "-")
		{
			arr = data[] - rhs.data[];
			return Vector!(n, T)(arr);
		}
		else static if (op == "*")
		{
			arr = data[] * rhs;
			return Vector!(n, T)(arr);
		}
	}

	auto opDispatch(string s)()
	{
		static assert(s.length > 0, "Invalid opDispatch call with empty string!");
		static if (s.length == 1)
		{
			static if (s == "x" || s == "r")
				return data[0];
			else static if (s == "y" || s == "g")
				return data[1];
			else static if (s == "z" || s == "b")
				return data[2];
			else static if (s == "w" || s == "a")
				return data[3];
			else
				static assert(0, "No such component for " ~ typeof(this).stringof ~ ": " ~ s);
		}
		else
		{
			T[s.length] arr;
			mixin(ElementWise!s);
			return Vector!(s.length, T)(arr);
		}
	}

	T opDispatch(string s)(T value)
	{
		static if (s == "x" || s == "r")
			return data[0] = value;
		else static if (s == "y" || s == "g")
			return data[1] = value;
		else static if (s == "z" || s == "b")
			return data[2] = value;
		else static if (s == "w" || s == "a")
			return data[3] = value;
		else
			static assert(0, "No such component for " ~ typeof(this).stringof ~ ": " ~ s);
	}

	T opIndex(size_t i)
	{
		return data[i];
	}

	T opIndexAssign(T value, size_t i)
	{
		return data[i] = value;
	}

	T opIndexOpAssign(string op)(T value, size_t i)
	{
		mixin("return data[i] " ~ op ~ "= value;");
	}
	
	T lengthSquared() @property
	{
		T res;
		foreach(f; data)
			res += f * f;
		return res;
	}
	
	T length() @property
	{
		static if(!__traits(compiles, { return sqrt(lengthSquared); }))
			return cast(T) sqrt(cast(float) lengthSquared);
		else
			return sqrt(lengthSquared);
	}

	string toString()
	{
		return data.to!string;
	}

	bool opEquals(T)(T b)
	{
		static assert(is(T == typeof(this)),
			"cannot implicitly convert " ~ typeof(this).stringof ~ " to " ~ T.stringof);
		return data == b.data;
	}

	T opCast(T)()
	{
		static assert(T.Size == Size, "Cannot cast from a " ~ Size.to!string ~ "-Element Vector to a " ~ T.Size.to!string ~ "-Element Vector");
		T.Type[T.Size] arr;
		foreach(i, val; data)
			arr[i] = cast(T.Type) val;
		return T(arr);
	}
	
	/// Returns a tuple with named fields x, y, z and w
	auto tuple() @property
	{
		import std.typecons;
		static if(Size == 1)
			return mixin("tuple!(\"x\")(" ~ TupleConstructor!(data.length) ~ ")");
		else static if(Size == 2)
			return mixin("tuple!(\"x\", \"y\")(" ~ TupleConstructor!(data.length) ~ ")");
		else static if(Size == 3)
			return mixin("tuple!(\"x\", \"y\", \"z\")(" ~ TupleConstructor!(data.length) ~ ")");
		else
			return mixin("tuple!(\"x\", \"y\", \"z\", \"w\")(" ~ TupleConstructor!(data.length) ~ ")");
	}
	
	/// Returns a tuple with named fields r, g, b and a
	auto colorTuple() @property
	{
		import std.typecons;
		static if(Size == 1)
			return mixin("tuple!(\"r\")(" ~ TupleConstructor!(data.length) ~ ")");
		else static if(Size == 2)
			return mixin("tuple!(\"r\", \"g\")(" ~ TupleConstructor!(data.length) ~ ")");
		else static if(Size == 3)
			return mixin("tuple!(\"r\", \"g\", \"b\")(" ~ TupleConstructor!(data.length) ~ ")");
		else
			return mixin("tuple!(\"r\", \"g\", \"b\", \"a\")(" ~ TupleConstructor!(data.length) ~ ")");
	}
}

unittest
{
	vec2 a = vec2(0, 5);
	vec2 b = vec2(4, 2);
	assert(a + b == vec2(4, 7));
	assert(a - b == vec2(-4, 3));
	assert(a * 5 == vec2(0, 25));
	assert(a + b != vec2(21, 71));
	assert(((a + b) * 5).x == 20);
	assert(cast(vec2i) a == vec2i(0, 5));
	vec3 c = vec3(4, 4, 2);
	assert(b == c.xz);
	assert(b.xxy == c);
	
	import std.typecons;
	void checkTuple(Tuple!(int, "x", int, "y") t, int x2, int y2)
	{
		assert(t.x == x2);
		assert(t.y == y2);
	}
	checkTuple((cast(vec2i) a).tuple, 0, 5);
}

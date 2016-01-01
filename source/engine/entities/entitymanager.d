module engine.entities.entitymanager;

import engine.entities.entity;
import engine.gfx.bitmap;
import std.algorithm;

class EntityManager
{
public:
	this()
	{
	}

	this(int maxEntities)
	{
		_maxEntities = maxEntities;
		_entities.reserve(maxEntities);
	}

	void update(double delta)
	{
		foreach (entity; _entities)
			entity.update(delta);
	}

	void render(Bitmap screen)
	{
		foreach (entity; _entities)
			entity.render(screen);
	}

	Entity addEntity(Entity e)
	{
		if (_entities.length < _maxEntities)
			_entities ~= e;
		return e;
	}

	void removeEntity(int index)
	{
		_entities = _entities.remove(index);
	}

	Entity opIndex(size_t index)
	{
		return _entities[index];
	}

	ref auto maxEntities() @property
	{
		return _maxEntities;
	}

	auto entities() @property
	{
		return _entities;
	}

protected:
	Entity[] _entities;
	int _maxEntities = 500;
}

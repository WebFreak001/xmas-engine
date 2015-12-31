package com.deviotion.terraria.entities;

import java.util.Random;

import uk.co.nozzer.engine.entities.Entity;
import uk.co.nozzer.engine.entities.EntityManager;
import uk.co.nozzer.engine.entities.EntityRenderable;

public class EntityManagerTest extends EntityManager {

	public EntityManagerTest(int maxEntities) {
		super(maxEntities);
	}
	
	private Random random = new Random();
	
	@Override
	public void update(double delta) {
		for (Entity e : entities) {
			if (e instanceof EntityRenderable) {
				if (random.nextInt(120) == 1)
					((EntityRenderable) e).setColour(random.nextInt(0xFFFFFF));
			}
			e.update(delta);
		}
	}
}

package com.deviotion.terraria.states;

import java.util.Random;

import com.deviotion.terraria.entities.EntityManagerTest;

import uk.co.nozzer.engine.entities.EntityManager;
import uk.co.nozzer.engine.entities.EntityRenderable;
import uk.co.nozzer.engine.gfx.Bitmap;
import uk.co.nozzer.engine.maths.Dimension2f;
import uk.co.nozzer.engine.maths.Vector2f;
import uk.co.nozzer.engine.states.State;

public class StateTest extends State {

	private EntityManager testThang = new EntityManagerTest(1000000);
	private Random random = new Random();

	public StateTest() {
		for (int i = 0; i < testThang.getMaxEntities(); i++) {
			testThang.addEntity(new EntityRenderable(
					new Vector2f(random.nextInt(1280 / 2), random.nextInt(720 / 2)),
					new Dimension2f(random.nextInt(3), random.nextInt(3))));
		}
	
	}

	@Override
	public void update(double delta) {
		testThang.update(delta);
	}

	@Override
	public void render(Bitmap screen) {
		screen.fill(0);
		testThang.render(screen);
	}

}

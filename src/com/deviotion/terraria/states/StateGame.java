package com.deviotion.terraria.states;

import java.util.Random;

import com.deviotion.terraria.Main;
import com.deviotion.terraria.entities.EntitySnow;
import com.deviotion.terraria.level.Level;

import uk.co.nozzer.entities.EntityManager;
import uk.co.nozzer.gfx.Bitmap;
import uk.co.nozzer.input.Mouse;
import uk.co.nozzer.maths.Dimension2f;
import uk.co.nozzer.maths.Vector2f;
import uk.co.nozzer.states.State;

public class StateGame extends State {
	
	private Level level;

	private EntityManager entityManager;
	private Random random;	
	
	public StateGame() {
		this.level = new Level(new Dimension2f(Main.WIDTH / Level.TILE_SIZE,
				Main.HEIGHT / Level.TILE_SIZE));

		this.random = new Random();
		this.entityManager = new EntityManager();
		for (int i = 0; i < this.entityManager.getMaxEntities(); i++) {
			this.entityManager.addEntity(new EntitySnow(
					new Vector2f(random.nextInt(Main.WIDTH), random.nextInt(Main.HEIGHT)),
					new Dimension2f(1, 1), new Dimension2f(Main.WIDTH, Main.HEIGHT)));
		}
	}
	
	@Override
	public void update(double delta) {
		level.update(delta);
		entityManager.update(delta);
	}

	@Override
	public void render(Bitmap screen) {
		level.render(screen);
		entityManager.render(screen);
	}

}

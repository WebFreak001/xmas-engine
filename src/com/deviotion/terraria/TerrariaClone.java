package com.deviotion.terraria;

import java.util.Random;

import com.deviotion.terraria.entities.EntitySnow;
import com.deviotion.terraria.level.Level;

import uk.co.nozzer.Game;
import uk.co.nozzer.entities.EntityManager;
import uk.co.nozzer.entities.gfx.LightFade;
import uk.co.nozzer.gfx.Bitmap;
import uk.co.nozzer.gfx.Window;
import uk.co.nozzer.maths.Dimension2f;
import uk.co.nozzer.maths.Vector2f;

public class TerrariaClone extends Game {

	private Level level;

	private EntityManager entityManager;
	private Random random;
	
	public TerrariaClone(Window window) {
		super(window);

		this.level = new Level(new Dimension2f(window.getInitialWidth() / Level.TILE_SIZE,
				window.getInitialHeight() / Level.TILE_SIZE));

		this.random = new Random();
		this.entityManager = new EntityManager();
		for (int i = 0; i < this.entityManager.getMaxEntities(); i++) {
			this.entityManager.addEntity(new EntitySnow(
					new Vector2f(random.nextInt(window.getInitialWidth()), random.nextInt(window.getInitialHeight())),
					new Dimension2f(1, 1), new Dimension2f(window.getInitialWidth(), window.getInitialHeight())));
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

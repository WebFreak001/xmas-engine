package com.deviotion.terraria.states;

import java.util.Random;

import com.deviotion.terraria.Main;
import com.deviotion.terraria.entities.EntityPlayer;
import com.deviotion.terraria.level.Level;

import uk.co.nozzer.engine.entities.EntityManager;
import uk.co.nozzer.engine.gfx.Bitmap;
import uk.co.nozzer.engine.maths.Dimension2f;
import uk.co.nozzer.engine.maths.Vector2f;
import uk.co.nozzer.engine.states.State;

public class StateGame extends State {
	
	private Level level;

	private EntityManager levelEntities;
	
	private EntityPlayer player;
	
	private Random random;
	
	public StateGame() {
		this.level = new Level(new Dimension2f(Main.WIDTH / Level.TILE_SIZE,
				Main.HEIGHT / Level.TILE_SIZE));

		this.random = new Random();
		
		this.levelEntities = new EntityManager();
		this.player = new EntityPlayer(new Vector2f(10, 100), new Dimension2f(8, 16), level);
		this.levelEntities.addEntity(this.player);
	}
	
	@Override
	public void update(double delta) {
		level.update(delta);
		levelEntities.update(delta);
		
	}

	@Override
	public void render(Bitmap screen) {
		level.render(screen);
		levelEntities.render(screen);
		
		screen.vignette();
	}

}

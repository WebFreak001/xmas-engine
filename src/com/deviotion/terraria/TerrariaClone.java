package com.deviotion.terraria;

import com.deviotion.terraria.states.StateGame;
import com.deviotion.terraria.states.StateGenerationThing;

import uk.co.nozzer.engine.Game;
import uk.co.nozzer.engine.gfx.Bitmap;
import uk.co.nozzer.engine.gfx.Window;
import uk.co.nozzer.engine.states.State;
import uk.co.nozzer.engine.states.StateManager;

public class TerrariaClone extends Game {

	private StateManager stateManager;
	
	private State gameState = new StateGame();
	private State genState = new StateGenerationThing();
	
	public TerrariaClone(Window window) {
		super(window);
		
		this.stateManager = new StateManager();
		this.stateManager.addState(gameState); // 0
		this.stateManager.addState(genState); // 1
		
		this.stateManager.setCurrentState(0);
	}

	@Override
	public void update(double delta) {
		this.stateManager.update(delta);
	}

	@Override
	public void render(Bitmap screen) {
		this.stateManager.render(screen);
	}

}

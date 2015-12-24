package com.deviotion.terraria;

import com.deviotion.terraria.states.StateGame;
import com.deviotion.terraria.states.StateTest;

import uk.co.nozzer.Game;
import uk.co.nozzer.gfx.Bitmap;
import uk.co.nozzer.gfx.Window;
import uk.co.nozzer.states.State;
import uk.co.nozzer.states.StateManager;

public class TerrariaClone extends Game {

	private StateManager stateManager;
	
	private State gameState = new StateGame();
	
	public TerrariaClone(Window window) {
		super(window);
		
		this.stateManager = new StateManager();
		this.stateManager.addState(gameState);
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

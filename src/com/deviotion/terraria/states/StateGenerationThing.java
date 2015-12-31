package com.deviotion.terraria.states;

import uk.co.nozzer.engine.gfx.Bitmap;
import uk.co.nozzer.engine.states.State;

public class StateGenerationThing extends State {

	public StateGenerationThing() {
		
	}
	
	@Override
	public void update(double delta) {
		
	}

	@Override
	public void render(Bitmap screen) {
		for (int i = 0; i < screen.getWidth() * screen.getHeight(); i++) {
			screen.setPixel(i % screen.getWidth(), i / screen.getHeight(), i * i / 2);
		}
	}

}

package com.deviotion.terraria.states;

import uk.co.nozzer.gfx.Bitmap;
import uk.co.nozzer.states.State;

public class StateTest extends State {

	@Override
	public void update(double delta) {
		
	}

	@Override
	public void render(Bitmap screen) {
		screen.fillRectangle(10, 10, 100,100, 0xFF11FF);
	}

}

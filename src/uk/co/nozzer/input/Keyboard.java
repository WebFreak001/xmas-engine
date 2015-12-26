package uk.co.nozzer.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private static boolean[] keys = new boolean[500];

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	// --- getters and setters
	public static boolean isPressed(int keycode) {
		if (keycode >= 0 && keycode < 500) return keys[keycode];
		else return false;
	}
}

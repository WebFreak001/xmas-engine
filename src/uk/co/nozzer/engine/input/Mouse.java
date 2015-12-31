package uk.co.nozzer.engine.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import uk.co.nozzer.engine.maths.Vector2f;

public class Mouse implements MouseListener, MouseMotionListener {
	
	private static Vector2f position = new Vector2f(-1, -1);
	private static int button = -1;
	private static boolean down = false;
	
	private static int scale;
	
	public Mouse(int scale) {
		this.scale = scale;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		position.set(e.getX() / scale, e.getY() / scale);
		button = e.getButton();
		down = true;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		position.set(e.getX() / scale, e.getY() / scale);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		button = e.getButton();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		position.set(e.getX() / scale, e.getY() / scale);
		button = e.getButton();
		down = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		button = -1;
		down = false;
	}

	// --- getters and setters
	public static Vector2f getPosition() {
		return position;
	}
	public static boolean isDown() {
		return down;
	}
	public static int getButton() {
		return button;
	}
}

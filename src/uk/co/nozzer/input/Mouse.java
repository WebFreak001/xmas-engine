package uk.co.nozzer.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import uk.co.nozzer.maths.Vector2f;

public class Mouse implements MouseListener, MouseMotionListener {
	
	private Vector2f position = new Vector2f(-1, -1);
	private int button = -1;
	private boolean down = false;
	
	private int scale;
	
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
	public Vector2f getPosition() {
		return this.position;
	}
	public boolean isDown() {
		return this.down;
	}
	public int getButton() {
		return this.button;
	}
}

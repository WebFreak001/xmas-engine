package uk.co.nozzer.gfx;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import uk.co.nozzer.input.Keyboard;
import uk.co.nozzer.input.Mouse;
import uk.co.nozzer.maths.Dimension2f;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private int width;
	private int height;
	private int scale;
	private String title;
	
	private Canvas canvas;
	
	private Keyboard keyboard;
	private Mouse mouse;
	
	public Window(int width, int height, int scale, String title) {
		this.width = width;
		this.height = height;
		this.scale = scale;
		this.title = title;
		
		this.init();
	}
	
	public Window(Dimension2f size, int scale, String title) {
		this.width = (int) size.getWidth();
		this.height = (int) size.getHeight();
		this.scale = scale;
		this.title = title;
		
		this.init();
	}
	
	private void init() {
		setTitle(title);
		setResizable(false);
		setSize(new Dimension(width * scale, height * scale));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		setVisible(true);
		requestFocus();
		
		canvas = new Canvas();
		canvas.setSize(new Dimension(width * scale, height * scale));
		add(canvas);
		canvas.createBufferStrategy(3);
		
		keyboard = new Keyboard();
		mouse = new Mouse(scale);
		
		canvas.addKeyListener(keyboard);
		canvas.addMouseListener(mouse);
		canvas.addMouseMotionListener(mouse);
	}
	
	public int getInitialWidth() {
		return this.width;
	}
	public int getInitialHeight() {
		return this.height;
	}
	public String getTitle() {
		return this.title;
	}
	public int getScale() {
		return this.scale;
	}
	public Canvas getCanvas() {
		return this.canvas;
	}
	public Keyboard getKeyboard() {
		return this.keyboard;
	}
	public Mouse getMouse() {
		return this.mouse;
	}
}

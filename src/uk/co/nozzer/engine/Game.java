package uk.co.nozzer.engine;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import uk.co.nozzer.engine.gfx.Bitmap;
import uk.co.nozzer.engine.gfx.Window;

public abstract class Game {

	protected Window window;

	protected Bitmap screen;

	private long startTimer = 0L;

	public Game(Window window) {
		this.window = window;

		this.screen = new Bitmap(window.getInitialWidth(), window.getInitialHeight());
	}

	public void start() {
		long lastTime = System.nanoTime();
		double delta = 0;
		double ns = 1000000000.0 / 60.0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		int updates = 0;
		long lastUpdate = System.currentTimeMillis();
		System.out.println("Took " + ((System.currentTimeMillis() - startTimer) / 1000.0) + " seconds to load!");
		while (true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				privateUpdate(delta);
				updates++;
				delta--;
			}
			try {
				long timeToSleep = (long) (System.currentTimeMillis() - (lastUpdate + 1000.0 / 60.0)); 
				Thread.sleep(Math.abs(timeToSleep));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		    lastUpdate = System.currentTimeMillis();
			privateRender();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + "ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
	}

	private void privateUpdate(double delta) {
		this.update(delta);
	}

	private void privateRender() {
		this.render(screen);

		BufferStrategy bufferStrat = this.window.getCanvas().getBufferStrategy();
		Graphics graphics = bufferStrat.getDrawGraphics();
		
		graphics.drawImage(screen.getImage(), 0, 0, window.getWidth(), window.getHeight(), null);

		graphics.dispose();
		bufferStrat.show();
	}

	public abstract void update(double delta);

	public abstract void render(Bitmap screen);
}

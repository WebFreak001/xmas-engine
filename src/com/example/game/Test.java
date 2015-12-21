package com.example.game;

import java.awt.event.KeyEvent;

import uk.co.nozzer.Game;
import uk.co.nozzer.audio.Audio;
import uk.co.nozzer.entities.EntityManager;
import uk.co.nozzer.entities.gfx.LightFade;
import uk.co.nozzer.gfx.Bitmap;
import uk.co.nozzer.gfx.Font;
import uk.co.nozzer.gfx.Spritesheet;
import uk.co.nozzer.gfx.Window;
import uk.co.nozzer.maths.Dimension2f;
import uk.co.nozzer.maths.Vector2f;

public class Test extends Game {

	private EntityManager entities = new EntityManager(100);
	
	private Spritesheet fontsheet;
	private Font font;

	private Audio audio;
	
	public Test(Window window) {
		super(window);

		entities.addEntity(new LightFade(new Vector2f(50, 50), 15, 0xFFC400, 100, 5));
		
		fontsheet = new Spritesheet(new Bitmap("/font.png"), 6, 9);
		font = new Font(fontsheet);
	}

	int x = 0, y = 0;

	@Override
	public void update(double delta) {
		entities.update(delta);
		
		if (window.getKeyboard().isPressed(KeyEvent.VK_W)) y++;
		if (window.getKeyboard().isPressed(KeyEvent.VK_S)) y--;
		if (window.getKeyboard().isPressed(KeyEvent.VK_A)) x++;
		if (window.getKeyboard().isPressed(KeyEvent.VK_D)) x--;
	}

	Bitmap test = new Bitmap("/placeholder.png");

	@Override
	public void render(Bitmap screen) {
		screen.fill(0);
		screen.blit(test, x, y);
		entities.render(screen);
		
		screen.drawString("Hello world", font, 20, 20, 0xFFFFFF);
	}

	public static void main(String... args) {
		int scale = 2;
		int width = 800 / scale;
		int height = 600 / scale;
		new Test(new Window(new Dimension2f(width, height), scale, "Test Game for Engine thing")).start();
	}
}

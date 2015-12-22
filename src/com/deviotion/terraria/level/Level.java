package com.deviotion.terraria.level;

import java.util.Random;

import uk.co.nozzer.gfx.Bitmap;
import uk.co.nozzer.maths.Dimension2f;
import uk.co.nozzer.maths.Vector2f;

public class Level {

	private Dimension2f size;
	private Random random;
	
	private Tile[] tiles;
	
	public static final int TILE_SIZE = 8;
	
	public Level(Dimension2f size) {
		this.size = size;
		this.tiles = new Tile[(int) size.getWidth() * (int) size.getHeight()];
		
		this.random = new Random();
		
		generate();
	}
	
	public void update(double delta) {
		for (int i = 0; i < tiles.length; i++) {
			tiles[i].update(delta);
		}
	}
	
	public void render(Bitmap screen) {
		for (int i = 0; i < tiles.length; i++) {
			tiles[i].render(screen);
		}
	}
	
	private void generate() {
		for (int x = 0; x < size.getWidth(); x++) {
			for (int y = 0; y < size.getHeight(); y++) {
				if (random.nextInt(2) == 0) tiles[x + y * (int) size.getWidth()] = new TileDirt(new Vector2f(x, y));
//				else if (random.nextInt(2) == 1) tiles[x + y * (int) size.getWidth()] = new TileGrass(new Vector2f(x, y));
				else tiles[x + y * (int) size.getWidth()] = new TileGrass(new Vector2f(x, y));
			}
		}
	}
}

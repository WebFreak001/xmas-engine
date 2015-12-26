package com.deviotion.terraria.level;

import java.util.Random;

import uk.co.nozzer.gfx.Bitmap;
import uk.co.nozzer.maths.Dimension2f;
import uk.co.nozzer.maths.Vector2f;

public class Level {

	private Dimension2f size;
	private Tile[] tiles;
	
	private Random random;

	public static final int TILE_SIZE = 8;
	
	public static int UNDERGROUND_LEVEL;
	
	private int gravity = 1;
	
	public Level(Dimension2f size) {
		this.size = size;
		this.tiles = new Tile[(int) size.getWidth() * (int) size.getHeight()];
		
		this.random = new Random();
		
		UNDERGROUND_LEVEL = (int) size.getHeight() / 2 + 10;

		generate();
	}

	public void update(double delta) {
		for (int i = 0; i < tiles.length; i++) {
			Tile tile = tiles[i];
			if (tile == null)
				return;
			tile.update(delta);
		}
	}

	public void render(Bitmap screen) {
		for (int i = 0; i < tiles.length; i++) {
			Tile tile = tiles[i];
			if (tile == null)
				return;
			tile.render(screen);
		}
	}

	private void generate() {
		for (int x = 0; x < (int) size.getWidth(); x++) {
			for (int y = 0; y < (int) size.getHeight(); y++) {
				setTile(x, y, new TileAir(new Vector2f(x, y), this));
			}
		}
		for (int x = 0; x < (int) size.getWidth(); x++) {
			int y = (int) size.getHeight() / 2 - (int) (Math.sin(x) * (2 + random.nextInt(2)));
			
			setTile(x, y, new TileGrass(new Vector2f(x, y), this));
			for (int yy = y; yy < (int) size.getHeight(); yy++) {
				if (yy - (y + random.nextInt(10)) <= 3) setTile(x, yy, new TileDirt(new Vector2f(x, yy), this));
				else setTile(x, yy, new TileStone(new Vector2f(x, yy), this));
			}
		}
		
		for (int x = 0; x < (int) size.getWidth(); x++) {
			for (int y = 0; y < (int) size.getHeight(); y++) {
				Tile tile = getTile(x, y);
				if (tile instanceof TileDirt) {
					for (int depth = 0; depth < 4; depth++) {
						if (getTile(x, y - depth) instanceof TileAir && random.nextInt(depth) == 0) {
							setTile(x, y, new TileGrass(tile.getPosition(), this));
							break;
						}
						
					}
				}
			}
		}
	}

	// --- getters and setters
	public void setTile(Vector2f position, Tile tile) {
		setTile((int) position.getX(), (int) position.getY(), tile);
	}

	public void setTile(int x, int y, Tile tile) {
		if (x < 0 || y < 0 || x >= (int) size.getWidth() || y >= (int) size.getHeight())
			return;
		tiles[x + y * (int) size.getWidth()] = tile;
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= (int) size.getWidth() || y >= (int) size.getHeight())
			return new TileVoid(new Vector2f(0, 0), this);
		return tiles[x + y * (int) size.getWidth()];
	}
	
	public int getGravity() {
		return this.gravity;
	}
}

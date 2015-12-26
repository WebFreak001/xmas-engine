package com.deviotion.terraria.level;

import java.util.Random;

import com.deviotion.terraria.gfx.Art;

import uk.co.nozzer.gfx.Bitmap;
import uk.co.nozzer.maths.Vector2f;

public class TileDirt extends Tile {

	private Random random;
	
	private int specXOffs = 0;
	private int specYOffs = 0;
	
	private int specXOffs2 = 0;
	private int specYOffs2 = 0;
	
	public TileDirt(Vector2f position, Level level) {
		super(position, level);
		
		this.random = new Random();
		
		this.specXOffs = random.nextInt(Level.TILE_SIZE);
		this.specYOffs = random.nextInt(Level.TILE_SIZE);
		
		this.specXOffs2 = random.nextInt(Level.TILE_SIZE);
		this.specYOffs2 = random.nextInt(Level.TILE_SIZE);
	}

	@Override
	public void update(double delta) {
		
	}

	@Override
	public void render(Bitmap screen) {
		screen.blit(Art.TILE_DIRT, (int) position.getX() * Level.TILE_SIZE, (int) position.getY() * Level.TILE_SIZE);
		
		screen.setPixel((int) position.getX() * Level.TILE_SIZE + specXOffs, (int) position.getY() * Level.TILE_SIZE  + specYOffs, 0x725139);
		screen.setPixel((int) position.getX() * Level.TILE_SIZE + specXOffs2, (int) position.getY() * Level.TILE_SIZE  + specYOffs2, 0x725139);
	}

	// --- getters and setters
	public boolean isSolid() {
		return true;
	}
}

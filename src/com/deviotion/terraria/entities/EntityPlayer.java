package com.deviotion.terraria.entities;

import java.awt.event.KeyEvent;

import com.deviotion.terraria.gfx.Art;
import com.deviotion.terraria.level.Level;

import uk.co.nozzer.entities.EntityRenderable;
import uk.co.nozzer.gfx.Bitmap;
import uk.co.nozzer.gfx.Font;
import uk.co.nozzer.input.Keyboard;
import uk.co.nozzer.maths.Dimension2f;
import uk.co.nozzer.maths.Vector2f;

public class EntityPlayer extends EntityRenderable {

	private Level level;
    private int speed = 1;
    private boolean jumping = false;
    private int jumpY = 0;
    private boolean onGround = false;
    
	private Font font = new Font(Art.FONT);
    
    private static final String NAME = "J3NN33";
	
	public EntityPlayer(Vector2f position, Dimension2f size, Level level) {
		super(position, size);
		
		this.level = level;
	}
	
	private void move(int dx, int dy) {
		boolean collide = false;
		int xp = (int) position.getX() + dx;
		int yp = (int) position.getY() + dy;
		
		// top left
		if (level.getTile(xp / Level.TILE_SIZE, yp / Level.TILE_SIZE).isSolid()) collide = true;

		// top right
		if (level.getTile((xp + (int) size.getWidth()) / Level.TILE_SIZE, yp / Level.TILE_SIZE).isSolid()) collide = true;
		
		// bottom left
		if (level.getTile(xp / Level.TILE_SIZE, (yp + (int) size.getHeight()) / Level.TILE_SIZE).isSolid()) collide = true;
		
		// bottom right
		if (level.getTile((xp + (int) size.getWidth()) / Level.TILE_SIZE, (yp + (int) size.getHeight()) / Level.TILE_SIZE).isSolid()) collide = true;
		
		if(!collide) {
			this.position.add(dx, dy);
		}
	}
	
	@Override
	public void update(double delta) {
		if (level.getTile((int) position.getX() / Level.TILE_SIZE, ((int) position.getY() + (int) size.getHeight()) / Level.TILE_SIZE + 1).isSolid()) {
			onGround = true;
		}
		
		if (Keyboard.isPressed(KeyEvent.VK_A)) move(-speed, 0);
		if (Keyboard.isPressed(KeyEvent.VK_D)) move(speed, 0);
		
		if (Keyboard.isPressed(KeyEvent.VK_W)) {
			if (!jumping && onGround) {
				onGround = false;
				jumping = true;
				jumpY = 6;
			}
		}
		
		move(0, -jumpY);
		if (jumpY > 0) jumpY -= level.getGravity();
		if (jumpY <= 0) {
			jumping = false;
			jumpY = 0;
		}
		move(0, level.getGravity());
	}
	
	@Override
	public void render(Bitmap screen) {
		screen.blit(Art.PLAYER, position);
		
		screen.drawString(position.toString(), font, 0, 0, 0xFFFFFF);
		screen.drawString("jumpY: " + jumpY, font, 0, 12, 0xFFFFFF);
		screen.drawString("Jumping: " + jumping, font, 0, 21, 0xFFFFFF);
	}
}

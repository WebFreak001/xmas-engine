package com.deviotion.terraria.entities;

import java.util.Random;

import uk.co.nozzer.entities.EntityRenderable;
import uk.co.nozzer.gfx.Bitmap;
import uk.co.nozzer.gfx.ColourUtils;
import uk.co.nozzer.maths.Dimension2f;
import uk.co.nozzer.maths.Vector2f;

public class EntitySnow extends EntityRenderable {
	
	private Random random;
	private Vector2f deltaPosition;
	private Dimension2f bounds;
	
	public static final int MAX_X_SPEED = 1;
	public static final int MAX_Y_SPEED = 3;
	
	public EntitySnow(Vector2f position, Dimension2f size, Dimension2f bounds) {
		super(position, size);
		
		this.random = new Random();
		this.deltaPosition = new Vector2f(random.nextFloat() * MAX_X_SPEED, random.nextFloat() * MAX_Y_SPEED);
		this.size.add(this.deltaPosition.getY(), this.deltaPosition.getY());
		
		this.bounds = bounds;
	}

	@Override
	public void update(double delta) {
		this.position.add(this.deltaPosition);
		
		if (this.position.getX() > this.bounds.getWidth()) this.position.setX(0);
		if (this.position.getX() < 0) this.position.setX(this.bounds.getWidth());
		if (this.position.getY() > this.bounds.getHeight()) this.position.setY(0);
		if (this.position.getY() < 0) this.position.setY(this.bounds.getHeight());
	}

	@Override
	public void render(Bitmap screen) {
		screen.fillRectangle(position, size, 0xFFFFFF);
	}

}

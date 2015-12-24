package uk.co.nozzer.gfx;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import uk.co.nozzer.maths.Dimension2f;
import uk.co.nozzer.maths.Vector2f;

public class Bitmap {

	private int width;
	private int height;

	private BufferedImage image;
	private int[] pixels;

	public Bitmap(int width, int height) {
		this.width = width;
		this.height = height;

		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	}

	public Bitmap(Dimension2f size) {
		this.width = (int) size.getWidth();
		this.height = (int) size.getHeight();

		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	}

	public Bitmap(String path) {
		try {
			BufferedImage image = ImageIO.read(this.getClass().getResourceAsStream(path));
			int width = image.getWidth();
			int height = image.getHeight();

			this.pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
			this.width = width;
			this.height = height;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void fill(int colour) {
		Arrays.fill(this.pixels, colour);
	}

	public void blit(Bitmap bitmap, int xPos, int yPos) {
		for (int y = 0; y < bitmap.getHeight(); y++) {
			int yp = y + yPos;
			for (int x = 0; x < bitmap.getWidth(); x++) {
				int xp = x + xPos;
				setPixel(xp, yp, bitmap.getPixel(x, y));
			}
		}
	}

	public void fillRectangle(Vector2f position, Dimension2f size, int colour) {
		fillRectangle((int) position.getX(), (int) position.getY(), (int) size.getWidth(), (int) size.getHeight(),
				colour);
	}

	public void fillRectangle(int xPos, int yPos, int width, int height, int colour) {
		for (int x = 0; x < width; x++) {
			int xp = x + xPos;
			for (int y = 0; y < height; y++) {
				int yp = y + yPos;
				setPixel(xp, yp, colour);
			}
		}
	}

	public void fillCircle(Vector2f position, int radius, int colour) {
		fillCircle((int) position.getX(), (int) position.getY(), radius, colour);
	}
	public void fillCircle(int xPos, int yPos, int radius, int colour) {
		for (int x = -radius; x <= radius; x++) {
			for (int y = -radius; y <= radius; y++) {
				if (x * x + y * y < radius * radius) {
					setPixel(xPos + x, yPos + y, colour);
				}
			}
		}
	}
	
	public void drawString(String string, Font font, int x, int y, int colour) {
		Spritesheet sheet = font.getSheet();
		for (int i = 0; i < string.length(); i++) {
			String currentChar = string.split("")[i];
			int index = font.getFontMap().indexOf(currentChar);
			
			Bitmap character = sheet.getSprite(
					index % sheet.getSheetSpriteWidth(), 
					(int) Math.floor(index / sheet.getSheetSpriteWidth()));
			blit(character, x + (i * sheet.getSpriteWidth()), y);
		}
	}
	
	public void save(String path) {
		try {
			ImageIO.write(image, "PNG", new File(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// --- getters and setters
	public BufferedImage getImage() {
		return this.image;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public int getPixel(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return 0;
		else
			return pixels[x + y * width];
	}

	public void setPixel(Vector2f position, int colour) {
		setPixel((int) position.getX(), (int) position.getY(), colour);
	}
	public void setPixel(int x, int y, int colour) {
		if (x < 0 || y < 0 || x >= width || y >= height || colour == 0)
			return;
		if (colour == 0xFF00FF || colour == 0xFFFF00FF)
			return;
		this.pixels[x + y * width] = colour;
	}
}

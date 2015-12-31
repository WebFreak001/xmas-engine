package uk.co.nozzer.engine.gfx;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import uk.co.nozzer.engine.maths.Dimension2f;
import uk.co.nozzer.engine.maths.Vector2f;
import uk.co.nozzer.engine.utils.Utils;

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

	
	public void blit(Bitmap bitmap, Vector2f position) {
		blit(bitmap, (int) position.getX(), (int) position.getY());
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
	
	public void blit(Bitmap bitmap, Vector2f position, int scale) {
		blit(bitmap, (int) position.getX(), (int) position.getY(), scale);
	}
	public void blit(Bitmap bitmap, int xPos, int yPos, int scale) {
		
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
	
	public void drawRectangle(Vector2f position, Dimension2f size, int colour) {
		drawRectangle((int) position.getX(), (int) position.getY(), (int) size.getWidth(), (int) size.getHeight(), colour);
	}
	public void drawRectangle(int xPos, int yPos, int width, int height, int colour) {
		drawLine(xPos, yPos, xPos + width, yPos, colour);
		drawLine(xPos, yPos, xPos, yPos + height, colour);
		drawLine(xPos + width, yPos, xPos + width, yPos + height, colour);
		drawLine(xPos + width, yPos + height, xPos, yPos + height, colour);
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
	
	public void fillEllipse(int xPos, int yPos, int radiusWidth, int radiusHeight, int colour) {
		for (int x = -radiusWidth; x <= radiusWidth; x++) {
			for (int y = -radiusHeight; y <= radiusHeight; y++) {
				if (x * x + y * y < radiusWidth * radiusHeight) {
					setPixel(xPos + x, yPos + y, colour);
				}
			}
		}
	}
	
	public void vignette() {
		int radiusWidth = (int) width;
		int radiusHeight = (int) height;
		
		for (int x = -radiusWidth; x <= radiusWidth; x++) {
			for (int y = -radiusHeight; y <= radiusHeight; y++) {
				if (x * x + y * y < radiusWidth * radiusHeight) {
					int xp = width / 2 + x;
					int yp = height / 2 + y;
					setPixel(xp, yp, ColourUtils.darken(getPixel(xp, yp), (int) Utils.distance(xp, yp, width / 2, height / 2) / 2));
				}
			}
		}
		
	}

	public void drawString(String string, Font font, int x, int y, int colour) {
		Spritesheet sheet = font.getSheet();
		for (int i = 0; i < string.length(); i++) {
			String currentChar = string.split("")[i];
			int index = font.getFontMap().indexOf(currentChar);

			Bitmap character = sheet.getSprite(index % sheet.getSheetSpriteWidth(),
					(int) Math.floor(index / sheet.getSheetSpriteWidth()));
			blit(character, x + (i * sheet.getSpriteWidth()), y);
		}
	}

	public void drawLine(Vector2f from, Vector2f to, int colour) {
		drawLine((int) from.getX(), (int) from.getY(), (int) to.getX(), (int) to.getY(), colour);
	}
	public void drawLine(int x, int y, int x2, int y2, int colour) {
		int w = x2 - x;
		int h = y2 - y;
		int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0;
		if (w < 0)
			dx1 = -1;
		else if (w > 0)
			dx1 = 1;
		if (h < 0)
			dy1 = -1;
		else if (h > 0)
			dy1 = 1;
		if (w < 0)
			dx2 = -1;
		else if (w > 0)
			dx2 = 1;
		int longest = Math.abs(w);
		int shortest = Math.abs(h);
		if (!(longest > shortest)) {
			longest = Math.abs(h);
			shortest = Math.abs(w);
			if (h < 0)
				dy2 = -1;
			else if (h > 0)
				dy2 = 1;
			dx2 = 0;
		}
		int numerator = longest >> 1;
		for (int i = 0; i <= longest; i++) {
			setPixel(x, y, colour);
			numerator += shortest;
			if (!(numerator < longest)) {
				numerator -= longest;
				x += dx1;
				y += dy1;
			} else {
				x += dx2;
				y += dy2;
			}
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

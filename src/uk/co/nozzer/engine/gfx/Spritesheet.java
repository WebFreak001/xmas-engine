package uk.co.nozzer.engine.gfx;

public class Spritesheet {

	private Bitmap sheet;
	private int spriteWidth;
	private int spriteHeight;
	private int sheetSpriteWidth;
	private int sheetSpriteHeight;

	public Spritesheet(Bitmap sheet, int spriteSize) {
		this.sheet = sheet;
		this.spriteWidth = spriteSize;
		this.spriteHeight = spriteSize;

		this.sheetSpriteWidth = sheet.getWidth() / spriteSize;
		this.sheetSpriteHeight = sheet.getHeight() / spriteSize;
	}

	public Spritesheet(Bitmap sheet, int spriteWidth, int spriteHeight) {
		this.sheet = sheet;
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;

		this.sheetSpriteWidth = sheet.getWidth() / spriteWidth;
		this.sheetSpriteHeight = sheet.getHeight() / spriteHeight;
	}

	public Bitmap getSprite(int xPos, int yPos) {
		Bitmap sprite = new Bitmap(spriteWidth, spriteHeight);
		for (int x = 0; x < spriteWidth; x++) {
			int xp = x + (xPos * spriteWidth);
			for (int y = 0; y < spriteHeight; y++) {
				int yp = y + (yPos * spriteHeight);
				sprite.setPixel(x, y, sheet.getPixel(xp, yp));
			}
		}
		return sprite;
	}
	
	// --- getters and setters
	public Bitmap getSheet() {
		return this.sheet;
	}

	public int getSpriteWidth() {
		return this.spriteWidth;
	}

	public int getSpriteHeight() {
		return this.spriteHeight;
	}

	public int getSheetSpriteWidth() {
		return this.sheetSpriteWidth;
	}

	public int getSheetSpriteHeight() {
		return this.sheetSpriteHeight;
	}
}

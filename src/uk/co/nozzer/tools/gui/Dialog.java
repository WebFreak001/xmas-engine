package uk.co.nozzer.tools.gui;

import uk.co.nozzer.engine.gfx.Bitmap;
import uk.co.nozzer.engine.gfx.Font;
import uk.co.nozzer.engine.gfx.Spritesheet;
import uk.co.nozzer.engine.gfx.Window;
import uk.co.nozzer.engine.maths.Dimension2f;
import uk.co.nozzer.engine.maths.Vector2f;

public class Dialog extends Component {
	
	private String title;
	private String body;
	
	private Vector2f position;
	private Dimension2f size;
	
	private Bitmap screen;

	private Font font = new Font(new Spritesheet(new Bitmap("/font.png"), 6, 9));
	
	public Dialog(Window window, String title, String body) {
		this.position = new Vector2f(window.getInitialWidth() / 2 - (title.length() * font.getSheet().getSpriteWidth()) / 2, window.getInitialHeight() / 2 - (150 / 2));
		this.size = new Dimension2f(100 + (title.length() * font.getSheet().getSpriteWidth()), 150);
		
		this.title = title;
		this.body = body;
		
		this.screen = new Bitmap((int) size.getWidth(), (int) size.getHeight());
	}

	@Override
	public void update(double delta) {
		
	}

	@Override
	public void render() {
		screen.fillRectangle(Vector2f.ORIGIN, size, 0x444444);
		screen.fillRectangle(0, 0, (int) size.getWidth(), 20, 0x4F4F4F);
		screen.drawString(title, font, 4, 4, 0xFFFFF);
		
		int line = 0;
		for (int i = 0; i < body.length(); i++) {
			String[] bodySplit = body.split("");
			String currentChar = bodySplit[i];
			screen.drawString(currentChar, font, i * font.getSheet().getSpriteWidth(), 20 + line * font.getSheet().getSpriteHeight(),0xFFFFFF);
		}
	}
	
	// --- getters and setters
	public Bitmap getScreen() {
		return this.screen;
	}
	public Vector2f getPosition() {
		return this.position;
	}
	public Dimension2f getSize() {
		return this.size;
	}
}

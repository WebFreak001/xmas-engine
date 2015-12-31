package uk.co.nozzer.tools.map;

import java.util.ArrayList;
import java.util.List;

import uk.co.nozzer.engine.Game;
import uk.co.nozzer.engine.gfx.Bitmap;
import uk.co.nozzer.engine.gfx.Window;
import uk.co.nozzer.engine.maths.Dimension2f;
import uk.co.nozzer.engine.maths.Vector2f;
import uk.co.nozzer.tools.gui.Dialog;
import uk.co.nozzer.tools.gui.Panel;
import uk.co.nozzer.tools.gui.PanelMap;

public class MapEditor extends Game {

	private List<Panel> panels = new ArrayList<Panel>();

	private Panel panelToolbar;
	private Panel panelTiles;
	private Panel panelMap;
	
	private Dialog dialog;

	public MapEditor() {
		super(new Window(1024, 768, 1, "XMas Engine | Map Editor"));

		this.panelToolbar = new Panel(new Vector2f(0, 0), new Dimension2f(50, window.getInitialHeight()));
		this.panelToolbar.setBackgroundColour(0x333333);
		
		this.panelTiles = new Panel(new Vector2f(50, window.getInitialHeight() - 200), new Dimension2f(window.getInitialWidth() - 50, 200));
		this.panelTiles.setBackgroundColour(0x333333);

		this.panelMap = new PanelMap(new Vector2f(50, 0), new Dimension2f(window.getInitialWidth() - 50, window.getInitialHeight() - 200));
		
		this.panels.add(this.panelToolbar);
		this.panels.add(this.panelTiles);
		this.panels.add(this.panelMap);
		
		 this.dialog = new Dialog(window, "Test Title", "This is soem text that will be rendered and hopfully wrapped somewhere. My spelling is horrid");
	}

	@Override
	public void update(double delta) {
		for (Panel p : this.panels) {
			p.update(delta);
		}
	}

	@Override
	public void render(Bitmap screen) {
		screen.fill(0x111111);
		for (Panel p : this.panels) {
			p.render();
			screen.blit(p.getScreen(), p.getPosition());
		}
		
		dialog.render();
		screen.blit(dialog.getScreen(), dialog.getPosition());
	}
}

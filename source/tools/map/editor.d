module tools.map.editor;

import tools.gui.panel;
import tools.gui.dialog;
import tools.gui.panelmap;

import engine.game;
import engine.gfx.bitmap;
import engine.maths.vector;

class MapEditor : Game
{
public:
	this()
	{
		super(1024, 768, 1, "XMas Engine | Map Editor (D Edition)");

		pToolbar = new Panel(vec2i(0, 0), vec2i(50, height), 0xFF333333);
		pTiles = new Panel(vec2i(50, height - 200), vec2i(width - 50, 200), 0xFF333333);
		pMap = new PanelMap(vec2i(50, 0), vec2i(width - 50, height - 200));

		panels ~= [pToolbar, pTiles, pMap];

		dialog = new Dialog(this,
			"Test Title",
			"This is soem text that will be rendered and hopfully wrapped somewhere. My spelling is horrid");
			
		test = Bitmap("res/tiles.png");
	}

	override void update(double delta)
	{
		foreach (ref panel; panels)
			panel.update(delta);
	}

	override void render(Bitmap screen)
	{
		screen.fill(0xFF111111);
		screen.blit(test, 0, 0);
		foreach (ref panel; panels)
		{
			panel.render();
			screen.blit(panel.screen, panel.position);
		}

		dialog.render();
		screen.blit(dialog.screen, dialog.position);
		if(screenshot)
		{
			screen.save("test.png");
			screenshot = false;
		}
	}

private:
	Panel[] panels;
	Panel pToolbar, pTiles;
	PanelMap pMap;
	Dialog dialog;
	Bitmap test;
	bool screenshot = true;
}

void main()
{
	new MapEditor().start();
}

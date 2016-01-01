module tools.map.editor;

import std.stdio;
import std.random;
import std.datetime;

import tools.gui.panel;
import tools.gui.dialog;
import tools.gui.panelmap;

import engine.game;
import engine.entities.entitymanager;
import engine.entities.entityrenderable;
import engine.entities.gfx.light;
import engine.entities.gfx.lightfade;
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

		entities = new EntityManager();
		foreach (_; 0 .. 500)
			entities.addEntity(new Light(vec2i(200, 100), 64, uniform(0, 0xFFFFFF),
				0.2f));
	}

	override void update(double delta)
	{
		foreach (ref panel; panels)
			panel.update(delta);
		entities.update(delta);
		t += delta;
		foreach (i, ref entity; entities.entities)
			(cast(Light) entity).position = cast(vec2i) vec2(512 + sin(t + i) * (50 + i),
				384 + cos(t + i) * (50 + i));
	}

	override void render(Bitmap screen)
	{
		screen.fill(0xFF111111);
		foreach (i, ref panel; panels)
		{
			panel.render();
			screen.blit(panel.screen, panel.position);
		}

		entities.render(screen);

		dialog.render();
		screen.blit(dialog.screen, dialog.position);
		if (screenshot)
		{
			screen.save("test.png");
			screenshot = false;
		}
	}

private:
	EntityManager entities;
	Panel[] panels;
	Panel pToolbar, pTiles;
	PanelMap pMap;
	Dialog dialog;
	bool screenshot = true;
	double t = 0;
}

void main()
{
	new MapEditor().start();
}

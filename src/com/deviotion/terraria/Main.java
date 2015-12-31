package com.deviotion.terraria;

import uk.co.nozzer.engine.gfx.Window;

public class Main {

	public static final int SCALE = 3;
	public static final int WIDTH = 800 / SCALE;
	public static final int HEIGHT = 600 / SCALE;
	
	public static void main(String... args) {
		new TerrariaClone(new Window(WIDTH, HEIGHT, SCALE, "Terraria Clone | Powered by the XMas Engine")).start();
	}
}

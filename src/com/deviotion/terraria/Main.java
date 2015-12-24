package com.deviotion.terraria;

import uk.co.nozzer.gfx.Window;

public class Main {

	public static final int SCALE = 2;
	public static final int WIDTH = 1280 / SCALE;
	public static final int HEIGHT = WIDTH / 16 * 9;
	
	public static void main(String... args) {
		new TerrariaClone(new Window(WIDTH, HEIGHT, SCALE, "Terraria Clone | Powered by the XMas Engine")).start();
	}
}

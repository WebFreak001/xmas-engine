package com.deviotion.terraria;

import uk.co.nozzer.gfx.Window;

public class Main {

	public static void main(String... args) {
		int scale = 2;
		int width = 1280 / scale;
		int height = width / 16 * 9;
		
		new TerrariaClone(new Window(width, height, scale, "Terraria Clone | Powered by the XMas Engine")).start();
		
	}
}

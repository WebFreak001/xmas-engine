package uk.co.nozzer.gfx;

public class ColourUtils {

	public static int getHex(int r, int g, int b) {
		if (r > 255) r = 255;
		if (g > 255) g = 255;
		if (b > 255) b = 255;
		
		if (r < 0) r = 0;
		if (g < 0) g = 0;
		if (b < 0) b = 0;
		
		return (0xff << 24) | ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);
	}
	
	public static int getHex(int[] rgb) {
		int r = rgb[0];
		int g = rgb[1];
		int b = rgb[2];
		
		if (r > 255) r = 255;
		if (g > 255) g = 255;
		if (b > 255) b = 255;
		
		if (r < 0) r = 0;
		if (g < 0) g = 0;
		if (b < 0) b = 0;
		
		return (0xff << 24) | ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);
	}
	
	public static int[] getRGB(int hex) {
		int result[] = new int[3];

		result[0] = (hex >> 16) & 0xFF;
		result[1] = (hex >> 8) & 0xFF;
		result[2] = (hex >> 0) & 0xFF;

		return result;
	}
	
	public static int lighten(int hex, int factor) {
		int rgb[] = getRGB(hex);
		rgb[0] += factor;
		rgb[1] += factor;
		rgb[2] += factor;
		
		return getHex(rgb);
	}
	
	public static int darken(int hex, int factor) {
		int rgb[] = getRGB(hex);
		rgb[0] -= factor;
		rgb[1] -= factor;
		rgb[2] -= factor;
		
		return getHex(rgb);
	}
	
	public static int add(int hex1, int hex2, int factor) {
		if (factor < 0) factor = 0;
		int rgb1[] = getRGB(hex1);
		int rgb2[] = getRGB(hex2);
		
		int r = rgb1[0] + ((rgb2[0] / 100) * factor);
		int g = rgb1[1] + ((rgb2[1] / 100) * factor);
		int b = rgb1[2] + ((rgb2[2] / 100) * factor);
		
		return getHex(r, g, b);
	}

	public static int sub(int hex1, int hex2, int factor) {
		if (factor <= 0) factor = 1;
		int rgb1[] = getRGB(hex1);
		int rgb2[] = getRGB(hex2);
		
		int r = rgb1[0] - ((rgb2[0] / 100) * factor);
		int g = rgb1[1] - ((rgb2[1] / 100) * factor);
		int b = rgb1[2] - ((rgb2[2] / 100) * factor);
		
		return getHex(r, g, b);
	}
}

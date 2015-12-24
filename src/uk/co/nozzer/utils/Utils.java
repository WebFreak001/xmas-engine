package uk.co.nozzer.utils;

public class Utils {

	public static float distance(double x1, double y1, double x2, double y2) {
		return (float) (Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2)));
	}

}

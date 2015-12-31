package uk.co.nozzer.engine.maths;

public class Dimension2f {

	private float width;
	private float height;
	
	public Dimension2f(float width, float height) {
		this.width = width;
		this.height = height;
	}

	public void add(float amount) {
		this.width += amount;
		this.height += amount;
	}
	public void add(float width, float height) {
		this.width += width;
		this.height += height;
	}
	public void add(Dimension2f dimension) {
		this.width += dimension.getWidth();
		this.height += dimension.getHeight();
	}
	
	public void sub(float amount) {
		this.width -= amount;
		this.height -= amount;
	}
	public void sub(float width, float height) {
		this.width -= width;
		this.height -= height;
	}
	public void sub(Dimension2f dimension) {
		this.width -= dimension.getWidth();
		this.height -= dimension.getHeight();
	}
	
	// --- getters and setters
	public void set(float amount) {
		this.width = amount;
		this.height = amount;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	
	public float[] get() {
		float result[] = new float[2];
		result[0] = width;
		result[1] = height;
		
		return result;
	}
	public float getWidth() {
		return this.width;
	}
	public float getHeight() {
		return this.height;
	}
}

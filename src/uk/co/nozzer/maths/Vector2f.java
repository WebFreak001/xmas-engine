package uk.co.nozzer.maths;

public class Vector2f {

	private float x;
	private float y;
	
	public Vector2f() {
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	
	public void add(float value) {
		this.x += value;
		this.y += value;
	}
	public void add(float x, float y) {
		this.x += x;
		this.y += y;
	}
	public void add(Vector2f vector) {
		this.x += vector.getX();
		this.y += vector.getY();
	}
	
	public void sub(float value) {
		this.x -= value;
		this.y -= value;
	}
	public void sub(float x, float y) {
		this.x -= x;
		this.y -= y;
	}
	public void sub(Vector2f vector) {
		this.x -= vector.getX();
		this.y -= vector.getY();
	}
	
	public void multiply(float amount) {
		this.x *= amount;
		this.y *= amount;
	}
	public void multiply(float x, float y) {
		this.x *= x;
		this.y *= y;
	}
	public void multiply(Vector2f vector) {
		this.x *= vector.getX();
		this.y *= vector.getY();
	}
	
	public void divide(float amount) {
		this.x /= amount;
		this.y /= amount;
	}
	public void divide(float x, float y) {
		this.x /= x;
		this.y /= y;
	}
	public void divide(Vector2f vector) {
		this.x /= vector.getX();
		this.y /= vector.getY();
	}
	
	// --- getters and setters
	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}
	public void set(Vector2f vector) {
		this.x = vector.getX();
		this.y = vector.getY();
	}
	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}
	
	public float getX() {
		return this.x;
	}
	public float getY() {
		return this.y;
	}
	public float[] get() {
		float[] result = new float[2];
		result[0] = x;
		result[1] = y;
		
		return result;
	}
	
	public String toString() {
		return "X: " + x + " Y: " + y;
	}
}

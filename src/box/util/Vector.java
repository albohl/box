package box.util;

public class Vector {
	public double x, y, z;
	
	public Vector(double x, double y){
		this.x = x;
		this.y = y;
		z = 1.0;
	}
	
	public double dot(Vector v){
		return x*v.x + y*v.y;
	}

	public void print(){
		System.out.println("x: " + x + " y: " + y);
	}

	public Vector minus(Vector v) {
		return new Vector(x - v.x, y - v.y);
	}
	
}

package box.util;

import box.graphics.Screen;

public class Triangle {
	
	public Vector v0, v1, v2;
	public int minx, miny, maxx, maxy;
	private Screen screen;
	
	public Triangle(Vector v0, Vector v1, Vector v2, Screen screen){
		this.v0 = v0;
		this.v1 = v1;
		this.v2 = v2;
		v0.print();
		v1.print();
		v2.print();
		this.screen = screen;
	}
	
	public boolean pointInTriangle(Vector P){
		Vector AB = v1.minus(v0);//inefficient
		Vector AC = v2.minus(v0);
		Vector AP = P.minus(v0);
		double dotABAB = AB.dot(AB);
		double dotABAC = AB.dot(AC);
		double dotABAP = AB.dot(AP);
		double dotACAC = AC.dot(AC);
		double dotACAP = AC.dot(AP);
		double invDenom = 1.0 / (dotABAB * dotACAC - dotABAC * dotABAC);
		double u = (dotACAC * dotABAP - dotABAC * dotACAP) * invDenom;
		double v = (dotABAB * dotACAP - dotABAC * dotABAP) * invDenom;
		return (u >= 0.0) && (v >= 0.0) && (u + v <= 1.0);
	}
	
	public void render(int colour){
		double miny = Math.min(v0.y, Math.min(v1.y, v2.y));
		double minx = Math.min(v0.x,Math.min(v1.y, v2.y));
		double maxy = Math.max(v0.y, Math.max(v1.y, v2.y));
		double maxx = Math.max(v0.x,Math.max(v1.y, v2.y));
		Vector render = new Vector(minx, miny);
		for (render.y = miny; render.y < maxy; render.y++){
			for (render.x = minx; render.x < maxx; render.x++){
				if (pointInTriangle(render)){
					screen.pixels[(int)render.x + (int)render.y * screen.width] = colour;
				}
			}
		}
	}
}

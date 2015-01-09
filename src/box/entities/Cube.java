package box.entities;

import box.Game;
import box.graphics.Screen;
import box.util.Triangle;
import box.util.Vector;

public class Cube extends Entity{
	
	public Cube(Vector center, double size, Game game, Screen screen){
		super(center, game, screen);
		vectors = new Vector[4];
		double x = center.x;
		double y = center.y;
		double hsize = size / 2;
		vectors[0] = new Vector(x - hsize, y - hsize);
		vectors[1] = new Vector(x + hsize, y - hsize);
		vectors[2] = new Vector(x + hsize, y + hsize);
		vectors[3] = new Vector(x - hsize, y + hsize);
//		for (Vector v : vectors){
//			v.print();
//		}
		addTriangles();
	}
	
	public void addTriangles(){
		triangles = new Triangle[vectors.length - 2];
		for (int i = 0; i < triangles.length; i++){
			triangles[i] = new Triangle(vectors[0], vectors[i + 1], vectors[i + 2], screen);
		}
	}
}

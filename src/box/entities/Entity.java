package box.entities;

import box.Game;
import box.graphics.Screen;
import box.util.Matrix;
import box.util.Triangle;
import box.util.Vector;

public abstract class Entity {
	protected Game game;
	protected Screen screen;
	protected Vector[] vectors;
	protected Triangle[] triangles;
	protected Vector massCenter;
	protected Vector dir;
	protected Matrix transformation = new Matrix();
	protected double angVelocity;
	protected int colour = 0xff00ff;
	
	public Entity(Vector center, Game game, Screen screen){
		massCenter = center;
		this.game = game;
		this.screen = screen;
		game.entities.add(this);
	}
	
	public void update(){}

	public void render(){
		for (Triangle t: triangles){
			t.render(colour);
		}
	}
	
	public void move(){
		translate(dir);
		rotate(angVelocity);
		transformation.matrixReset();
	}
	
	public void translate(Vector direction){
		transformation.matrix[0][2] += direction.x;
		transformation.matrix[1][2] += direction.y;
	}
	
	public void scale(double size){
		transformation.matrix[0][0] += size;
		transformation.matrix[1][1] += size;
	}
	
	public void rotate(double radians){
		transformation.matrix[0][0] += Math.cos(radians);
		transformation.matrix[0][1] += -Math.sin(radians);
		transformation.matrix[1][0] += Math.sin(radians);
		transformation.matrix[1][1] += Math.cos(radians);
	}
}
package box.util;

public class Matrix {

public double[][] matrix;
	
	public Matrix(){
		matrix = new double[2][3];
	}
	
	public void matrixReset(){
		matrix[0][0] = 0;
		matrix[0][1] = 0;
		matrix[0][2] = 1;
		matrix[1][0] = 0;
		matrix[1][1] = 0;
		matrix[1][2] = 1;
	}
	
	public void matrixMultiplication(Vector v){
		v.x = matrix[0][0] * v.x + matrix[0][1] * v.y + matrix[0][2] * v.z;
		v.y = matrix[1][0] * v.x + matrix[1][1] * v.y + matrix[1][2] * v.z;
	}
}

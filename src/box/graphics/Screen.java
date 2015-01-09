package box.graphics;

public class Screen {

	public int width;
	public int height; 
	public int[] pixels;
	
	public Screen(int width, int height){
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void render(int xOffset, int yOffset) {
		for (int y = 0; y < height; y++){
			for (int x = 0; x < width; x++){
				pixels[x + y * width] = 0xffffff;
			}
		}
	}
	
}

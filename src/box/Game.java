package box;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import box.entities.Cube;
import box.entities.Entity;
import box.graphics.Screen;
import box.input.Mouse;
import box.util.Vector;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	
	public static int width = 800;
	public static int height = width / 16 * 9;
	public double tickRate = 60;
	private int clickCount;
	private int lastClickCount = 0;
	private Random random = new Random();
	
	private Thread gameThread;
	public JFrame frame = new JFrame();
	private boolean running = false;
	public List<Entity> entities = new ArrayList<Entity>();
	
	private Screen screen;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

	
	public Game() {
		Dimension size = new Dimension(width, height);
		setPreferredSize(size);
		screen = new Screen(width, height);
		frame = new JFrame();
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		Cube cube = new Cube(new Vector(100, 100), 20, this, screen);
	}
	
	public static void main(String[] args){
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle("Box");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / tickRate;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1){
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				frame.setTitle("Updates per second: " + updates + ", FPS: " + frames);
				updates = 0;
				frames = 0;
			}
		}
	}
	
	public void update() {
		clickCount = Mouse.clickCount;
		if (lastClickCount < clickCount){
			clicked();
			lastClickCount = clickCount;
		}
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null){
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();

		screen.render(0, 0);
		
		for (Entity e: entities){
			e.render();
		}
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	public void clicked(){
	}

	public synchronized void start() {
		running = true;
		gameThread = new Thread(this, "Game");
		gameThread.start();
	}
	
	public synchronized void stop() {
		running = false;
		try{
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

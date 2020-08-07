package James.Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import James.Game.framework.KeyInput;
import James.Game.framework.ObjectId;
import James.Objects.Player;

public class Game extends Canvas implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7587336698680269016L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH/12*9;
	private Thread thread;
	private boolean running = false;
	
	Handler handler;
	
	private void init(){
		handler = new Handler();
		
		handler.addObject(new Player(100, 100, ObjectId.Player));
		
		handler.createLevel();
		
		this.addKeyListener(new KeyInput(handler));
	}

	public Game(){
	
	}
	
	public synchronized void start(){
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		init();
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		double updates =0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta+=(now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			
			if(System.currentTimeMillis() - timer > 1000){
				timer+= 1000;
				System.out.println("FPS: "+ frames+ " TICKS: "+ updates);
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}
	
	
	
	public synchronized void stop(){
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void tick(){
		handler.tick();
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH+10, HEIGHT+10);
		
		handler.render(g);
		
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		new Window(WIDTH, HEIGHT, "Game", new Game());

	}

	

}

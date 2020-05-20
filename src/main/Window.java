package main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import gameObjects.Constantes;
import graphics.Recursos;
import input.Teclado;
import states.EstadoJuego;


public class Window extends JFrame implements Runnable{

	private static final long serialVersionUID = 1L;
	
	private Canvas canvas;
	private Thread thread;
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics gf;
	
	private final int FPS = 60;
	private double TARGETTIME = 1000000000/FPS;
	private double delta = 0;
	private int AVERAGEFPS = FPS;
	
	private EstadoJuego gameState;
	private Teclado keyBoard;
	
	public Window()
	{
		setTitle("Space Ship Game");
		setSize(Constantes.WIDTH, Constantes.HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		
		canvas = new Canvas();
		keyBoard = new Teclado();
		
		canvas.setPreferredSize(new Dimension(Constantes.WIDTH, Constantes.HEIGHT));
		canvas.setMaximumSize(new Dimension(Constantes.WIDTH, Constantes.HEIGHT));
		canvas.setMinimumSize(new Dimension(Constantes.WIDTH, Constantes.HEIGHT));
		canvas.setFocusable(true);
		
		add(canvas);
		canvas.addKeyListener(keyBoard);
		setVisible(true);
	}
	
	

	public static void main(String[] args) {
		new Window().start();
	}
	
	
	private void update(){
		keyBoard.update();
		gameState.update();
	}

	private void draw(){
		bs = canvas.getBufferStrategy();
		
		if(bs == null)
		{
			canvas.createBufferStrategy(3);
			return;
		}
		
		gf = bs.getDrawGraphics();
		
		//-----------------------
		
		gf.setColor(Color.BLACK);
		
		gf.fillRect(0, 0, Constantes.WIDTH, Constantes.HEIGHT);
		
		gameState.draw(gf);
		
		gf.setColor(Color.WHITE);
		
		gf.drawString(""+AVERAGEFPS, 10, 20);
		
		//---------------------
		
		gf.dispose();
		bs.show();
	}
	
	private void init()
	{
		Recursos.init();
		gameState = new EstadoJuego();
	}
	
	
	@Override
	public void run() {
		
		long now = 0;
		long lastTime = System.nanoTime();
		int frames = 0;
		long time = 0;
		
		init();
		
		while(running)
		{
			now = System.nanoTime();
			delta += (now - lastTime)/TARGETTIME;
			time += (now - lastTime);
			lastTime = now;
			
			
			
			if(delta >= 1)
			{		
				update();
				draw();
				delta --;
				frames ++;
			}
			if(time >= 1000000000)
			{
				AVERAGEFPS = frames;
				frames = 0;
				time = 0;
				
			}
			
			
		}
		
		stop();
	}
	
	private void start(){
		
		thread = new Thread(this);
		thread.start();
		running = true;
		
		
	}
	private void stop(){
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
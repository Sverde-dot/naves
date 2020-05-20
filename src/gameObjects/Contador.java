package gameObjects;

public class Contador {
	private long delta, ultimavez;
	private long time;
	private boolean running;
	
	public Contador(){
		delta = 0;
		ultimavez = 0;
		running = false;
	}
	
	public void run(long time){
		running = true;
		this.time = time;
	}
	
	public void actualizar(){	
		if(running)
			delta += System.currentTimeMillis() - ultimavez;
		if(delta >= time){
			running = false;
			delta = 0;
		}
		
		ultimavez = System.currentTimeMillis();
	}
	
	public boolean isRunning(){
		return running;
	}
	
}

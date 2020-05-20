package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener{
	
	private boolean[] tecla = new boolean[256];
	
	public static boolean arriba,abajo,izquierda,derecha,disparar;
	
	public Teclado()
	{
		arriba = false;
		abajo = false;
		izquierda = false;
		derecha = false;
		disparar = false;
	}
	
	public void update()
	{
		arriba = tecla[KeyEvent.VK_UP];
		abajo = tecla[KeyEvent.VK_DOWN];
		izquierda = tecla[KeyEvent.VK_LEFT];
		derecha = tecla[KeyEvent.VK_RIGHT];
		disparar = tecla[KeyEvent.VK_SPACE];
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		tecla[arg0.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		tecla[arg0.getKeyCode()] = false;
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {}
	
}

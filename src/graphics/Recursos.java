package graphics;

import java.awt.image.BufferedImage;

public class Recursos {
	
	public static BufferedImage jugador;
	public static BufferedImage enemigorojo;
	
	
	public static BufferedImage speed;
	
	// lasers
	
	public static BufferedImage laserjugador;
	
	public static void init()
	{
		jugador = Cargador.CargaImagen("/ships/player.png");
		enemigorojo = Cargador.CargaImagen("/ships/enemigorojo.png");
		
		speed = Cargador.CargaImagen("/effects/fire08.png");
		
		laserjugador = Cargador.CargaImagen("/lasers/laserBlue01.png");
		
		
		
	}
	
}

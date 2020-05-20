package gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import graphics.Recursos;
import input.Teclado;
import math.Vector2D;
import states.EstadoJuego;

public class Jugador extends MovingObject{
	
	private Vector2D cabezera;	
	private Vector2D acceleration;

	private boolean accelerating = false;
	private Contador ultimavez;
	
	public Jugador(Vector2D position, Vector2D velocidad, double maxVel, BufferedImage textura, EstadoJuego gameState) {
		super(position, velocidad, maxVel, textura, gameState);
		cabezera = new Vector2D(0, 1);
		acceleration = new Vector2D();
		ultimavez = new Contador();
	}
	
	@Override
	public void actualizar() 
	{
		
		
		if(Teclado.disparar &&  !ultimavez.isRunning())
		{		
			gameState.getMovingObjects().add(0,new Laser(
					getCenter().add(cabezera.escalar(width)),
					cabezera,
					Constantes.LASER_VEL,
					angulo,
					Recursos.laserjugador,
					gameState
					));
			ultimavez.run(Constantes.RatioFuego);
		}
		
		
		if(Teclado.izquierda)
			angulo -= Constantes.DELTAANGLE;
		if(Teclado.derecha)
			angulo += Constantes.DELTAANGLE;
		
		if(Teclado.arriba)
		{
			acceleration = cabezera.escalar(Constantes.ACC);
			accelerating = true;
		}else
		{
			if(velocidad.getMagnitud() != 0)
				acceleration = (velocidad.escalar(-1).normalize()).escalar(Constantes.ACC/2);
			accelerating = false;
		}
		
		velocidad = velocidad.add(acceleration);
		
		velocidad = velocidad.limit(maxVel);
		
		cabezera = cabezera.setDirection(angulo - Math.PI/2);
		
		posicion = posicion.add(velocidad);
		
		if(posicion.getX() > Constantes.WIDTH)
			posicion.setX(0);
		if(posicion.getY() > Constantes.HEIGHT)
			posicion.setY(0);
		
		if(posicion.getX() < 0)
			posicion.setX(Constantes.WIDTH);
		if(posicion.getY() < 0)
			posicion.setY(Constantes.HEIGHT);
		
		
		ultimavez.actualizar();
		colisionar();
		
	}
	
	
	@Override
	public void dibujar(Graphics gf) {
		
		Graphics2D gf2d = (Graphics2D)gf;
		
		AffineTransform at1 = AffineTransform.getTranslateInstance(posicion.getX() + width/2 + 5,
				posicion.getY() + height/2 + 10);
		
		AffineTransform at2 = AffineTransform.getTranslateInstance(posicion.getX() + 5, posicion.getY() + height/2 + 10);
		
		at1.rotate(angulo, -5, -10);
		at2.rotate(angulo, width/2 -5, -10);
		
		if(accelerating)
		{
			gf2d.drawImage(Recursos.speed, at1, null);
			gf2d.drawImage(Recursos.speed, at2, null);
		}
		
		
		
		at = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());
		
		at.rotate(angulo, width/2, height/2);
		
		gf2d.drawImage(Recursos.jugador, at, null);
		
	}
	
	
}

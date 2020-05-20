package gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import graphics.Recursos;
import math.Vector2D;
import states.EstadoJuego;

public class Enemigo extends MovingObject{

	public Enemigo(Vector2D posicion, Vector2D velocidad, double maxVel, BufferedImage textura, EstadoJuego gameState) {
		super(posicion, velocidad, maxVel, textura, gameState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actualizar() {
		posicion = posicion.add(velocidad);
		
		if(posicion.getX() > Constantes.WIDTH)
			posicion.setX(0);
		if(posicion.getY() > Constantes.HEIGHT)
			posicion.setY(0);
		
		if(posicion.getX() < 0)
			posicion.setX(Constantes.WIDTH);
		if(posicion.getY() < 0)
			posicion.setY(Constantes.HEIGHT);
		
		angulo += Constantes.DELTAANGLE;
	}

	@Override
	public void dibujar(Graphics gf) {
		Graphics2D gf2d = (Graphics2D)gf;
		
		at = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());
		
		at.rotate(angulo, width/2, height/2);
		
		gf2d.drawImage(Recursos.enemigorojo, at, null);
		
	}

}

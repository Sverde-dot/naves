package gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import math.Vector2D;
import states.EstadoJuego;

public class Laser extends MovingObject{

	public Laser(Vector2D position, Vector2D velocidad, double maxVel, double angulo, BufferedImage textura, EstadoJuego gameState) {
		super(position, velocidad, maxVel, textura, gameState);
		this.angulo = angulo;
		this.velocidad = velocidad.escalar(maxVel);
	}

	@Override
	public void actualizar() {
		posicion = posicion.add(velocidad);
		if(posicion.getX() < 0 || posicion.getX() > Constantes.WIDTH ||
				posicion.getY() < 0 || posicion.getY() > Constantes.HEIGHT){
			Destruir();
		}
		colisionar();
	}
	

	@Override
	public void dibujar(Graphics gf) {
		Graphics2D gf2d = (Graphics2D)gf;
		
		at = AffineTransform.getTranslateInstance(posicion.getX() - width/2, posicion.getY());
		
		at.rotate(angulo, width/2, 0);
		
		gf2d.drawImage(textura, at, null);
		
	}
	
	public Vector2D getCenter(){
		return new Vector2D(posicion.getX() + width/2, posicion.getY() + width/2);
	}
}

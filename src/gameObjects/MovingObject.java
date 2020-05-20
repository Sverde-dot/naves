package gameObjects;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import math.Vector2D;
import states.EstadoJuego;

public abstract class MovingObject extends GameObject{
	
	protected Vector2D velocidad;
	protected AffineTransform at;
	protected double angulo;
	protected double maxVel;
	protected int width;
	protected int height;
	protected EstadoJuego gameState;
	
	public MovingObject(Vector2D posicion, Vector2D velocidad, double maxVel, BufferedImage textura, EstadoJuego gameState) {
		super(posicion, textura);
		this.velocidad = velocidad;
		this.maxVel = maxVel;
		this.gameState = gameState;
		width = textura.getWidth();
		height = textura.getHeight();
		angulo = 0;
		
	}
	
	protected void colisionar() {
		ArrayList<MovingObject> movingObjects = gameState.getMovingObjects();
		
		for(int i=0;i<movingObjects.size();i++) {
			MovingObject mover = movingObjects.get(i);
			if(mover.equals(this))
				continue;
			
			double distanciaobjetos = mover.getCenter().quitar(getCenter()).getMagnitud();
			if(distanciaobjetos <mover.width/2 + width/2 && movingObjects.contains(this)) {
				colisionObjetos(mover,this);
			}
		}
	}
	
	private void colisionObjetos(MovingObject v, MovingObject w) {
		if(!(v instanceof Enemigo && w instanceof Enemigo) ) {
			v.Destruir();
			w.Destruir();
		}
	}
	
	protected void Destruir() {
		gameState.getMovingObjects().remove(this);
	}
	
	protected Vector2D getCenter(){
		return new Vector2D(posicion.getX() + width/2, posicion.getY() + height/2);
	}

}

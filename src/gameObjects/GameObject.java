package gameObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import math.Vector2D;

public abstract class GameObject {
	protected BufferedImage textura;
	protected Vector2D posicion;
	
	public GameObject(Vector2D posicion, BufferedImage textura)
	{
		this.posicion = posicion;
		this.textura = textura;
	}
	
	public abstract void actualizar();
	
	public abstract void dibujar(Graphics gf);

	public Vector2D getPosicion() {
		return posicion;
	}

	public void setPosition(Vector2D position) {
		this.posicion = position;
	}
	
}

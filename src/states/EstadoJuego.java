package states;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gameObjects.Constantes;
import gameObjects.Enemigo;
import gameObjects.MovingObject;
import gameObjects.Jugador;
import graphics.Recursos;
import math.Vector2D;

public class EstadoJuego {
	
	private Jugador player;
	private ArrayList<MovingObject> movingObjects = new ArrayList<MovingObject>();
	private int enemigo;
	
	public EstadoJuego()
	{
		player = new Jugador(new Vector2D(Constantes.WIDTH/2 - Recursos.jugador.getWidth()/2,
				Constantes.HEIGHT/2 - Recursos.jugador.getHeight()/2), new Vector2D(), Constantes.PLAYER_MAX_VEL, Recursos.jugador, this);
		movingObjects.add(player);
		
		enemigo=1;
		Oleada();
	}
	
	private void Oleada() {
		double x,y;
		for(int i=0;i<enemigo;i++) {
			x = i% 2== 0 ? Math.random()*Constantes.WIDTH :0;
			y = i% 2== 0 ? 0 : Math.random()*Constantes.HEIGHT;
			BufferedImage textura = Recursos.enemigorojo;
			movingObjects.add(new Enemigo(
					new Vector2D(x,y),
					new Vector2D(0,1).setDirection(Math.random()*Math.PI*2),
					Constantes.ENEMIGO_VEL +1,
					textura,
					this
					));
		}
	}
	
	
	public void update()
	{
		for(int i = 0; i < movingObjects.size(); i++)
			movingObjects.get(i).actualizar();
	}
	
	public void draw(Graphics gf)
	{	
		Graphics2D gf2d = (Graphics2D)gf;
		
		gf2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		for(int i = 0; i < movingObjects.size(); i++)
			movingObjects.get(i).dibujar(gf);
	}

	public ArrayList<MovingObject> getMovingObjects() {
		return movingObjects;
	}
}

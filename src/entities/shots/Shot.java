package entities.shots;

import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import entities.Entity;
import utils.DataApi;

public abstract class Shot extends Entity{
	public int direction = 1, shooterid; //1 down, -1 up
	public boolean collided = false;
	public Shot(int x, int y, int width, int height, int shooterid) {
		super(x, y, width, height, 9999, null); //Pongo el ultimo número alto, por ejemplo 9999 ya que no creo que llegen a 9999 entidades.
		isMoving = true;
		this.shooterid = shooterid;
		damage = 20;
	}

	@Override
	public BufferedImage getImage() {		
		return null;
	}
	
	public int getShooter() {
		return shooterid;
	}
	
	@Override
	public int getHP() {	
		return 0;
	}
	
	//1 down, -1 up.
	public void direction(int direction) {
		if(direction != 1 && direction != -1) return;
		this.direction = direction;
	} //there's only ONE DIRECTION.
	
	public int getDirection() {
		return direction;
	}
	
	public void move() {
		label.setLocation(label.getX(), label.getY()+(speed*direction));
		bounds.setLocation(label.getX(), label.getY());
	}
}

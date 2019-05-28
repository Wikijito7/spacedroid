package entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entities.shots.GreenShot;
import entities.shots.Shot;
import utils.Assets;
import utils.DataApi;

public abstract class Entity {
	
	/**
	 * Se hace la clase abstract para que sirva de base para usarla en otras clases y no repetir mucho codigo y crear lo mismo en distintas, haciendo
	 * más eficiente el código y, en definitiva, haciendo que vaya algo mejor el programa.
	 * Además, se definen las variables que van a usar todas las clases que recojan este objeto, el objeto Entity.
	 * Finalmente se declara cómo se va a componer estas clases con los public abstract para que, de nuevo, se pueda reutilizar y escribir lo que se quiera
	 * de cada uno, independientemente si es un cuadrado o una nave. 
	 */	
	
	public BufferedImage image; 
	public DataApi dataApi;
	public int speed = 5, damage = 5, health = 5, attackProb = 60/*siendo 1 100% y 100 1%.*/, bullets = 3, currentShots = 0, explosionDelay = 0, dieAnimNum = 0, x, y, width, height, id;
	public boolean isDead = false, isMoving = false, isExploding = false;
	public Rectangle bounds;
	public JLabel label, bullet;
	private Clip explosion;
	public Entity(int x, int y, int width, int height, int id, DataApi dataApi) {
		bounds = new Rectangle(x, y, width, height);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		this.dataApi = dataApi;
		label = new JLabel();
	}
	
	public abstract BufferedImage getImage();
	public abstract Rectangle getHitbox();
	public abstract JLabel getLabel();
	public abstract int getHP();
	public abstract String getName();
	public void move() {
		if(label.getY() > 680) {
			label.setLocation(label.getX(), 0);
			dataApi.removeScore(25);
		}
		label.setLocation(label.getX(), label.getY()+speed);
		bounds.setLocation(label.getX(), label.getY());
	}
	
	public int getX() {
		return label.getX();
	}
	
	public int getY() {
		return label.getY();
	}
	
	public int getId() {
		return id;
	}
	
	public Shot shot(int dir, int type) {
		int r = new Random().nextInt(attackProb);
		if(r == 0 && currentShots < bullets && isMoving && !isExploding) {
			Shot s = new GreenShot(getX()+12, getY()+32, id);
			s.direction(dir);
			currentShots++;
			return s;
		}
		return null;
	}
	
	public abstract void animation();
	public void damage(int dmg) {
		if(getHP() <= 0 || getHP()-dmg <= 0) {
			isExploding = true;
			dieAnimation();
			return;
		}
		health -= dmg;
	}
	
	public void die() {
		isDead = true;
		dataApi.addScore(50);
		speed = 0;
		label.setVisible(false);
	}
	
	public void dieAnimation() {
		if(!isExploding) return;
		if(explosionDelay > 0) {
			explosionDelay--;
			return;
		}
		if(dieAnimNum == 6) {
			die();
			return;
		}
		if(speed > 0) speed--;
		image = Assets.explosion[dieAnimNum];
		label.setIcon(new ImageIcon(image));
		dieAnimNum++;
		explosionDelay = 20;
	}
	public boolean hasCollided(int x, int y) {
		return label.getBounds().contains(x, y);
	}
	
	public void interfereShip() {
		if(speed != 1) speed = speed/2;
		attackProb = 1000;
	}
	
	private void explode() {
		try {
			explosion = AudioSystem.getClip();
			explosion.open(AudioSystem.getAudioInputStream(Entity.class.getResource("/sounds/game-s/explosion" + (new Random().nextInt(2)+1) + ".wav")));
			explosion.start();
		}catch(Exception e) {}
	}
}

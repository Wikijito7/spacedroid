package entities.usership;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import entities.Entity;
import entities.shots.BlueShot;
import entities.shots.BulletShot;
import entities.shots.GreenShot;
import entities.shots.OrangeShot;
import entities.shots.PurpleShot;
import entities.shots.RedShot;
import entities.shots.Shot;
import utils.Assets;
import utils.DataApi;

public class Usership extends Entity{
	private int animNum = 0, dir_ud = 0, dir_lr = 0;
	public int speedModifier = 0, shieldHealth = 0;
	public int regenerateshield = 0;
	protected BufferedImage shieldImage;
	public JLabel shield; 
	public Usership(int x, int y, DataApi dataApi) {
		super(x, y, 32, 32, 0, null);
		image = Assets.usership[animNum];
		shieldImage = Assets.shield[0];
		attackProb = 1;
		speed = 4;
		bullets = 3;
		health = 3;
		shield = new JLabel();
		generateLabel();
		generateShield();
		activateShield();
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

	@Override
	public Rectangle getHitbox() {
		return label.getBounds();
	}
	
	@Override
	public boolean hasCollided(int x, int y) {
		if(shieldHealth > 0) return shield.contains(x, y);
		return label.contains(x, y);
	}
	
	@Override
	public JLabel getLabel() {
		return label;
	}
	
	public JLabel getShieldLabel() {
		return shield;
	}

	@Override
	public int getHP() {
		return health;
	}

	@Override
	public String getName() {
		return "Usership";
	}

	@Override
	public void animation() {
		if(isExploding) return;
		if(animNum > 3) animNum = 0;
		image = Assets.usership[animNum];
		if(shieldHealth > 0) {
			shieldImage = Assets.shield[animNum];
			shield.setIcon(new ImageIcon(shieldImage));
		} else {
			destroyShield();
			if(regenerateshield > 140) activateShield(); 
			regenerateshield++;
		}
		label.setIcon(new ImageIcon(image));
		animNum++;
	}
	
	@Override
	public void move() {
		if(isExploding || isDead) return;
		if(dir_ud == 1 && label.getY() > 360) label.setLocation(label.getX(), label.getY()-speed);
		if(dir_lr == 1 && label.getX() > 0) label.setLocation(label.getX()-speed, label.getY());
		if(dir_ud == 2 && label.getY()+32 < 680) label.setLocation(label.getX(), label.getY()+speed);	
		if(dir_lr == 2 && label.getX()+32 < 640) label.setLocation(label.getX()+speed, label.getY());
		shield.setLocation(label.getX()-8, label.getY()-8);
	}
	
	public void menuMove() {
		label.setLocation(label.getX(), label.getY()-speed);
	}
	
	public void setUD_dir(int dir) {
		dir_ud = dir;
	}
	
	public void setLR_dir(int dir) {
		dir_lr = dir;
	}
	
	@Override
	public Shot shot(int dir, int type) {
		if(!isExploding) {
			Shot s;
			switch(type) {
			case 0:
				s = new RedShot(getX()+12, getY(), id);
				bullets = 3;
				break;
			case 1:
				s = new GreenShot(getX()+12, getY(), id);
				bullets = 3;
				break;
			case 2:
				s = new BlueShot(getX()+12, getY(), id);
				bullets = 3;
				break;
			case 3:
				s = new OrangeShot(getX()+12, getY(), id);
				bullets = 3;
				break;
			case 4:
				s = new PurpleShot(getX()+12, getY(), id);
				bullets = 3;
				break;
			case 5:
				s = new BulletShot(getX()+12, getY(), id);
				bullets = 10;
				break;
			default:
				s = new RedShot(getX()+12, getY(), id);
			}
			
			s.speed = s.speed + speedModifier;
 			s.direction(dir);
			return s;
		}
		return null;
	}
	
	@Override
	public void damage(int dmg) {
		if(health == 0) {
			isExploding = true;
			dieAnimation();
			return;
		}
		if(shieldHealth == 0) destroyShield();
		if(shieldHealth > 0) shieldHealth--;
		else health--;
	}
	
	@Override
	public void die() {
		isDead = true;
		speed = 0;
		label.setVisible(false);
	}
	
	public void destroyShield() {
		shield.setVisible(false);
		shield.setEnabled(false);
	}
	
	protected void activateShield() {
		shieldHealth = 2;
		shield.setVisible(true);
		shield.setEnabled(true);
	}
	
	public void generateShield() {
		shield.setBounds(x, y, 48, 48);
		shield.setVisible(false);
		shield.setEnabled(false);
		shield.setIcon(new ImageIcon(shieldImage));
	}
	
	private void generateLabel() {
		label.setBounds(x, y, width, height);
		label.setVisible(true);
		label.setEnabled(true);
		if(image != null) label.setIcon(new ImageIcon(image));
		else {
			label.setOpaque(true);
			label.setBackground(Color.blue);
		}
	}
}

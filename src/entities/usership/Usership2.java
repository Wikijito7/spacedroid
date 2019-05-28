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

public class Usership2 extends Usership{
	private int animNum, type;
	public Usership2(int x, int y, DataApi dataApi, int type) { //0 blue, 1 red
		super(x, y, dataApi);
		if(type == 1) image = Assets.usership2[0];
		if(type == 2) image = Assets.usership3[0];
		this.type = type;
		attackProb = 1;
		speed = 5;
		bullets = 10;
		health = 3;
		shield = new JLabel();
		generateShield();
		activateShield();
	}
	
	@Override
	public void animation() {
		if(isExploding) return;
		if(animNum > 3) animNum = 0;
		if(type == 1) image = Assets.usership2[animNum];
		if(type == 2) image = Assets.usership3[animNum];
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
}

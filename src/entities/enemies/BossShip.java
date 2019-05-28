package entities.enemies;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import entities.Entity;
import entities.shots.Shot;
import utils.Assets;
import utils.DataApi;

public class BossShip extends Entity{
	private int animNum = 0;
	public BossShip(int x, int y, int id, DataApi dataApi) {
		super(x, y, 32, 32, id, dataApi);
		image = Assets.bossship[0];
		health = 200;
		attackProb = 20;
		bullets = 5;
		speed = 1;
		generateLabel();
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

	@Override
	public Rectangle getHitbox() {
		return bounds;
	}

	@Override
	public JLabel getLabel() {
		return label;
	}

	@Override
	public int getHP() {
		return health;
	}

	@Override
	public String getName() {
		return "BossShip";
	}

	@Override
	public void animation() {
		if(animNum > 3) animNum = 0;
		if(isExploding) return;
		image = Assets.bossship[animNum];
		label.setIcon(new ImageIcon(image));
		animNum++;
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

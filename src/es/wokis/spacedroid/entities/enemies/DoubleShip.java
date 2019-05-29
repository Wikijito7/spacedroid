package es.wokis.spacedroid.entities.enemies;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import es.wokis.spacedroid.entities.Entity;
import es.wokis.spacedroid.utils.Assets;
import es.wokis.spacedroid.utils.DataApi;

public class DoubleShip extends Entity {
	private int animNum = 0;
	public DoubleShip(int x, int y, int id, DataApi dataApi) {
		super(x, y, 32, 32, id, dataApi);
		image = Assets.doubleship[0];
		health = 40;
		speed = 3;
		attackProb = 40;
		bullets = 3;
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
		// TODO Auto-generated method stub
		return label;
	}

	@Override
	public int getHP() {
		return health;
	}

	@Override
	public String getName() {
		return "Enemy DoubleShip";
	}
	
	@Override
	public void animation() {
		if(animNum > 3) animNum = 0;
		if(isExploding) return;
		image = Assets.doubleship[animNum];
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

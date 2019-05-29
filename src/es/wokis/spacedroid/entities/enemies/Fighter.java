package es.wokis.spacedroid.entities.enemies;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import es.wokis.spacedroid.entities.Entity;
import es.wokis.spacedroid.utils.Assets;
import es.wokis.spacedroid.utils.DataApi;

public class Fighter extends Entity {
	private int animNum = 0;
	public Fighter(int x, int y, int id, DataApi dataApi) {
		super(x, y, 32, 32, id, dataApi);
		image = Assets.fighter[0];
		health = 30;
		speed = 4;
		attackProb = 10;
		bullets = 6;
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
		return "Enemy Fighter";
	}
	
	@Override
	public void animation() {
		if(animNum > 3) animNum = 0;
		if(isExploding) return;
		image = Assets.fighter[animNum];
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

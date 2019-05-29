package es.wokis.spacedroid.entities.enemies;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import es.wokis.spacedroid.entities.Entity;
import es.wokis.spacedroid.utils.Assets;
import es.wokis.spacedroid.utils.DataApi;

public class YellowShip extends Entity{
	
	private int animNum = 0;
	public YellowShip(int x, int y, int id, DataApi dataApi) {
		super(x, y, 32, 32, id, dataApi);
		image = Assets.yellowship[0];
		health = 50;
		speed = 2;
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
		return "Yellow ship"; //I'm on the yellow submarine
	}
	
	@Override
	public void animation() {
		if(animNum > 2) animNum = 0;
		if(isExploding) return;
		image = Assets.yellowship[animNum];
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

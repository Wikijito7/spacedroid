package entities.shots;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import entities.Entity;
import utils.Assets;

public class PurpleShot extends Shot{
	private int animNum = 0, finalX = 0, finalY = 0;
	private Entity entity;
	public PurpleShot(int x, int y, int shooterid) {
		super(x, y, 8, 8, shooterid);
		image = Assets.purpleshot[0];
		speed = 3;
		damage = 55;
		isMoving = false;
		generateLabel();
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
	public String getName() {
		return "Purple shot";
	}
	
	@Override
	public void animation() {
		if(animNum > 3) return;
		image = Assets.purpleshot[animNum];
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

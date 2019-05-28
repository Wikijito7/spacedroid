package entities.shots;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import utils.Assets;

public class BulletShot extends Shot{
	private int animNum = 0;
	public BulletShot(int x, int y, int shooterid) {
		super(x, y, 8, 8, shooterid);
		image = Assets.bulletshot[0];
		speed = 7;
		damage = 10;
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
		return "Orange shot";
	}

	@Override
	public void animation() {
		if(animNum > 3) return;
		image = Assets.bulletshot[animNum];
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

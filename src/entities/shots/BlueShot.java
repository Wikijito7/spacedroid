package entities.shots;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import utils.Assets;

public class BlueShot extends Shot{
	private int animNum = 0;
	public BlueShot(int x, int y, int shooterid) {
		super(x, y, 8, 8, shooterid);
		image = Assets.blueshot[0];
		speed = 4;
		damage = 20;
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
		return "Blue shot";
	}

	@Override
	public void animation() {
		if(animNum > 3) return;
		image = Assets.blueshot[animNum];
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

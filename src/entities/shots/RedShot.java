package entities.shots;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import utils.Assets;

public class RedShot extends Shot{
	private int animNum = 0;
	public RedShot(int x, int y, int shooterid) {
		super(x, y, 8, 8, shooterid);
		image = Assets.redshot[0];
		speed = 7;
		damage = 20;
		generateLabel();
	}

	@Override
	public Rectangle getHitbox() {
		return label.getBounds();
	}

	@Override
	public JLabel getLabel() {
		return label;
	}

	@Override
	public String getName() {
		return "Red shot of " + shooterid;
	}
	
	@Override
	public void animation() {
		if(animNum > 3) return;
		image = Assets.redshot[animNum];
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

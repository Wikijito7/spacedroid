package utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	int width, height;
	BufferedImage image;
	public SpriteSheet(String path, int width, int height) {
		this.width = width;
		this.height = height;
		
		try {
			System.out.println(path);
			image = ImageIO.read(SpriteSheet.class.getResource(path));
		} catch (IOException e) {
			//e.printStackTrace();
			image = null;
		}
	}
	
	public BufferedImage getImage(int row, int col) {
		return image.getSubimage(col * width, row * height, width, height);
	}
	
	public BufferedImage getImage(int row, int col, int srow, int scol, int nwidth, int nheight) {
		return image.getSubimage(col * width + scol*8, row * height + srow*8, nwidth, nheight);
	}
}

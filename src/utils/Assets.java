package utils;

import java.awt.image.BufferedImage;

import entities.shots.OrangeShot;

public class Assets {
	public static BufferedImage score; //g(green)b(bullet)shop, ...
	public static BufferedImage[] explosion, fighter, cube, doubleship, bossship, usership, usership2, usership3, yellowship, greenshot, blueshot, redshot, orangeshot, purpleshot, bulletshot, health, shot, shield, missiles, shieldh; 
	public static BufferedImage[] shopitems;
	private static SpriteSheet shipss, guiss, shopss, shieldss;
	//Creo un void estatico que se ejecutará al abrir la ventana del juego para obtener todas las imagenes y tenerlas a mano para cuando se necesiten.
	public static void init() {
		//Defino dónde está y cómo es el spritesheet.
		shipss = new SpriteSheet("/ships.png", 32, 32);
		guiss = new SpriteSheet("/gui.png", 67, 24);
		shopss = new SpriteSheet("/shopitems.png", 32, 32);
		shieldss = new SpriteSheet("/shield.png", 48, 48);
		//Indico el tamaño de la matriz de la imagen en si, por ejemplo explosion, en este caso 6
		//Ships
		explosion = new BufferedImage[6];
		fighter = new BufferedImage[4];
		doubleship = new BufferedImage[4];
		cube = new BufferedImage[4];
		usership = new BufferedImage[4];
		usership2 = new BufferedImage[4];
		usership3 = new BufferedImage[4];
		yellowship = new BufferedImage[3];
		bossship = new BufferedImage[4];
		greenshot = new BufferedImage[4];
		blueshot = new BufferedImage[4];
		redshot = new BufferedImage[4];
		orangeshot = new BufferedImage[4];
		purpleshot = new BufferedImage[4];
		bulletshot = new BufferedImage[4];
		shield = new BufferedImage[4];
		
		//GUI
		health = new BufferedImage[4];
		shot = new BufferedImage[5];
		shieldh = new BufferedImage[3];
		//Shop
		shopitems = new BufferedImage[14];
		//Finalmente le doy los valores que debe tener cada uno de los componentes de dicha matriz.
		//El shipss.getImage(int, int) da la imagen exacta en esa posición. Empieza por 0 y acaba en 9 en imagenes donde las tiles son de 10x10
		explosion[0] = shipss.getImage(4, 2);
		explosion[1] = shipss.getImage(4, 3);
		explosion[2] = shipss.getImage(4, 4);
		explosion[3] = shipss.getImage(4, 5);
		explosion[4] = shipss.getImage(4, 6);
		explosion[5] = shipss.getImage(4, 7);
		
		//Userships
		//Usership1
		usership[0] = shipss.getImage(1, 6);
		usership[1] = shipss.getImage(1, 7);
		usership[2] = shipss.getImage(1, 8);
		usership[3] = shipss.getImage(1, 9);
		//Usership2
		usership2[0] = shipss.getImage(2, 0);
		usership2[1] = shipss.getImage(2, 1);
		usership2[2] = shipss.getImage(2, 2);
		usership2[3] = shipss.getImage(2, 3);
		//Usership3
		usership3[0] = shipss.getImage(2, 4);
		usership3[1] = shipss.getImage(2, 5);
		usership3[2] = shipss.getImage(2, 6);
		usership3[3] = shipss.getImage(2, 7);
		
		//Enemyships
		//Fighter
		fighter[0] = shipss.getImage(1, 2);
		fighter[1] = shipss.getImage(1, 3);
		fighter[2] = shipss.getImage(1, 4);
		fighter[3] = shipss.getImage(1, 5);
		//Cube
		cube[0] = shipss.getImage(0, 4);
		cube[1] = shipss.getImage(0, 5);
		cube[2] = shipss.getImage(0, 6);
		cube[3] = shipss.getImage(0, 7);
		//YellowShip
		yellowship[0] = shipss.getImage(2, 8);
		yellowship[1] = shipss.getImage(2, 9);
		yellowship[2] = shipss.getImage(3, 0);
		//Doubleship
		doubleship[0] = shipss.getImage(0, 8);
		doubleship[1] = shipss.getImage(0, 9);
		doubleship[2] = shipss.getImage(1, 0);
		doubleship[3] = shipss.getImage(1, 1);
		//BossShip
		bossship[0] = shipss.getImage(0, 0);
		bossship[1] = shipss.getImage(0, 1);
		bossship[2] = shipss.getImage(0, 2);
		bossship[3] = shipss.getImage(0, 3);
		//Shield
		shield[0] = shieldss.getImage(0, 0);
		shield[1] = shieldss.getImage(0, 1);
		shield[2] = shieldss.getImage(0, 2);
		shield[3] = shieldss.getImage(0, 3);
		
		//Shots
		//Blueshot
		blueshot[0] = shipss.getImage(4, 0, 0, 0, 8, 8);
		blueshot[1] = shipss.getImage(4, 0, 0, 1, 8, 8);
		blueshot[2] = shipss.getImage(4, 0, 0, 2, 8, 8);
		blueshot[3] = shipss.getImage(4, 0, 0, 3, 8, 8);
		//Greenshot
		greenshot[0] = shipss.getImage(4, 0, 1, 0, 8, 8);
		greenshot[1] = shipss.getImage(4, 0, 1, 1, 8, 8);
		greenshot[2] = shipss.getImage(4, 0, 1, 2, 8, 8);
		greenshot[3] = shipss.getImage(4, 0, 1, 3, 8, 8);
		//Redshot
		redshot[0] = shipss.getImage(4, 0, 0, 4, 8, 8);
		redshot[1] = shipss.getImage(4, 0, 0, 5, 8, 8);
		redshot[2] = shipss.getImage(4, 0, 0, 6, 8, 8);
		redshot[3] = shipss.getImage(4, 0, 0, 7, 8, 8);
		//OrageShot
		orangeshot[0] = shipss.getImage(4, 0, 2, 0, 8, 8);
		orangeshot[1] = shipss.getImage(4, 0, 2, 1, 8, 8);
		orangeshot[2] = shipss.getImage(4, 0, 2, 2, 8, 8);
		orangeshot[3] = shipss.getImage(4, 0, 2, 3, 8, 8);
		//PurpleShot
		purpleshot[0] = shipss.getImage(4, 0, 3, 0, 8, 8);
		purpleshot[1] = shipss.getImage(4, 0, 3, 1, 8, 8);
		purpleshot[2] = shipss.getImage(4, 0, 3, 2, 8, 8);
		purpleshot[3] = shipss.getImage(4, 0, 3, 3, 8, 8);
		//Bullet
		bulletshot[0] = shipss.getImage(4, 0, 1, 4, 8, 8);
		bulletshot[1] = shipss.getImage(4, 0, 1, 5, 8, 8);
		bulletshot[2] = shipss.getImage(4, 0, 1, 6, 8, 8);
		bulletshot[3] = shipss.getImage(4, 0, 1, 7, 8, 8);
		
		//GUI
		//Health
		health[0] = guiss.getImage(0, 0);
		health[1] = guiss.getImage(0, 1);
		health[2] = guiss.getImage(0, 2);
		health[3] = guiss.getImage(0, 3);
		//Shield
		shieldh[0] = guiss.getImage(3, 0);
		shieldh[1] = guiss.getImage(3, 1);
		shieldh[2] = guiss.getImage(3, 2);
		//Shot
		shot[0] = guiss.getImage(1, 0);
		shot[1] = guiss.getImage(1, 1);
		shot[2] = guiss.getImage(1, 2);
		shot[3] = guiss.getImage(1, 3);
		shot[4] = guiss.getImage(1, 4);
		//Score
		score = guiss.getImage(2, 0);
		
		//Shop
		shopitems[0] = shopss.getImage(0, 0);
		shopitems[1] = shopss.getImage(0, 1);
		shopitems[2] = shopss.getImage(0, 2);
		shopitems[3] = shopss.getImage(0, 3);
		shopitems[4] = shopss.getImage(0, 4);
		shopitems[5] = shopss.getImage(0, 5);
		shopitems[6] = shopss.getImage(0, 6);
		shopitems[7] = shopss.getImage(0, 7);
		shopitems[8] = shopss.getImage(0, 8);
		shopitems[9] = shopss.getImage(0, 9);
		shopitems[10] = shopss.getImage(1, 0);
		shopitems[11] = shopss.getImage(1, 1);
		shopitems[12] = shopss.getImage(1, 2);
		shopitems[13] = shopss.getImage(1, 3);
		
	}
}

package es.wokis.spacedroid.windows;

import java.awt.EventQueue;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import es.wokis.spacedroid.entities.Entity;
import es.wokis.spacedroid.entities.enemies.BossShip;
import es.wokis.spacedroid.entities.enemies.Cube;
import es.wokis.spacedroid.entities.enemies.DoubleShip;
import es.wokis.spacedroid.entities.enemies.Fighter;
import es.wokis.spacedroid.entities.enemies.YellowShip;
import es.wokis.spacedroid.entities.shots.BlueShot;
import es.wokis.spacedroid.entities.shots.Shot;
import es.wokis.spacedroid.entities.usership.Usership;
import es.wokis.spacedroid.entities.usership.Usership2;
import es.wokis.spacedroid.utils.Assets;
import es.wokis.spacedroid.utils.DataApi;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JProgressBar;

public class JuegoFinal extends JFrame {

	private JPanel contentPane;
	private JSeparator sep, sep2;
	private JLabel bg;
	private boolean isShooting = false, isBossSpawned = false;
	private int enemyMoving = 0, delay = 0, movedelay = 50, setMovementDelay = 500, enemyShootingDelay = 200, delayt = 100, delayexp = 200, upgShotDelay = 20000;
	private int shotType = 0, shipType;
	private int[] slots = new int[5];
	private ArrayList<Entity> entities = new ArrayList<>();
	private ArrayList<Shot> shot = new ArrayList<>(), enemyShot = new ArrayList<>();
	private Usership usership;
	private DataApi dataApi = new DataApi();
	private Clip laser, music, explosion;
	private final int usershipX = 288, usershipY = 643;
	/**
	 * @wbp.nonvisual location=21,699
	 */
	private final Timer timer = new Timer(0, (ActionListener) null);
	/**
	 * @wbp.nonvisual location=51,699
	 */
	private final Timer timer2 = new Timer(0, (ActionListener) null);
	private JLabel shopbg;
	private JButton homeBtn;
	private JButton pauseBtn;
	private JLabel optionsBg;
	private JLabel scorel;
	private JLabel lfscore;
	private JLabel bulletl;
	private JButton shopbtn1;
	private JButton shopbtn2;
	private JButton shopbtn3;
	private JButton shopbtn4;
	private JButton shopbtn5;
	private JLabel qbLabel;
	private JLabel shieldLbl;
	private JLabel otherbg;
	private JLabel healthl;
	private JProgressBar progressBar;
	private JButton playBtn;
	private JLabel lblShieldH;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Assets.init();
					JuegoFinal frame = new JuegoFinal();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JuegoFinal() {
		setIconImage(Assets.usership[1]);
		setTitle("Wiki's SpaceDroid");
		randomizeIds();
		music(Config.music);
		timer2.addActionListener(new ActionListener() {
			public synchronized void actionPerformed(ActionEvent arg0) {
				//Shot collided
				for(int b = 0; b < shot.size(); b++) {
					for(int e = 0; e < entities.size(); e++) {
						try {
							if(entities.get(e).hasCollided(shot.get(b).getLabel().getX(), shot.get(b).getLabel().getY()) && !entities.get(e).isExploding && shot.get(b).getShooter() != entities.get(e).getId() && !shot.get(b).collided) {
								if(shot.get(b) instanceof BlueShot) entities.get(e).interfereShip();
								entities.get(e).damage(shot.get(b).damage);
								shot.get(b).collided = true;
								shot.get(b).getLabel().setVisible(false);
								contentPane.remove(shot.get(b).getLabel());
								shot.remove(entities.get(b));
							}
						}catch(Exception exc) {}
						
						try {
						shot.forEach(s ->{
							if(s.collided) shot.remove(s);
						});
						}catch(Exception exc) {}
					}
					
				}
				try {
					enemyShot.forEach(b->{
						if(usership.getLabel().getBounds().contains(b.getX(), b.getY())) {
							contentPane.remove(b.getLabel());
							b.getLabel().setVisible(false);
							enemyShot.remove(b);
							usership.damage(1);
						}
					});
				}catch(Exception e){}
			}
		});

		timer2.setDelay(5);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				contentPane.requestFocus();
				Assets.init();
				timer.start();
				timer2.start();
				shipType = Config.ship;
				if(Config.ship == 0) usership = new Usership(usershipX, usershipY, dataApi);
				else {
					shotType = 5;
					usership = new Usership2(usershipX, usershipY, dataApi, Config.ship);
				}
				contentPane.add(usership.getLabel(), 0);
				contentPane.add(usership.getShieldLabel(), 0);
			}
		});
		
		timer.setDelay(10);
		timer.addActionListener(new ActionListener() {
			public synchronized void actionPerformed(ActionEvent arg0) {
				if(dataApi.round == 3) {
					progressBar.setValue(0);
					resetShop();
					activateShop();
					timer.stop();
				}
				//restamos delay
				if(delay > 0) delay -= 10;
				if(movedelay > 0) movedelay -= 10;
				if(setMovementDelay > 0) setMovementDelay -= 10;
				if(delayt > 0) delayt -= 10;
				if(delayexp > 0) delayexp -= 10;
				if(enemyShootingDelay > 0) enemyShootingDelay -= 10;
				if(upgShotDelay > 0) upgShotDelay -= 10;
				
				if(music.getFramePosition() == music.getFrameLength() && Config.isMusicOn) music(Config.music);
				
				//Reset shot
				if(upgShotDelay == 0) {
					switch(shipType){
					case 0:
						shotType = 0;
						break;
					case 1:
					case 2:
						shotType = 5;
						break;
					}
				}
				
				//Label update
				lfscore.setText(String.valueOf(dataApi.score));
				if(usership.bullets == 3) bulletl.setIcon(new ImageIcon(Assets.shot[3-shot.size()]));
				else {
					qbLabel.setVisible(true);
					qbLabel.setText("x" + String.valueOf(10-shot.size()));
					bulletl.setIcon(new ImageIcon(Assets.shot[4]));
					
				}
				if(usership.health >= 0) healthl.setIcon(new ImageIcon(Assets.health[usership.health]));
				setShopItemsIcons();
				shieldLbl.setIcon(new ImageIcon(Assets.shieldh[usership.shieldHealth]));
				if(usership.shieldHealth == 0) {
					int rs = 150*(140 - usership.regenerateshield)/1000;
					lblShieldH.setText(rs + "s");
				} else lblShieldH.setText("");
				
				//Enemies generation
				if(entities.size() == 0) {
					generateEnemies(Config.enemies);
					progressBar.setValue(33*dataApi.round);
				}
				
				//Movement
				usership.move();
				
				//Enemies movement
				if(setMovementDelay == 0 && enemyMoving < entities.size()) {
					entities.get(enemyMoving).isMoving = true;
					enemyMoving++;
					setMovementDelay = 500;
				}
				
				if(movedelay == 0) {
					entities.forEach(e->{
						if(e.isMoving) e.move();
						else if (enemyMoving >= entities.size() && !e.isMoving) e.isMoving = true;
						movedelay = 20;
					});
					shot.forEach(e->{
						e.move();
					});
					enemyShot.forEach(e-> e.move());
				}
				
				//Shooting code
				if(isShooting && delay <= 0 && shot.size() < usership.bullets) {
					isShooting = false;
					Shot s = usership.shot(-1, shotType); //0 red, 1 green, 2 blue, 3 orange, 4 purple, 5 bullet
					if(s == null) return;
					shot.add(s);
					contentPane.add(s.getLabel(),0);
					laser();
					if(shotType == 5) delay = 100;
					else delay = 200;
				} else isShooting = false;
				
				//Shot out of bounds
				try {
					shot.forEach(e->{
						if(e.getLabel().getY() < 0 || e.getX() < 0 || e.getY() > 720 || e.getX() > 640) {
							contentPane.remove(e.getLabel());
							shot.remove(e);
						}
					});
					
					enemyShot.forEach(e->{
						if(e.getLabel().getY() > contentPane.getHeight()) {
							for(int t = 0; t < entities.size(); t++) {
								if(e.getShooter() == entities.get(t).getId()) entities.get(t).currentShots--;;
							}
							contentPane.remove(e.getLabel());
							enemyShot.remove(e);
						}
					});
				}catch(Exception exc){}
				
				//Get es.wokis.spacedroid.entities that have died
				try{
					entities.forEach(e ->{
						if(e.isDead) {
							contentPane.remove(e.getLabel());
							entities.remove(e);
							explode();
						}
					});
					
					if(usership.isDead) {
						stop();
						int respuesta; // declaro una variable entera
						respuesta = JOptionPane.showConfirmDialog(null, "�Deseas reiniciar el juego?");
						if(respuesta == JOptionPane.YES_OPTION){
							dispose();
							JuegoFinal vent = new JuegoFinal();
							vent.setVisible(true);
						}
						
						else if(respuesta == JOptionPane.NO_OPTION) {
							Menu vent = new Menu();
							vent.setVisible(true);
							dispose();
						}
						
					}
				}catch(ConcurrentModificationException e){}
				
				//Enemies shooting system
				if(enemyShootingDelay == 0) {
					entities.forEach(e ->{
					Shot s = e.shot(1, 1);
					if(s == null) return;
					contentPane.add(s.getLabel(), 0);
					enemyShot.add(s);
					laser();
					});
					enemyShootingDelay = 200;
				}
				
				//Animations, kinda.
				if(delayt == 0 && Config.animations) {
					entities.forEach(e-> e.animation());
					shot.forEach(e-> e.animation());
					enemyShot.forEach(e-> e.animation());
					usership.animation();
					dataApi.addScore(1);
					delayt = 150;
				}
				
				//Exploding animation
				if(delayexp == 0) {
					entities.forEach(e->{
						if(e.isExploding) {
							e.dieAnimation();
						}
						usership.dieAnimation();
					});
				}
			}

			
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 960, 720);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				switch(arg0.getKeyCode()) {
					case KeyEvent.VK_A:
						usership.setLR_dir(1);
						break;
					case KeyEvent.VK_D:
						usership.setLR_dir(2);
						break;
					case KeyEvent.VK_W:
						usership.setUD_dir(1);
						break;
					case KeyEvent.VK_S:
						usership.setUD_dir(2);
						break;
					case KeyEvent.VK_V:
					case KeyEvent.VK_SPACE:
						isShooting = true;
						break;
				}
			}
			
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_W || arg0.getKeyCode() == KeyEvent.VK_S) usership.setUD_dir(0);
				if(arg0.getKeyCode() == KeyEvent.VK_A || arg0.getKeyCode() == KeyEvent.VK_D) usership.setLR_dir(0);
				if(arg0.getKeyCode() == KeyEvent.VK_V || arg0.getKeyCode() == KeyEvent.VK_V) isShooting = false;
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		sep = new JSeparator();
		sep.setForeground(Color.BLACK);
		sep.setOpaque(true);
		sep.setBackground(Color.BLACK);
		sep.setBounds(640, 0, 2, 720);
		contentPane.add(sep);
		
		sep2 = new JSeparator();
		sep2.setForeground(Color.BLACK);
		sep2.setOpaque(true);
		sep2.setBackground(Color.black);
		sep2.setBounds(640, 360, 320, 2);
		contentPane.add(sep2);
		
		healthl = new JLabel("");
		healthl.setBounds(652, 54, 67, 24);
		healthl.setIcon(new ImageIcon(Assets.health[3]));
		contentPane.add(healthl);
		
		bg = new JLabel("");
		bg.setIcon(new ImageIcon(JuegoFinal.class.getResource("/starfield" + Config.background + ".png")));
		bg.setBounds(0, 0, 642, 706);
		contentPane.add(bg);
		
		lblShieldH = new JLabel("");
		lblShieldH.setBounds(758, 54, 67, 24);
		contentPane.add(lblShieldH);
		
		lfscore = new JLabel("");
		lfscore.setBounds(712, 386, 58, 24);
		contentPane.add(lfscore);
		
		shieldLbl = new JLabel("");
		shieldLbl.setBounds(738, 54, 67, 24);
		shieldLbl.setIcon(new ImageIcon(Assets.shieldh[0]));
		contentPane.add(shieldLbl);
		
		scorel = new JLabel("");
		scorel.setIcon(new ImageIcon(Assets.score));
		scorel.setBounds(692, 386, 67, 24);
		contentPane.add(scorel);
		
		qbLabel = new JLabel("");
		qbLabel.setVisible(false);
		qbLabel.setBounds(679, 89, 58, 24);
		contentPane.add(qbLabel);
		
		bulletl = new JLabel("");
		bulletl.setIcon(new ImageIcon(Assets.shot[3]));
		bulletl.setBounds(652, 89, 67, 24);
		contentPane.add(bulletl);
		
		shopbtn1 = new JButton("");
		shopbtn1.setDisabledIcon(new ImageIcon(JuegoFinal.class.getResource("/disabled.png")));
		shopbtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buy(slots[0]);
				contentPane.requestFocus();
			}
		});
		
		progressBar = new JProgressBar();
		progressBar.setOpaque(true);
		progressBar.setBackground(Color.LIGHT_GRAY);
		progressBar.setForeground(Color.ORANGE);
		progressBar.setBounds(719, 370, 146, 14);
		contentPane.add(progressBar);
		shopbtn1.setOpaque(false);
		shopbtn1.setContentAreaFilled(false);
		shopbtn1.setBounds(689, 446, 32, 32);
		contentPane.add(shopbtn1);
		
		shopbtn2 = new JButton("");
		shopbtn2.setDisabledIcon(new ImageIcon(JuegoFinal.class.getResource("/disabled.png")));
		shopbtn2.setOpaque(false);
		shopbtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buy(slots[1]);
				contentPane.requestFocus();
			}
		});
		shopbtn2.setContentAreaFilled(false);
		shopbtn2.setBounds(737, 446, 32, 32);
		contentPane.add(shopbtn2);
		
		shopbtn3 = new JButton("");
		shopbtn3.setDisabledIcon(new ImageIcon(JuegoFinal.class.getResource("/disabled.png")));
		shopbtn3.setOpaque(false);
		shopbtn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buy(slots[2]);
				contentPane.requestFocus();
			}
		});
		shopbtn3.setContentAreaFilled(false);
		shopbtn3.setBounds(785, 446, 32, 32);
		contentPane.add(shopbtn3);
		
		shopbtn4 = new JButton("");
		shopbtn4.setDisabledIcon(new ImageIcon(JuegoFinal.class.getResource("/disabled.png")));
		shopbtn4.setOpaque(false);
		shopbtn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buy(slots[3]);
				contentPane.requestFocus();
			}
		});
		shopbtn4.setContentAreaFilled(false);
		shopbtn4.setBounds(833, 446, 32, 32);
		contentPane.add(shopbtn4);
		
		shopbtn5 = new JButton("");
		shopbtn5.setDisabledIcon(new ImageIcon(JuegoFinal.class.getResource("/disabled.png")));
		shopbtn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buy(slots[4]);
				contentPane.requestFocus();
			}
		});
		shopbtn5.setOpaque(false);
		shopbtn5.setContentAreaFilled(false);
		shopbtn5.setBounds(881, 446, 32, 32);
		contentPane.add(shopbtn5);
		
		shopbg = new JLabel("");
		shopbg.setIcon(new ImageIcon(JuegoFinal.class.getResource("/shopbg2.png")));
		shopbg.setBounds(662, 395, 282, 129);
		contentPane.add(shopbg);
		
		homeBtn = new JButton("");
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu pantalla1 = new Menu();
				pantalla1.setVisible(true);
				timer.stop();
				timer2.stop();
				music.close();
				dispose();
			}
		});
		homeBtn.setPressedIcon(new ImageIcon(JuegoFinal.class.getResource("smenu.png")));
		homeBtn.setBorderPainted(false);
		homeBtn.setContentAreaFilled(false); 
		homeBtn.setFocusPainted(false); 
		homeBtn.setIcon(new ImageIcon(JuegoFinal.class.getResource("menu.png")));
		homeBtn.setOpaque(false);
		homeBtn.setBounds(652, 11, 35, 35);
		contentPane.add(homeBtn);
		
		playBtn = new JButton("");
		playBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				start();
			}
		});
		playBtn.setOpaque(false);
		playBtn.setFocusPainted(false);
		playBtn.setContentAreaFilled(false);
		playBtn.setBorderPainted(false);
		playBtn.setPressedIcon(new ImageIcon(JuegoFinal.class.getResource("splay.png")));
		playBtn.setIcon(new ImageIcon(JuegoFinal.class.getResource("play.png")));
		playBtn.setBounds(690, 11, 35, 35);
		contentPane.add(playBtn);
		
		pauseBtn = new JButton("");
		pauseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pause();
			}
		});
		pauseBtn.setBorderPainted(false);
		pauseBtn.setFocusPainted(false); 
		pauseBtn.setIcon(new ImageIcon(JuegoFinal.class.getResource("pause.png")));
		pauseBtn.setPressedIcon(new ImageIcon(JuegoFinal.class.getResource("spause.png")));
		pauseBtn.setOpaque(false);
		pauseBtn.setContentAreaFilled(false);
		pauseBtn.setBounds(728, 11, 35, 35);
		contentPane.add(pauseBtn);
		
		optionsBg = new JLabel("");
		optionsBg.setIcon(new ImageIcon(JuegoFinal.class.getResource("bgbg.png")));
		optionsBg.setBounds(645, 0, 319, 362);
		contentPane.add(optionsBg);
		
		otherbg = new JLabel("");
		otherbg.setIcon(new ImageIcon(JuegoFinal.class.getResource("bgbg.png")));
		otherbg.setBounds(645, 360, 319, 330);
		contentPane.add(otherbg);
	}
	
	private void generateEnemies(int quantity) {
		isBossSpawned = false;
		for(int e = 0; e < quantity; e++) {
			int r = new Random().nextInt(6);
			switch(r) {
				case 1:
					entities.add(new Cube(10+e*40, 10, e+1, dataApi));
					break;
				case 2:
					entities.add(new YellowShip(10+e*40, 10, e+1, dataApi));
					break;
				case 3:
					entities.add(new Fighter(10+e*40, 10, e+1, dataApi));
					break;
				case 4:
					entities.add(new DoubleShip(10+e*40, 10, e+1, dataApi));
					break;
				case 5:
					if(!isBossSpawned) {
						entities.add(new BossShip(10+e*40, 10, e+1, dataApi));
						isBossSpawned = true;
					}
					break;
			}
		}
		
		shot.forEach(e-> contentPane.remove(e.getLabel())); //eliminamos las balas en caso que haya del contentPane
		shot.clear(); //limpiamos la lista de los disparos del usuario
		
		entities.forEach(e-> contentPane.add(e.getLabel(), 0)); //el 0 es para que se coloque con prioridad por encima de todo
		enemyMoving = 0; //se reinicia esta variable para que empiecen a bajar de nuevo.
		dataApi.round++;
	}
	
	private void randomizeIds() {
		int r = new Random().nextInt(14);
		for(int s = 0; s < slots.length; s++) {
			for(int x = 0; x < slots.length; x++) {
				while(slots[x] == r){
					r = new Random().nextInt(14);
				}
			}
			slots[s] = r;
		}
	}
	
	private void setShopItemsIcons() {
		shopbtn1.setIcon(new ImageIcon(Assets.shopitems[slots[0]]));
		shopbtn2.setIcon(new ImageIcon(Assets.shopitems[slots[1]]));
		shopbtn3.setIcon(new ImageIcon(Assets.shopitems[slots[2]]));
		shopbtn4.setIcon(new ImageIcon(Assets.shopitems[slots[3]]));
		shopbtn5.setIcon(new ImageIcon(Assets.shopitems[slots[4]]));
	}
	
	private void buy(int id) {
		switch(id) {
			case 0:
			case 3:
				if(dataApi.has(1500) && usership.health < 3) {
					usership.health++;
					dataApi.removeScore(1500);
				}
				break;
			case 1:
				break;
			case 2:
				if(dataApi.has(1500)) {
					usership.speedModifier++;
					dataApi.removeScore(1500);
				}
				break;
			case 4:
				if(dataApi.has(3000)) { //0 red, 1 green, 2 blue, 3 orange, 4 purple, 5 bullet
					shotType = 2;
					dataApi.removeScore(3000);
					upgShotDelay = 120000;
				}
				break;
			case 5:
				if(dataApi.has(3000)) { //0 red, 1 green, 2 blue, 3 orange, 4 purple, 5 bullet
					shotType = 1;
					dataApi.removeScore(3000);
					upgShotDelay = 121000;
				}
				break;
			case 6:
				if(dataApi.has(3000)) { //0 red, 1 green, 2 blue, 3 orange, 4 purple, 5 bullet
					shotType = 3;
					dataApi.removeScore(3000);
					upgShotDelay = 125000;
				}
				break;
			case 7:
				if(dataApi.has(3000)) { //0 red, 1 green, 2 blue, 3 orange, 4 purple, 5 bullet
					shotType = 4;
					dataApi.removeScore(3000);
					upgShotDelay = 115000;
				}
				break;
			case 8:
				if(dataApi.has(3000)) { //0 red, 1 green, 2 blue, 3 orange, 4 purple, 5 bullet
					shotType = 0;
					dataApi.removeScore(3000);
					upgShotDelay = 120000;
				}
				break;
			case 9:
				if(dataApi.has(5000)) { 
					usership.getLabel().setVisible(false);
					usership.destroyShield();
					contentPane.remove(usership.getLabel());
					usership = new Usership(usershipX, usershipY, dataApi);
					contentPane.add(usership.getLabel(), 0);
					dataApi.removeScore(5000);
				}
				break;
			case 10:
				if(dataApi.has(5000)) {
					usership.getLabel().setVisible(false);
					usership.destroyShield();
					contentPane.remove(usership.getLabel());
					usership = new Usership2(usershipX, usershipY, dataApi, 1);
					contentPane.add(usership.getLabel(), 0);
					dataApi.removeScore(5000);
				}
				break;
			case 11:
				if(dataApi.has(5000)) {
					usership.getLabel().setVisible(false);
					usership.destroyShield();
					contentPane.remove(usership.getLabel());
					usership = new Usership2(usershipX, usershipY, dataApi, 2);
					contentPane.add(usership.getLabel(), 0);
					dataApi.removeScore(5000);
				}
				break;
			case 12:
				if(dataApi.has(3000)) { //0 red, 1 green, 2 blue, 3 orange, 4 purple, 5 bullet
					shotType = 5;
					dataApi.removeScore(3000);
					upgShotDelay = 130000;
				}
				break;
			case 13:
				break;

		}
		
		lfscore.setText(String.valueOf(dataApi.score));
	}
	
	private void stop() {
		explode();
		music.stop();
		timer.stop();
		timer2.stop();
	}
	
	private void laser() {
		try{
			laser = AudioSystem.getClip();
			if(!Config.isSoundOn) return;
			laser.open(AudioSystem.getAudioInputStream(JuegoFinal.class.getResource("/sounds/game-s/laser" + (new Random().nextInt(3)+1) + ".wav")));
			laser.start();
		}catch(Exception ex){}
	}
	
	private void music(int gamemusic) {
		try{
			music = AudioSystem.getClip();
			if(!Config.isMusicOn) return;
			switch(gamemusic) {
				case 0:
					music.open(AudioSystem.getAudioInputStream(JuegoFinal.class.getResource("/sounds/game-m/gs" + (new Random().nextInt(3)+1) + ".wav")));
					break;
				case 1:
				case 2:
				case 3:
					music.open(AudioSystem.getAudioInputStream(JuegoFinal.class.getResource("/sounds/game-m/gs" + gamemusic + ".wav")));
					break;
			}
			
			music.start();
		}catch(Exception ex){}
	}
	
	private void explode() {
		try {
			explosion = AudioSystem.getClip();
			if(!Config.isSoundOn) return;
			explosion.open(AudioSystem.getAudioInputStream(JuegoFinal.class.getResource("/sounds/game-s/explosion" + (new Random().nextInt(2)+1) + ".wav")));
			explosion.start();
		}catch(Exception e) {}
	}
	
	private void activateShop() {
		shopbtn1.setEnabled(true);
		shopbtn2.setEnabled(true);
		shopbtn3.setEnabled(true);
		shopbtn4.setEnabled(true);
		shopbtn5.setEnabled(true);
	}
	
	private void desactivateShop() {
		shopbtn1.setEnabled(false);
		shopbtn2.setEnabled(false);
		shopbtn3.setEnabled(false);
		shopbtn4.setEnabled(false);
		shopbtn5.setEnabled(false);
	}
	
	private void resetShop() {
		randomizeIds();
		setShopItemsIcons();
		desactivateShop();
		dataApi.round = 0;
	}
	
	private void pause() {
		timer.stop();
		timer2.stop();
		music.stop();
		contentPane.requestFocus();
	}
	
	private void start() {
		timer.start();
		timer2.start();
		music.start();
		desactivateShop();
		contentPane.requestFocus();
	}
}

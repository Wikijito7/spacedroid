
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Entity;
import entities.enemies.Cube;
import entities.enemies.DoubleShip;
import entities.enemies.Fighter;
import entities.enemies.YellowShip;
import entities.shots.Shot;
import entities.usership.Usership;
import entities.usership.Usership2;
import utils.Assets;

import javax.swing.JLabel;
import java.awt.Color;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.io.IOException;
import javax.swing.JInternalFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;

public class Menu extends JFrame {

	private JPanel contentPane;
	private JLabel logo;
	private JLabel bg;
	public static Clip clip;
	private JInternalFrame internalFrame;
	private JScrollPane scrollPane;
	private JTextArea txtrJuegoHechoPor;
	private JButton button;
	private Usership usership;
	private Entity entity;
	private Shot shot;
	/**
	 * @wbp.nonvisual location=2,359
	 */
	private final Timer timer = new Timer(0, (ActionListener) null);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Assets.init();
					Menu frame = new Menu();
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
	public Menu() {
		timer.setInitialDelay(5000);
		timer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(usership == null) {
					switch(new Random().nextInt(3)) {
					case 0:
						usership = new Usership(100, 380, null);
						break;
					case 1:
						usership = new Usership2(100, 380, null, 1);
						break;
					case 2:
						usership = new Usership2(100, 380, null, 2);
						break;
					}
					contentPane.add(usership.getLabel(), 0);
				}
				
				if(usership.getY() < 0) {
					usership.getLabel().setVisible(false);
					contentPane.remove(usership.getLabel());
					usership = null;
				} else {
					usership.animation();
					usership.menuMove();
				}
				
				int r = new Random().nextInt(40);
				if(r == 0 && shot == null && usership != null) {
					shot = usership.shot(-1, new Random().nextInt(5));
					if(shot != null)
					contentPane.add(shot.getLabel(), 0);
				}
				if(shot != null) {
					shot.animation();
					shot.move();
					if(shot.getY() < 0) {
						shot.getLabel().setVisible(false);
						contentPane.remove(shot.getLabel());
						shot = null;
					}
				}
				
				if(entity == null) generateEnemies(1);
				if(entity != null) {
					entity.move();
					entity.animation();
					if(entity.getY() > 375) {
						entity.getLabel().setVisible(false);
						contentPane.remove(entity.getLabel());
						entity = null;
					}
				}
				
				if(clip != null && clip.getFramePosition() == clip.getFrameLength()) {
					clip.close();
					try {
						clip.open(AudioSystem.getAudioInputStream(JuegoFinal.class.getResource("/sounds/game-m/mainmenu.wav")));
					} catch (Exception e1) {
					}
					clip.start();
				}
			}
		});
		timer.setDelay(100);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				try {
					if(clip != null) return;
					clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(JuegoFinal.class.getResource("/sounds/game-m/mainmenu.wav")));
					clip.start();
					timer.start();
				} catch (Exception e) {}
				
			}
		});
		setIconImage(Assets.usership[1]);
		setResizable(false);
		setTitle("Wiki's SpaceDroid");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 640, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		internalFrame = new JInternalFrame("Credits");
		internalFrame.setClosable(true);
		internalFrame.setBounds(112, -3, 425, 345);
		contentPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 399, 315);
		internalFrame.getContentPane().add(scrollPane);
		
		txtrJuegoHechoPor = new JTextArea();
		txtrJuegoHechoPor.setEditable(false);
		txtrJuegoHechoPor.setText("Juego hecho por Cristian Andrades.\r\nBeta testers: \r\nIv\u00E1n \"Cadox\" Rica\r\nAlexMLuis\r\nirlwoolf666\r\n\r\nDise\u00F1o gr\u00E1fico:\r\nCristian Andrades\r\nDise\u00F1adores de itch.io \r\n\r\nSonido: Sonidos de itch.io \r\n\r\nM\u00FAsicas: Power Rangers versi\u00F3n 8 bits. \"8 Bit Universe\". \r\nM\u00FAsica de intro de 8 bits de Paladins.  \r\n\r\nHecho en Java. \r\nWiki's Space Droid \u00A9 2018 Cristian \"Wiki\" Andrades .\r\nThis code is only used with educational proposes.\r\nWiki's Space Droid will not be sold as a product.");
		scrollPane.setViewportView(txtrJuegoHechoPor);
		
		logo = new JLabel("");
		logo.setIcon(new ImageIcon(Menu.class.getResource("logospacedroid.png")));
		logo.setBackground(Color.RED);
		logo.setBounds(256, 10, 128, 64);
		contentPane.add(logo);
		
		JButton btnPlay = new JButton("");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Config pantalla2 = new Config();
				pantalla2.setVisible(true);
				dispose();
			}
		});
		btnPlay.setOpaque(false);
		btnPlay.setIcon(new ImageIcon(Menu.class.getResource("playb.png")));
		btnPlay.setBounds(234, 95, 172, 24);
		contentPane.add(btnPlay);
		
		JButton btnInst = new JButton("");
		btnInst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Instruc instruc = new Instruc();
				instruc.setVisible(true);
				clip.stop();
				clip = null;
				dispose();
			}
		});
		btnInst.setOpaque(false);
		btnInst.setIcon(new ImageIcon(Menu.class.getResource("instb.png")));
		btnInst.setBounds(234, 131, 172, 24);
		contentPane.add(btnInst);
		
		button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				internalFrame.setVisible(true);
			}
		});
		button.setIcon(new ImageIcon(Menu.class.getResource("creditsb.png")));
		button.setOpaque(false);
		button.setBounds(234, 166, 172, 24);
		contentPane.add(button);
		
		JButton exitBtn = new JButton("");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});
		exitBtn.setOpaque(false);
		exitBtn.setIcon(new ImageIcon(Menu.class.getResource("exit.png")));
		exitBtn.setBounds(234, 201, 172, 24);
		contentPane.add(exitBtn);
		
		bg = new JLabel("");
		bg.setBounds(0, 0, 634, 342);
		bg.setIcon(new ImageIcon(Menu.class.getResource("/bmm.png")));
		contentPane.add(bg);
	}
	
	private void generateEnemies(int quantity) {
		for(int e = 0; e < quantity; e++) {
			int r = new Random().nextInt(5);
			switch(r) {
				case 1:
					entity = new Cube(600, -20, 1, null);
					break;
				case 2:
					entity = new YellowShip(600, -20, 1, null);
					break;
				case 3:
					entity = new Fighter(600, -20, 1, null);
					break;
				case 4:
					entity = new DoubleShip(600, -20, 1, null);
					break;
			}
			if(entity != null) contentPane.add(entity.getLabel(), 0);
		}
	}
}

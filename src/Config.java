
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.Assets;

import javax.swing.JLabel;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JRadioButton;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Config extends JFrame {

	private JPanel contentPane;
	public static int background = 0, music = 0, ship = -1, enemies = 5;
	public static boolean isMusicOn = false, isSoundOn = false, animations = false;
	private final ButtonGroup fondoGroup = new ButtonGroup();
	private final ButtonGroup naveGroup = new ButtonGroup();
	private JLabel navel, lblM, lblS, noselectedLbl;
	private JComboBox comboBox;
	private JButton btnTest;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Assets.init();
					Config frame = new Config();
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
	public Config() {
		setIconImage(Assets.usership[1]);
		setResizable(false);
		setTitle("Wiki's SpaceDroid");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 640, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Fondo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(29, 152, 121, 124);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JRadioButton bgr1 = new JRadioButton("Fondo 1");
		bgr1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				background = 1;
			}
		});
		bgr1.setBounds(6, 16, 109, 23);
		panel.add(bgr1);
		fondoGroup.add(bgr1);
		
		JRadioButton bgr2 = new JRadioButton("Fondo 2");
		bgr2.setBounds(6, 42, 109, 23);
		bgr2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				background = 2;
			}
		});
		panel.add(bgr2);
		fondoGroup.add(bgr2);
		
		JRadioButton bgr3 = new JRadioButton("Fondo 3");
		bgr3.setBounds(6, 68, 109, 23);
		bgr3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				background = 3;
			}
		});
		panel.add(bgr3);
		fondoGroup.add(bgr3);
		
		JRadioButton bgr4 = new JRadioButton("Fondo 4");
		bgr4.setBounds(6, 94, 109, 23);
		bgr4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				background = 4;
			}
		});
		panel.add(bgr4);
		fondoGroup.add(bgr4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Tipo de nave", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(29, 43, 121, 98);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JRadioButton naver1 = new JRadioButton("Nave 1");
		naver1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				navel.setIcon(new ImageIcon(Assets.usership[0]));
				ship = 0;
			}
		});
		naver1.setBounds(6, 16, 109, 23);
		panel_1.add(naver1);
		naveGroup.add(naver1);
		
		JRadioButton naver2 = new JRadioButton("Nave 2");
		naver2.setBounds(6, 42, 109, 23);
		naver2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				navel.setIcon(new ImageIcon(Assets.usership2[0]));
				ship = 1;
			}
		});
		panel_1.add(naver2);
		naveGroup.add(naver2);
		
		JRadioButton naver3 = new JRadioButton("Nave 3");
		naver3.setBounds(6, 68, 109, 23);
		naver3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				navel.setIcon(new ImageIcon(Assets.usership3[0]));
				ship = 2;
			}
		});
		panel_1.add(naver3);
		naveGroup.add(naver3);
		
		navel = new JLabel("");
		navel.setBounds(160, 50, 32, 32);
		contentPane.add(navel);
		
		JToggleButton soundTb = new JToggleButton("Sonido?");
		soundTb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblS.setText(String.valueOf(soundTb.isSelected()));
				isSoundOn = soundTb.isSelected();
			}
		});
		soundTb.setToolTipText("\u00BFActivar el sonido?");
		soundTb.setBounds(202, 50, 121, 23);
		contentPane.add(soundTb);
		
		JToggleButton musicTb = new JToggleButton("M\u00FAsica?");
		musicTb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				comboBox.setEnabled(musicTb.isSelected());
				btnTest.setEnabled(musicTb.isSelected());
				lblM.setText(String.valueOf(musicTb.isSelected()));
				isMusicOn = musicTb.isSelected();
			}
		});
		musicTb.setToolTipText("\u00BFActivar la música?");
		musicTb.setBounds(202, 84, 121, 23);
		contentPane.add(musicTb);
		
		JSpinner spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				System.out.println(spinner.getValue());
				enemies = (int) spinner.getValue();
			}
		});
		spinner.setModel(new SpinnerNumberModel(5, 5, 13, 2));
		spinner.setBounds(294, 149, 34, 20);
		contentPane.add(spinner);
		
		JLabel lblNDeEnemigos = new JLabel("N\u00BA de enemigos");
		lblNDeEnemigos.setBounds(202, 151, 90, 17);
		contentPane.add(lblNDeEnemigos);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				music = comboBox.getSelectedIndex();
			}
		});
		comboBox.setEnabled(false);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Aleatorio", "M\u00FAsica 1", "M\u00FAsica 2", "M\u00FAsica 3"}));
		comboBox.setToolTipText("Selecciona qu\u00E9 canci\u00F3n se reproduce de fondo, si seleccionas aleatorio se eligir\u00E1 uno de los 3 de manera aleatoria.");
		comboBox.setBounds(252, 179, 90, 20);
		contentPane.add(comboBox);
		
		JLabel lblMsica = new JLabel("M\u00FAsica:");
		lblMsica.setBounds(202, 179, 46, 20);
		contentPane.add(lblMsica);
		
		btnTest = new JButton("Test");
		btnTest.setEnabled(false);
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clip sonido1;
				if(comboBox.getSelectedIndex() == 0) return;
				try{
					sonido1 = AudioSystem.getClip();
					sonido1.open(AudioSystem.getAudioInputStream(Config.class.getResource("/sounds/game-m/gs" + comboBox.getSelectedIndex() + ".wav")));
					sonido1.start();
					Thread.sleep(5000); 
					sonido1.close();
				}
				catch(Exception ex){}
				music = comboBox.getSelectedIndex();
			}
		});
		btnTest.setBounds(262, 202, 62, 23);
		contentPane.add(btnTest);
		
		JCheckBox chckbxAnim = new JCheckBox("Animaciones?");
		chckbxAnim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				animations = chckbxAnim.isSelected();
			}
		});
		chckbxAnim.setToolTipText("Si el modo Insane est\u00E1 activado, la dificultad del juego subir\u00E1 progresivamente, los objetos costar\u00E1n m\u00E1s y los enemigos tendr\u00E1n m\u00E1s vida");
		chckbxAnim.setBounds(202, 232, 121, 23);
		contentPane.add(chckbxAnim);
		
		lblS = new JLabel("false");
		lblS.setBounds(333, 50, 46, 23);
		contentPane.add(lblS);
		
		lblM = new JLabel("false");
		lblM.setBounds(333, 84, 46, 23);
		contentPane.add(lblM);
		
		JButton btnPlay = new JButton("");
		btnPlay.setIcon(new ImageIcon(Config.class.getResource("/mplay.png")));
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(background == 0 || ship == -1) {
					noselectedLbl.setText("<html>Te has olvidado<br> seleccionar algo importante.. (fondo o nave)</html>");
					return;
				}
				JuegoFinal juegoFinal = new JuegoFinal();
				juegoFinal.setVisible(true);
				Menu.clip.close();
				Menu.clip = null;
				dispose();
			}
		});
		btnPlay.setBounds(448, 39, 67, 24);
		contentPane.add(btnPlay);
		
		JButton btnBack = new JButton("");
		btnBack.setIcon(new ImageIcon(Config.class.getResource("/mback.png")));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu();
				menu.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(448, 84, 67, 24);
		contentPane.add(btnBack);
		
		noselectedLbl = new JLabel("");
		noselectedLbl.setForeground(Color.RED);
		noselectedLbl.setBounds(448, 118, 130, 63);
		contentPane.add(noselectedLbl);
		
		JLabel bg = new JLabel("");
		bg.setBounds(0, 0, 634, 291);
		bg.setIcon(new ImageIcon(Config.class.getResource("/configbg.png")));
		contentPane.add(bg);
	}
}

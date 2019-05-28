
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
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import java.awt.SystemColor;

public class Instruc extends JFrame {

	private JPanel contentPane;
	public static int background = 0, music = 0, ship = -1, enemies = 5;
	public static boolean isMusicOn = false, isSoundOn = false, animations = false;
	private final ButtonGroup fondoGroup = new ButtonGroup();
	private final ButtonGroup naveGroup = new ButtonGroup();
	private JMenuBar menuBar;
	private JMenu mnSelector;
	private JMenuItem mntmControles;
	private JTextArea txt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Assets.init();
					Instruc frame = new Instruc();
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
	public Instruc() {
		setIconImage(Assets.usership[1]);
		setResizable(false);
		setTitle("Wiki's SpaceDroid");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 640, 341);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnSelector = new JMenu("Selector");
		menuBar.add(mnSelector);
		
		JMenuItem mntmInicio = new JMenuItem("Inicio");
		mntmInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txt.setText("Bienvenido a las instrucciones.\r\n\r\nPara seleccionar una pantalla de ayuda, haz click en la pesta\u00F1a de Selector de arriba a la izquierda y,\r\nposteriormente, a la parte que quieras consultar.\r\n\r\nFinalmente, para volver al inicio haz click al inicio.\r\nPara volver al men\u00FA, haz click en el boton de back.");
			}
		});
		mnSelector.add(mntmInicio);
		
		mntmControles = new JMenuItem("Controles");
		mntmControles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txt.setText("Controles\r\n\r\nW para subir\r\n\r\nA para ir a la izquierda\r\n\r\nS para bajar\r\n\r\nD para ir a la derecha\r\n\r\nEspacio o V para disparar\r\n\r\nQ para activar el escudo\r\n\r\nE para lanzar los misiles");
			}
		});
		mnSelector.add(mntmControles);
		
		JMenuItem mntmJuego = new JMenuItem("Juego");
		mntmJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.setText("Juego\r\n\r\nEl juego está dividido en minipartidas de tres rondas, posteriormente el juego se pausa y se activa la tienda.\r\n\r\nCada vez que matas a un enemigo recibes 50 puntos, por cada 150 ms se otorga un punto y,\r\nen caso que un enemigo llegue abajo del todo y se reinicie, se pierden 25 puntos.\r\n\r\nCada vez que se activa la tienda, los objetos de ésta se reinician, apareciendo nuevas opciones.");
			}
		});
		mnSelector.add(mntmJuego);
		
		JMenuItem mntmNaves = new JMenuItem("Naves");
		mntmNaves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.setText("Naves\r\n\r\n Existen 5 tipos de naves enemigas, cada una tiene una vida diferente y una velocidad diferente.\r\n\r\nAdemás, existen tres naves aliadas, la primera nave es más lenta pero empieza con el laser y, por otro lado, las\r\notras dos naves son más pequeñas y ligeras pero no empiezan con los láseres de serie.");
			}
		});
		mnSelector.add(mntmNaves);
		
		JMenuItem mntmTienda = new JMenuItem("Tienda");
		mntmTienda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.setText("Tienda\r\n\r\nLos objetos que dan vida, el corazón y el martillo, cuestan 1500 puntos.\r\n\r\nLos disparos cuestan 3000 puntos. Éstos no duran para siempre, así que juega bien con tus puntos.\r\n\r\nLas naves valen 5000 puntos y es un cambio permanente.");
			}
		});
		mnSelector.add(mntmTienda);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton backBtn = new JButton("");
		backBtn.setIcon(new ImageIcon(Config.class.getResource("/mback.png")));
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu();
				menu.setVisible(true);
				dispose();
			}
		});
		backBtn.setBounds(554, 256, 67, 24);
		contentPane.add(backBtn);
		
		txt = new JTextArea();
		txt.setEditable(false);
		txt.setText("Bienvenido a las instrucciones.\r\n\r\nPara seleccionar una pantalla de ayuda, haz click en la pesta\u00F1a de Selector de arriba a la izquierda y,\r\nposteriormente, a la parte que quieras consultar.\r\n\r\nFinalmente, para volver al inicio haz click al inicio.\r\nPara volver al men\u00FA, haz click en el boton de back.");
		txt.setBackground(SystemColor.scrollbar);
		txt.setBounds(7, 11, 614, 269);
		contentPane.add(txt);
		
		JLabel bg = new JLabel("");
		bg.setBounds(0, 0, 634, 291);
		bg.setIcon(new ImageIcon(Instruc.class.getResource("/configbg.png")));
		contentPane.add(bg);
	}
}

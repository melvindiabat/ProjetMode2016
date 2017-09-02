package vue;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import bdd.GUIFrame;
import modele.FaceModifier;
import modele.FichierReader;
import modele.Modele;
/**
 * Classe representant la fenetre principale de l'application 3D
 * @author Beuns Vianney
 *
 */
@SuppressWarnings("serial")
public class Fenetre extends JFrame {

	private static Fenetre instance = null; // dp : singleton
	
	public static Fenetre getInstance(){
		if(instance == null){
			instance = new Fenetre();
		}
		return instance; }

	private JPanel options;
	private Modele modele;
	//private Controleur controler;
	private PanelModele figure;
	private JPanel settings;
	private Container contenu;
	private JButton zoom;
	private JButton lilRotatD;
	private JButton dezoom;
	private JButton lilRotatG;
	private JButton rotationD;
	private JButton rotationG;
	private JButton translationD;
	private JButton translationG;
	private JButton translationH;
	private JButton translationB;
	private JButton rotationH;
	private JButton rotationB;
	private JButton bdd;
	private static JCheckBox faces;
	private static JCheckBox segments;
	private static JCheckBox eclairage;
	private static JCheckBox ombre;
	//TODO : ajouter options Ã©clairage et ombres


	private Fenetre() {
		contenu=getContentPane();
		contenu.setLayout(new BorderLayout());

		try {
			modele=new Modele(FichierReader.loadPoint(),FichierReader.loadFaces());
		} catch (FileNotFoundException e1) {
			System.exit(0);
		}
		figure = new PanelModele(modele, this);
		modele.addObserver( figure);
		options = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		settings = new JPanel();
		c.fill = GridBagConstraints.HORIZONTAL;		//Remplis horizontalement (fait propre)
		c.insets = new Insets(2, 5, 0, 5); 			//External padding

		faces = new JCheckBox("Faces");
		segments = new JCheckBox("Segments");
		eclairage = new JCheckBox("Eclairage");
		ombre = new JCheckBox("Ombre");
		faces.setSelected(true);
		segments.setSelected(true);
		bdd = new JButton("Base de données");

		contenu.add(options, BorderLayout.NORTH);
		contenu.add(figure, BorderLayout.CENTER);
		contenu.add(settings, BorderLayout.SOUTH);

		settings.setLayout(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		settings.add(faces, c);
		c.gridx = 1;
		settings.add(segments,c );
		c.gridx = 2;
		settings.add(eclairage, c);
		c.gridx = 3;
		settings.add(ombre, c);
		c.gridx = 4;
		c.insets = new Insets(2, 250, 0, 5);
		settings.add(bdd, c);
		c.insets = new Insets(2, 5, 0, 5); 			//External padding

		
		lilRotatD = new JButton("Lil' Rotation 10° D");
		lilRotatG = new JButton("Lil' Rotation 10° G");
		rotationH = new JButton("Lil' Rotation 10° H");
		rotationB = new JButton("Lil' Rotation 10° B");
		zoom = new JButton("+");
		dezoom = new JButton("-");
		rotationD = new JButton("Rotation 90° droite");
		rotationG = new JButton("Rotation 90° gauche");
		translationD = new JButton("→");
		translationG = new JButton("←");
		translationH = new JButton("↑");
		translationB = new JButton("↓");

		c.gridx = 0;			//Colonne numï¾ƒï½©ro 0
		c.gridy = 0;			//Colonne numï¾ƒï½©ro 0
		options.add(zoom, c);

		c.gridy = 1;
		options.add(dezoom, c);

		c.gridx = 2;
		c.gridy = 0;
		options.add(rotationD, c);

		c.gridx = 1;
		options.add(rotationG, c);

		c.gridy = 1;
		options.add(lilRotatG, c);

		c.gridx = 2;
		options.add(lilRotatD,c );

		c.gridx = 3;
		c.gridy = 0;
		options.add(rotationH,c);

		c.gridy = 1;
		options.add(rotationB,c);

		c.gridx = 4;
		c.gridy = 0;
		options.add(translationG,c);

		c.gridy = 1;
		options.add(translationH,c);

		c.gridx = 5;
		c.gridy = 0;
		options.add(translationD,c);

		c.gridy = 1;
		options.add(translationB,c);
		
		bdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new GUIFrame().setLocationRelativeTo(contenu);
			}
		});

		lilRotatD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FaceModifier.rotat10D(modele);
			}
		});

		lilRotatG.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FaceModifier.rotat10G((Modele) modele);

			}
		});

		rotationG.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FaceModifier.rotat90G(modele);

			}
		});

		rotationH.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FaceModifier.rotat10H(modele);

			}
		});

		rotationB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FaceModifier.rotat10B(modele);
			}
		}); 

		dezoom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FaceModifier.zoom065(modele);

			}
		});

		zoom.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				FaceModifier.zoom15(modele);

			}

		});

		rotationD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FaceModifier.rotat90D(modele);

			}
		});

		translationD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FaceModifier.transD(modele);

			}
		});

		translationG.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FaceModifier.transG(modele);

			}
		});
		translationH.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FaceModifier.transH(modele);

			}
		});
		translationB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FaceModifier.transB(modele);

			}
		});       

		faces.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		});

		segments.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		});

		eclairage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		});
		ombre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		});

		setTitle("Modele");
		setPreferredSize(new Dimension(1000, 700));
		setLocation(100,100);
		pack();
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static boolean getFace(){
		return faces.isSelected();
	}

	public static boolean getSegment(){
		return segments.isSelected();
	}
	
	public static boolean getEclairage(){
		return eclairage.isSelected();
	}
	
	public static boolean getOmbre(){
		return ombre.isSelected();
	}

}
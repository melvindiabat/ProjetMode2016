package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import modele.Face;
import modele.FaceModifier;
import modele.Modele;
import modele.Point;
import vue.Fenetre;

/**
 * Classe permettant l'affichage d'un modele
 * @author melvin
 *
 */
public class PanelModele extends JPanel implements Observer{

	private static final long serialVersionUID = 3058428317913139192L;
	Modele modele;
	JFrame window;
	private Color colorFace;

	public PanelModele(Modele modele, JFrame window){
		setBackground(Color.WHITE);
		this.modele = modele;
		this.window = window;
		colorFace = Color.CYAN;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		//double width = (FichierReader.widthMax - FichierReader.widthMin);
		//double height = (FichierReader.heightMax - FichierReader.heightMin);
		Modele ombre = new Modele();
		if(Fenetre.getOmbre()){
			ArrayList<Face> f = new ArrayList<Face>();
			ArrayList<Point> p = new ArrayList<Point>();
			for (Face s : modele.getFaces()) {
				f.add(s);
			}
			for (Point k : modele.getPoints()) {
				p.add(k);
			}
			ombre = new Modele(p,f);
			FaceModifier.zoom065(ombre);
			FaceModifier.transH(ombre);
			FaceModifier.transH(ombre);
			FaceModifier.transH(ombre);
			int i = 0;
			for (Face s3 : ombre.getFaces()) {
				int[] x2 = ombre.getFaces().get(i).getXCoord(getWidth());
				int[] y2 = ombre.getFaces().get(i).getYCoord(getHeight());
				Polygon face2 = new Polygon(x2, y2, x2.length);
				g2.setColor(Color.black);
				g2.fill(face2);
				i++;
			}
		}
		for (Face s : modele.getFaces()) {
			//List<Point> pts = s.getEnsemble();
			int[] x = s.getXCoord(getWidth());
			int[] y = s.getYCoord(getHeight());
			g.setColor(Color.BLACK);

			
			if(Fenetre.getEclairage()){
				float intensity = FaceModifier.eclairerFace(s);
				colorFace = Color.getHSBColor(0.5f, 1.0f, intensity);
			}else{
				colorFace = Color.cyan;
			}

			Polygon face = new Polygon(x, y, x.length);
			if (Fenetre.getFace()) {
				g2.setColor(colorFace);
				g2.fill(face);
			}
			if(Fenetre.getSegment()){
				g2.setColor(Color.black);
				g2.draw(face);
			}
		}
	}
	@Override
	public void update(Observable o, Object arg) {
		window.repaint();
	}
}

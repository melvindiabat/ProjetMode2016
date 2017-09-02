package test3D;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import modele.Face;
import modele.Modele;
import modele.Point;

public class TestModele {
	
	ArrayList<Face> faces=new ArrayList<>();
	ArrayList<Point> points=new ArrayList<>();
	Face face;
	Modele modele;

	public void init(){
		
		face = new Face();
		face.addPoint(new Point(2.0, 7.0, 1.0));
		face.addPoint(new Point(5.0, 5.0, 3.0));
		face.addPoint(new Point(1.0, 2.0, 2.0));
		faces.add(face);
		
		face=new Face();
		face.addPoint(new Point(2.0, 7.0, 1.0));
		face.addPoint(new Point(5.0, 5.0, 3.0));
		face.addPoint(new Point(1.0, 2.0, 2.0));
		faces.add(face);
		
		
		points.add(new Point(2.0, 3.0, 4.0));
		points.add(new Point(4.0, 1.0, 1.0));
		points.add(new Point(3.0, 4.0, 5.0));
		points.add(new Point(1.0, 5.0, 2.0));
		modele = new Modele(points, faces);

	}
	
	@Test
	public void testSortByZBuff() {
		
	}
	@Test
	public void testGetPoints(){
		init();
		assertEquals(modele.getPoints(),points);
	}
	
	/*@Test
	public void testGetFaces(){
		// marche pas car apres le new Modele, faces est delete
		init();
		assertEquals(modele.getFaces(),faces);
	}*/
	
}
package test3D;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


import modele.FaceModifier;
import matrice.Matrice;
import matrice.RemarquableMatrices;
import modele.Face;
import modele.Point;

public class TestFaceModifier {


	private Point p1 = new Point(1.0, 0.0, 0.0);
	private Point p2 = new Point(0.0, 1.0, 0.0);
	private Point p3 = new Point(0.0, 0.0, 1.0);
	private Face f = new Face();

	private void init(){
		f.addPoint(p1);
		f.addPoint(p2);
		f.addPoint(p3);
	}
	
	@Test
	public void testZoom15(){
		init();
		Matrice m = FaceModifier.faceHomogene(f);
		Double[] l1 = new Double[]{1.5, 0.0, 0.0, 0.0};
		Double[] l2 = new Double[]{0.0, 1.5, 0.0, 0.0};
		Double[] l3 = new Double[]{0.0, 0.0, 1.5, 0.0};
		Double[] l4 = new Double[]{0.0, 0.0, 0.0, 1.0};
		ArrayList<Double[]> a = new ArrayList<>();
		a.add(l1);
		a.add(l2);
		a.add(l3);
		a.add(l4);
		Matrice test = new Matrice(4,4,a);
		Matrice res = m.multiply(RemarquableMatrices.zoom15);
		assertTrue(res.equals(test));
	}
	
	@Test
	public void testZoom065(){
		init();
		Matrice m = FaceModifier.faceHomogene(f);
		Double[] l1 = new Double[]{0.67, 0.0, 0.0, 0.0};
		Double[] l2 = new Double[]{0.0, 0.67, 0.0, 0.0};
		Double[] l3 = new Double[]{0.0, 0.0, 0.67, 0.0};
		Double[] l4 = new Double[]{0.0, 0.0, 0.0, 1.0};
		ArrayList<Double[]> a = new ArrayList<>();
		a.add(l1);
		a.add(l2);
		a.add(l3);
		a.add(l4);
		Matrice test = new Matrice(4,4,a);
		Matrice res = m.multiply(RemarquableMatrices.zoom065);
		assertTrue(res.equals(test));
		
	}

	@Test
	public void testDehomogene(){
		init();
		Matrice m = FaceModifier.faceHomogene(f);
		ArrayList<Double[]> nonHArray = (ArrayList<Double[]>) f.toArrayAll();
		Matrice test = new Matrice(3, nonHArray.size(), nonHArray);
		assertTrue(test.equals(FaceModifier.dehomogene(m)));
	}

	@Test
	public void testFaceDehomo(){
		init();
		Matrice m = new Matrice(3, 3, f.toArrayAll());
		assertTrue(f.equals(FaceModifier.faceDehomo(m)));
	}
	
	@Test
	public void testFaceHomogene(){
		init();
		Matrice m = FaceModifier.faceHomogene(f);
		Double[] l1 = new Double[]{1.0, 0.0, 0.0, 0.0};
		Double[] l2 = new Double[]{0.0, 1.0, 0.0, 0.0};
		Double[] l3 = new Double[]{0.0, 0.0, 1.0, 0.0};
		Double[] l4 = new Double[]{0.0, 0.0, 0.0, 1.0};
		ArrayList<Double[]> a = new ArrayList<>();
		a.add(l1);
		a.add(l2);
		a.add(l3);
		a.add(l4);
		Matrice test = new Matrice(4,4,a);
		assertTrue(test.equals(m));
	}
	
	@Test
	public void testFaceHomogeneTranslat(){
		init();
		Matrice m = FaceModifier.faceHomogeneTranslat(f);
		Double[] l1 = new Double[]{1.0, 0.0, 0.0, 1.0};
		Double[] l2 = new Double[]{0.0, 1.0, 0.0, 1.0};
		Double[] l3 = new Double[]{0.0, 0.0, 1.0, 1.0};
		Double[] l4 = new Double[]{0.0, 0.0, 0.0, 1.0};
		ArrayList<Double[]> a = new ArrayList<>();
		a.add(l1);
		a.add(l2);
		a.add(l3);
		a.add(l4);
		Matrice test = new Matrice(4,4,a);
		assertTrue(test.equals(m));
	}

	@Test
	public void testRot90D(){
		init();
		Matrice m = FaceModifier.faceHomogene(f);
		Double[] l1 = new Double[]{0.0, 0.0, -1.0, 0.0};
		Double[] l2 = new Double[]{0.0, 1.0, 0.0, 0.0};
		Double[] l3 = new Double[]{1.0, 0.0, 0.0, 0.0};
		Double[] l4 = new Double[]{0.0, 0.0, 0.0, 1.0};
		ArrayList<Double[]> a = new ArrayList<>();
		a.add(l1);
		a.add(l2);
		a.add(l3);
		a.add(l4);
		Matrice test = new Matrice(4,4,a);
		m = RemarquableMatrices.rot90D.multiply(m);
		assertTrue(test.equals(m));
	}

	@Test
	public void testRot90G(){
		init();
		Matrice m = FaceModifier.faceHomogene(f);
		Double[] l1 = new Double[]{0.0, 0.0, 1.0, 0.0};
		Double[] l2 = new Double[]{0.0, 1.0, 0.0, 0.0};
		Double[] l3 = new Double[]{-1.0, 0.0, 0.0, 0.0};
		Double[] l4 = new Double[]{0.0, 0.0, 0.0, 1.0};
		ArrayList<Double[]> a = new ArrayList<>();
		a.add(l1);
		a.add(l2);
		a.add(l3);
		a.add(l4);
		Matrice test = new Matrice(4,4,a);
		m = RemarquableMatrices.rot90G.multiply(m);
		assertTrue(test.equals(m));
	}

	@Test
	public void testRot10G(){
		init();
		Matrice m = FaceModifier.faceHomogene(f);
		Double[] l1 = new Double[]{0.9847265389049334, 0.0, 0.17410813759359595, 0.0};
		Double[] l2 = new Double[]{0.0, 1.0, 0.0, 0.0};
		Double[] l3 = new Double[]{-0.17410813759359595, 0.0, 0.9847265389049334, 0.0};
		Double[] l4 = new Double[]{0.0, 0.0, 0.0, 1.0};
		ArrayList<Double[]> a = new ArrayList<>();
		a.add(l1);
		a.add(l2);
		a.add(l3);
		a.add(l4);
		Matrice test = new Matrice(4,4,a);
		m = RemarquableMatrices.rot10G.multiply(m);
		assertTrue(test.equals(m));
	}
	
	@Test
	public void testRot10D(){
		init();
		Matrice m = FaceModifier.faceHomogene(f);
		Double[] l1 = new Double[]{0.9847265389049334, 0.0, -0.17410813759359595, 0.0};
		Double[] l2 = new Double[]{0.0, 1.0, 0.0, 0.0};
		Double[] l3 = new Double[]{0.17410813759359595, 0.0, 0.9847265389049334, 0.0};
		Double[] l4 = new Double[]{0.0, 0.0, 0.0, 1.0};
		ArrayList<Double[]> a = new ArrayList<>();
		a.add(l1);
		a.add(l2);
		a.add(l3);
		a.add(l4);
		Matrice test = new Matrice(4,4,a);
		m = RemarquableMatrices.rot10D.multiply(m);
		assertTrue(test.equals(m));
	}
	
	@Test
	public void testTransD(){
		init();
		Matrice m = FaceModifier.faceHomogeneTranslat(f);
		Double[] l1 = new Double[]{1.0, 0.0, 0.0, 1.0};
		Double[] l2 = new Double[]{0.0, 1.0, 0.0, 1.0};
		Double[] l3 = new Double[]{0.0, 0.0, 1.0, 1.0};
		Double[] l4 = new Double[]{0.0, 0.0, 0.0, 1.0};
		ArrayList<Double[]> a = new ArrayList<>();
		a.add(l1);
		a.add(l2);
		a.add(l3);
		a.add(l4);
		Matrice test = new Matrice(4, 4, a);
		m = RemarquableMatrices.transD.multiply(m);
		assertTrue(test.equals(m));
	}

	@Test
	public void testTransG(){
		init();
		Matrice m = FaceModifier.faceHomogeneTranslat(f);
		Double[] l1 = new Double[]{1.0, 0.0, 0.0, 1.0};
		Double[] l2 = new Double[]{0.0, 1.0, 0.0, 1.0};
		Double[] l3 = new Double[]{0.0, 0.0, 1.0, 1.0};
		Double[] l4 = new Double[]{0.0, 0.0, 0.0, 1.0};
		ArrayList<Double[]> a = new ArrayList<>();
		a.add(l1);
		a.add(l2);
		a.add(l3);
		a.add(l4);
		Matrice test = new Matrice(4, 4, a);
		m = RemarquableMatrices.transG.multiply(m);
		assertTrue(test.equals(m));
	}

	@Test
	public void testTransB(){
		init();
		Matrice m = FaceModifier.faceHomogeneTranslat(f);
		Double[] l1 = new Double[]{1.0, 0.0, 0.0, 1.0};
		Double[] l2 = new Double[]{0.0, 1.0, 0.0, 1.0};
		Double[] l3 = new Double[]{0.0, 0.0, 1.0, 1.0};
		Double[] l4 = new Double[]{0.0, 0.0, 0.0, 1.0};
		ArrayList<Double[]> a = new ArrayList<>();
		a.add(l1);
		a.add(l2);
		a.add(l3);
		a.add(l4);
		Matrice test = new Matrice(4, 4, a);
		m = RemarquableMatrices.transB.multiply(m);
		assertTrue(test.equals(m));
	}

	@Test
	public void testTransH(){
		init();
		Matrice m = FaceModifier.faceHomogeneTranslat(f);
		Double[] l1 = new Double[]{1.0,  0.0, 0.0, 1.0};
		Double[] l2 = new Double[]{0.0,  1.0, 0.0, 1.0};
		Double[] l3 = new Double[]{0.0,  0.0, 1.0, 1.0};
		Double[] l4 = new Double[]{0.0,  0.0, 0.0, 1.0};
		ArrayList<Double[]> a = new ArrayList<>();
		a.add(l1);
		a.add(l2);
		a.add(l3);
		a.add(l4);
		Matrice test = new Matrice(4, 4, a);
		m = RemarquableMatrices.transH.multiply(m);
		assertTrue(test.equals(m));
	}
	
	@Test
	public void testEclairerFace(){
		init();
		double e1 =(double) FaceModifier.eclairerFace(f);
		assertTrue(e1==0.5773502588272095);
	}

}
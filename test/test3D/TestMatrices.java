package test3D;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


import matrice.Matrice;

public class TestMatrices {

	@Test
	public void TestAddvalues(){
		Matrice matrix =new Matrice(3,3);
		Double[] c1=new Double[]{0.0,0.0,0.0};
		Double[] c2=new Double[]{1.0,1.0,1.0};
		Double[] c3=new Double[]{2.0,2.0,2.0};
		Double[] f1=new Double[]{4.9,4.9,2.0,2.0,2.0};
		Double[] f2=new Double[]{4.9,4.9};
		assertTrue(matrix.addValues(c1));
		assertFalse(matrix.addValues(f1));
		assertTrue(matrix.addValues(c2));
		assertFalse(matrix.addValues(f2));
		assertTrue(matrix.addValues(c3));
		assertFalse(matrix.addValues(c1));
	}
		
	@Test
	public void testToString(){
		Double[] firstligne = new Double[]{2.0, 1.02, 2.51};
		Double[] secondligne = new Double[]{1.24,0.0,5.236};
		ArrayList<Double[]> m = new ArrayList<>();
		m.add(firstligne);
		m.add(secondligne);
		Matrice matrice = new Matrice(3, 2, m);
		assertEquals("| 2.0   1.24   |\n| 1.02  0.0    |\n| 2.51  5.236  |\n", matrice.toString());
	}
	
	@Test
	public void testMultiply(){
		Double[] firstligne = new Double[]{1.0, 0.0};
		Double[] secondligne = new Double[]{1.0,0.0};
		ArrayList<Double[]> m = new ArrayList<>();
		m.add(firstligne);
		m.add(secondligne);
		Matrice matrice = new Matrice(2, 2, m);
		Double[] firstligne2 = new Double[]{1.0, 1.0};
		Double[] secondligne2 = new Double[]{0.0,0.0};
		ArrayList<Double[]> m2 = new ArrayList<>();
		m2.add(firstligne2);
		m2.add(secondligne2);
		Matrice matriceM = new Matrice(2, 2, m2);
		Double[] firstligneRes = new Double[]{2.0, 0.0};
		Double[] secondligneRes = new Double[]{0.0,0.0};
		ArrayList<Double[]> mRes = new ArrayList<>();
		mRes.add(firstligneRes);
		mRes.add(secondligneRes);
		Matrice matriceRes = new Matrice(2, 2, mRes);
		assertTrue(matriceRes.equals(matrice.multiply(matriceM)));

		mRes.remove(firstligneRes);
		mRes.remove(secondligneRes);
		firstligneRes = new Double[]{1.0,2.0,3.0};		
		secondligneRes = new Double[]{1.0,2.0,3.0};
		
		mRes.add(firstligneRes);
		mRes.add(secondligneRes);
		matriceRes = new Matrice(3,3,mRes);
		assertEquals(null, matriceRes.multiply(matriceM));
	}
	
	@Test
	public void testEquals(){
		Double[] firstligne = new Double[]{1.0, 0.0};
		Double[] secondligne = new Double[]{0.0,1.0};
		ArrayList<Double[]> m = new ArrayList<>();
		m.add(firstligne);
		m.add(secondligne);
		Matrice matrice = new Matrice(2, 2, m);
		Matrice matrice2 = new Matrice(2,2,m);
		assertTrue(matrice.equals(matrice2));
		m.remove(firstligne);
		m.remove(secondligne);
		firstligne = new Double[]{3.0,2.0};
		secondligne = new Double[]{1.0,1.0};
		Double[] thirdligne = new Double[]{3.0,5.0};
		m.add(firstligne);
		m.add(secondligne);
		m.add(thirdligne);
		matrice2 = new Matrice(3, 2,m);
		assertFalse(matrice.equals(matrice2));
		m = new ArrayList<>();
		firstligne = new Double[]{3.0,2.0,3.0};
		secondligne = new Double[]{1.0,1.0,1.0};
		m.add(firstligne);
		m.add(secondligne);
		matrice2 = new Matrice(2, 3,m);
		assertFalse(matrice.equals(matrice2));
		m = new ArrayList<>();
		firstligne = new Double[]{1.0, 1.0};
		secondligne = new Double[]{0.0,1.0};
		m.add(firstligne);
		m.add(secondligne);
		matrice2 = new Matrice(2, 2,m);
		assertFalse(matrice.equals(matrice2));
	}
	
	@Test
	public void testFromPointToVecteur(){
		Double[] p1 = new Double[]{0.0,1.0,0.0};
		Double[] p2 = new Double[]{1.0,0.0,0.0};
		List<Double[]> mRes = new ArrayList<Double[]>();
		mRes.add(new Double[]{1.0,-1.0,0.0});
		Matrice res = new Matrice(3,1,mRes);
		Matrice vecteur = Matrice.fromPointstoVecteur(p1, p2);		
		assertEquals(res.toString(), vecteur.toString());
	}
	
	@Test
	public void testProduitVectoriel(){
		List<Double[]> m1 = new ArrayList<Double[]>();
		m1.add(new Double[]{1.0,0.0,0.0});
		Matrice matrice1 = new Matrice(3,1,m1);
		List<Double[]> m2 = new ArrayList<Double[]>();
		m2.add(new Double[]{0.0,1.0,0.0});
		Matrice matrice2 = new Matrice(3,1,m2);
		
		List<Double[]> mRes = new ArrayList<Double[]>();
		mRes.add(new Double[]{0.0,0.0,1.0});
		Matrice res = new Matrice(3,1,mRes);
		
		Matrice produit = matrice1.produitVectoriel(matrice2);
		assertEquals(produit.toString(), res.toString());
		
	}
	
}
package test3D;

import static org.junit.Assert.*;

import org.junit.Test;

import modele.Face;
import modele.Point;

public class TestFace {

	Face f1=new Face();
	Face f2=new Face();

	//test
	
	@Test
	public void testaddPoint() {
		f1.addPoint(new Point(0.0,4.0,-2.0));
		assertEquals("[( 0.0 , 4.0 , -2.0 )]", f1.getEnsemble().toString());
		f1.addPoint(new Point(9.0,3.0,-2.0));
		assertEquals("[( 0.0 , 4.0 , -2.0 ), ( 9.0 , 3.0 , -2.0 )]", f1.getEnsemble().toString());
		f1.addPoint(new Point(4.8,5.5,-1.2));
		assertEquals("[( 0.0 , 4.0 , -2.0 ), ( 9.0 , 3.0 , -2.0 ), ( 4.8 , 5.5 , -1.2 )]", f1.getEnsemble().toString());
	}
	
	public void add(){
		f1.addPoint(new Point(0.0,4.0,-2.0));
		f1.addPoint(new Point(9.0,3.0,45.0));
		f1.addPoint(new Point(4.8,5.5,-1.2));
	}
		
	
	@Test
	public void testBaricentre(){
		f1.addPoint(new Point(4.0, 6.0, 13.0));
		assertTrue(f1.baricentre()==13);	
		add();
		float bari=0;
		for(int i=0;i<f1.getEnsemble().size();i++){
			bari+=f1.getEnsemble().get(i).getZ();
		}
		bari=bari/f1.getEnsemble().size();
		assertTrue(bari==f1.baricentre());
		f2.addPoint(new Point(0.0,0.0,0.0));
		assertTrue(f2.baricentre()==0);
	}
	
	@Test
	public void testEquals(){
		add();
		assertFalse(f1.equals(f2));
		f2.addPoint(new Point(0.0,45.0,-62.0));
		f2.addPoint(new Point(96.0,63.0,5.0));
		f2.addPoint(new Point(-4.8,-5.75,-1.2));
		assertFalse(f1.equals(f2));
		Face f3= new Face();
		f3.addPoint(new Point(0.0,45.0,-62.0));
		f3.addPoint(new Point(96.0,63.0,5.0));
		f3.addPoint(new Point(-4.8,-5.75,-1.2));
		assertTrue(f2.equals(f3));
		f3.addPoint(new Point(-4.8,-5.75,-1.2));
		assertFalse(f2.equals(f3));
		assertFalse(f1.equals(f2));		
	}
	
	@Test
	public void testSize(){
		add();
		assertTrue(f1.size()==3);
	}
	
	@Test
	public void testToString(){
		add();
		assertEquals(f1.toString(), "["+f1.getEnsemble().get(0)+", "+f1.getEnsemble().get(1)+", "+f1.getEnsemble().get(2)+", ]");
	}
	
	@Test
	public void testGetXCoord(){
		add();
		assertEquals(f1.getXCoord(1)[0], 0);
		assertEquals(f1.getXCoord(1)[1], 2147483647);
		assertEquals(f1.getXCoord(1)[1], 2147483647);
	}
	
	@Test
	public void testGetYCoord(){
		add();
		assertEquals(f1.getXCoord(1)[0], 0);
		assertEquals(f1.getYCoord(1)[1], -2147483648);
		assertEquals(f1.getYCoord(1)[2],  -2147483648);	
	}
	
	@Test
	public void testToArrayAll(){
		add();
		
		for(int i=0; i<f1.toArrayAll().size();i++){
			for(int j=0; j<f1.toArrayAll().size();j++){
				assertEquals(f1.toArrayAll().get(i)[j],f1.getEnsemble().get(i).toArray()[j]); 
			}
			
		}
		
	}
	
}

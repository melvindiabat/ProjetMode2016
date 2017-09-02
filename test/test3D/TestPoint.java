package test3D;

import static org.junit.Assert.*;

import org.junit.Test;

import modele.Point;

public class TestPoint {

	Point p1;
	Point p2;

	public void add(){
		p1=new Point(5.9, 6.8, 7.0);
		p2=new Point(0.0, -6.3,-12.9);
	}

	@Test
	public void testToString() {
		add();
		assertEquals("( 5.9 , 6.8 , 7.0 )",p1.toString());
		assertEquals("( 0.0 , -6.3 , -12.9 )",p2.toString());
	}

	@Test
	public void testEquals(){
		add();
		assertFalse(p1.equals(p2));
		Point p3 = new Point(0.0, -6.3, -12.9);
		assertTrue(p2.equals(p3));
		assertFalse(p3.equals(new Point(0.0, 1, -12.9)));
		assertFalse(p3.equals(new Point(0.0, -6.3, -12.0)));
	}

	@Test
	public void testToArray(){
		assertEquals(new Point(2.0, 2.0, 2.0).toArray(), new Double[]{2.0,2.0,2.0});
	}

}

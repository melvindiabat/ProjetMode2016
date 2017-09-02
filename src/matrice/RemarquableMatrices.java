package matrice;

import java.util.ArrayList;

import modele.FichierReader;

/**
 * Enumeration permettant d'avoir accès à des matrices remarquables plus facilement
 * afin d'exécuter des modifications 3D
 * @author Beuns Vianney
 *
 */

public enum RemarquableMatrices {;

	static Double[] homog3D = new Double[]{0.0, 0.0, 0.0, 1.0};
	
	private static Double[] zoom15L1 = new Double[]{1.5, 0.0, 0.0, 0.0};
	private static Double[] zoom15L2 = new Double[]{0.0, 1.5, 0.0, 0.0};
	private static Double[] zoom15L3 = new Double[]{0.0, 0.0, 1.5, 0.0};
	private static ArrayList<Double[]> arrZoom15 = arrayConstruct(zoom15L1, zoom15L2, zoom15L3);

	/**
	 * La matrice permettant d'appliquer un zoom x1.5 à la figure
	 */
	public static final Matrice zoom15 = new Matrice(4, 4, arrZoom15);
	
	private static Double[] zoom05L1 = new Double[]{0.67, 0.0, 0.0, 0.0};
	private static Double[] zoom05L2 = new Double[]{0.0, 0.67, 0.0, 0.0};
	private static Double[] zoom05L3 = new Double[]{0.0, 0.0, 0.67, 0.0};
	private static ArrayList<Double[]> arrZoom05 = arrayConstruct(zoom05L1, zoom05L2, zoom05L3);
	
	/**
	 * La matrice permettant d'appliquer un zoom x0.65 à la figure
	 */
	public static final Matrice zoom065 = new Matrice(4, 4, arrZoom05);
	
	
	private static Double[] rot90DL1 = new Double[]{0.0, 0.0, -1.0, 0.0};
	private static Double[] rot90DL2 = new Double[]{0.0, 1.0, 0.0, 0.0};
	private static Double[] rot90DL3 = new Double[]{1.0, 0.0, 0.0, 0.0};
	private static ArrayList<Double[]> arrRot90D = arrayConstruct(rot90DL1, rot90DL2, rot90DL3);
	
	/**
	 * La matrice permettant d'appliquer une rotation à 90° à la figure vers la droite.
	 */
	public static final Matrice rot90D = new Matrice(4,4,arrRot90D);
	
	
	private static Double[] rot90GL1 = new Double[]{0.0, 0.0, 1.0, 0.0};
	private static Double[] rot90GL2 = new Double[]{0.0, 1.0, 0.0, 0.0};
	private static Double[] rot90GL3 = new Double[]{-1.0, 0.0, 0.0, 0.0};
	private static ArrayList<Double[]> arrRot90G = arrayConstruct(rot90GL1, rot90GL2, rot90GL3);
	
	/**
	 * La matrice permettant d'appliquer une rotation à 90° à la figure vers la gauche.
	 */
	public static final Matrice rot90G = new Matrice(4,4,arrRot90G);
	
	private static Double[] rot10GL1 = new Double[]{Math.cos(-0.175), 0.0, (-1)*Math.sin(-0.175), 0.0};
	private static Double[] rot10GL2 = new Double[]{0.0, 1.0, 0.0, 0.0};
	private static Double[] rot10GL3 = new Double[]{Math.sin(-0.175), 0.0, Math.cos(-0.175), 0.0};
	private static ArrayList<Double[]> arrRot10G = arrayConstruct(rot10GL1, rot10GL2, rot10GL3);
	
	/**
	 * La matrice permettant d'appliquer une rotation à 10° à la figure vers la gauche.
	 */
	public static final Matrice rot10G = new Matrice(4,4,arrRot10G);
	
	
	private static Double[] rot10DL1 = new Double[]{Math.cos(0.175), 0.0, (-1)*Math.sin(0.175), 0.0};
	private static Double[] rot10DL2 = new Double[]{0.0, 1.0, 0.0, 0.0};
	private static Double[] rot10DL3 = new Double[]{Math.sin(0.175), 0.0, Math.cos(0.175), 0.0};
	private static ArrayList<Double[]> arrRot10D = arrayConstruct(rot10DL1, rot10DL2, rot10DL3);
	
	/**
	 * La matrice permettant d'appliquer une rotation à 10° à la figure vers la droite.
	 */
	public static final Matrice rot10D = new Matrice(4,4,arrRot10D);
	

	private static ArrayList<Double[]> arrayConstruct(Double[] d1, Double[] d2, Double[] d3){
		ArrayList<Double[]> a = new ArrayList<>();
		a.add(d1);
		a.add(d2);
		a.add(d3);
		a.add(homog3D);
		return a;
	}
	
	private static ArrayList<Double[]> arrayConstructBis(Double[] d1, Double[] d2, Double[] d3, Double[] d4){
		ArrayList<Double[]> a = new ArrayList<>();
		a.add(d1);
		a.add(d2);
		a.add(d3);
		a.add(d4);
		return a;
	}
	
	private static Double[] transL1 = new Double[]{1.0, 0.0, 0.0,0.0};
	private static Double[] transL2 = new Double[]{0.0, 1.0, 0.0, 0.0};
	private static Double[] transL3 = new Double[]{0.0, 0.0, 1.0, 0.0};
	
	private static Double[] transDL4 = new Double[]{0.5*((FichierReader.widthMax - FichierReader.widthMin)/4), 0.0, 0.0, 1.0};
	private static ArrayList<Double[]> arrTransD = arrayConstructBis(transL1, transL2, transL3, transDL4);
	
	/**
	 * La matrice permettant d'appliquer une translation de 0.5 en x.
	 */
	public static final Matrice transD = new Matrice(4,4,arrTransD);
	
	
	private static Double[] transGL4 = new Double[]{-0.5*((FichierReader.widthMax - FichierReader.widthMin)/4), 0.0, 0.0, 1.0};
	private static ArrayList<Double[]> arrTransG = arrayConstructBis(transL1, transL2, transL3, transGL4);
	
	/**
	 * La matrice permettant d'appliquer une translation de -0.5 en x.
	 */
	public static final Matrice transG = new Matrice(4,4,arrTransG);
	

	private static Double[] transHL4 = new Double[]{0.0, 0.5*((FichierReader.heightMax - FichierReader.heightMin)/4), 0.0, 1.0};
	private static ArrayList<Double[]> arrTransH = arrayConstructBis(transL1, transL2, transL3, transHL4);
	
	/**
	 * La matrice permettant d'appliquer une translation de 0.5 en y.
	 */
	public static final Matrice transH = new Matrice(4,4,arrTransH);
	
	
	private static Double[] transBL4 = new Double[]{0.0, -0.5*((FichierReader.heightMax - FichierReader.heightMin)/4), 0.0, 1.0};
	private static ArrayList<Double[]> arrTransB = arrayConstructBis(transL1, transL2, transL3, transBL4);
	
	/**
	 * La matrice permettant d'appliquer une translation de -0.5 en y.
	 */
	public static final Matrice transB = new Matrice(4,4,arrTransB);
	
	
	private static Double[] rot10L1 = new Double[]{1.0,0.0,0.0, 0.0};
	
	private static Double[] rot10HL2 = new Double[]{0.0, Math.cos(0.175), (-1)*Math.sin(0.175), 0.0};
	private static Double[] rot10HL3 = new Double[]{0.0, Math.sin(0.175), Math.cos(0.175), 0.0};
	private static ArrayList<Double[]> arrRot10H = arrayConstruct(rot10L1, rot10HL2, rot10HL3);
	
	/**
	 * La matrice permettant d'appliquer une rotation à 10° à la figure vers le haut.
	 */
	public static final Matrice rot10H = new Matrice(4,4,arrRot10H);
	
	private static Double[] rot10BL2 = new Double[]{0.0, Math.cos(-0.175), (-1)*Math.sin(-0.175), 0.0};
	private static Double[] rot10BL3 = new Double[]{0.0, Math.sin(-0.175), Math.cos(-0.175), 0.0};
	private static ArrayList<Double[]> arrRot10B = arrayConstruct(rot10L1, rot10BL2, rot10BL3);
	
	/**
	 * La matrice permettant d'appliquer une rotation à 10° à la figure vers le bas.
	 */
	public static final Matrice rot10B= new Matrice(4,4,arrRot10B);
	
	
}


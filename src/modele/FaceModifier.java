package modele;

import java.util.ArrayList;

import matrice.Matrice;
import matrice.RemarquableMatrices;

/**
 * Classe réunissant les différentes méthodes pour altérer les faces d'un modèle dans l'espace 3D.
 * @author Beuns Vianney
 *
 */

public class FaceModifier {
	
	/**
	 * Passage d'une face en coordonnées homogènes afin de pouvoir effectuer des calculs dessus
	 * @param f La face que l'on veut passer en coordonnées homogènes
	 * @return Une matrice en coordonnées homogènes
	 */
	public static Matrice faceHomogene(Face f){
		
		ArrayList<Double[]> array = (ArrayList<Double[]>) f.toArrayAll();
		ArrayList<Double[]> arrayRes = new ArrayList<>();
		for(Double[] d : array){
			Double[] tmp = d.clone();
			Double[] res = new Double[d.length+1];
			for(int i = 0; i<tmp.length; i++){
				res[i] = tmp[i];
			}
			res[res.length-1] = 0.0;
			arrayRes.add(res);
		}
		Double[] homog3D = new Double[]{0.0, 0.0, 0.0, 1.0};
		arrayRes.add(homog3D);
		Matrice res = new Matrice(4, arrayRes.size(), arrayRes);
		return res;
	}
	
	/**
	 * Passage d'une face en coordonnées homogènes afin de pouvoir effectuer des translations dessus
	 * @param f La face que l'on veut passer en coordonnées homogènes
	 * @return Une matrice en coordonnées homogènes
	 */
	public static Matrice faceHomogeneTranslat(Face f){
		
		ArrayList<Double[]> array = (ArrayList<Double[]>) f.toArrayAll();
		ArrayList<Double[]> arrayRes = new ArrayList<>();
		for(Double[] d : array){
			Double[] tmp = d.clone();
			Double[] res = new Double[d.length+1];
			for(int i = 0; i<tmp.length; i++){
				res[i] = tmp[i];
			}
			res[res.length-1] = 1.0;
			arrayRes.add(res);
		}
		Double[] homog3D = new Double[]{0.0, 0.0, 0.0, 1.0};
		arrayRes.add(homog3D);
		Matrice res = new Matrice(4, arrayRes.size(), arrayRes);
		return res;
	}
	
	/**
	 * Permer de calculer l'intensite lumineuse d'une face 
	 * @param f face 
	 * @return intensite
	 */
	public static float eclairerFace(Face f){
		//Faire deux vecteurs avec les 3 points de la face
		ArrayList<Point> points = (ArrayList<Point>) f.getEnsemble();
		Matrice v1 = Matrice.fromPointstoVecteur(points.get(0).toArray(), points.get(1).toArray());
		Matrice v2 = Matrice.fromPointstoVecteur(points.get(0).toArray(), points.get(2).toArray());
		
		//Calculer le vecteur normal avec un produit vectoriel des deux vecteurs trouv�s plus t�t
		
		Matrice n = v1.produitVectoriel(v2);
		
		//Normaliser le vecteur normal
		
		Double[] nd = new Double[3];
		nd[0] = n.getMatrice()[0][0];
		nd[1] = n.getMatrice()[1][0];
		nd[2] = n.getMatrice()[2][0];
		double normal = Math.pow(nd[0], 2) + Math.pow(nd[1], 2) + Math.pow(nd[2], 2);
		normal = Math.sqrt(normal);
		
		//Produit du vecteur normale et du vecteur repr�sentant la direction de la lumi�re (en consid�rant ici qu'elle vienne du spectateur)
		
		double prod = n.getMatrice()[2][0];
		
		//Ratio entre le produit pr�cedemment calcul� et la normalisation du vecteur normal = intensit� lumineuse
		
		return (float) (prod/normal);
	}
	
	/**
	 * Permet de zoomer 1.5x le modèle
	 * @param m Le Modele sur lequel appliquer la transformation
	 */
	public static void zoom15(Modele m){

		ArrayList<Face> faces = (ArrayList<Face>) m.getFaces();
		ArrayList<Face> facesZoom = new ArrayList<>();
		for(Face f : faces){
			Matrice mat = FaceModifier.faceHomogene(f);
			mat = mat.multiply(RemarquableMatrices.zoom15);
			mat = FaceModifier.dehomogene(mat);
			Face r = FaceModifier.faceDehomo(mat);
			facesZoom.add(r);
		}
		m.setFaces(facesZoom);
	}
	
	/**
	 * Permet de zoomer 0.65x le modèle
	 * @param m Le Modele sur lequel appliquer la transformation
	 */
	public static void zoom065(Modele m){

		ArrayList<Face> faces = (ArrayList<Face>) m.getFaces();
		ArrayList<Face> facesZoom = new ArrayList<>();
		for(Face f : faces){
			Matrice mat = FaceModifier.faceHomogene(f);
			mat = mat.multiply(RemarquableMatrices.zoom065);
			mat = FaceModifier.dehomogene(mat);
			Face r = FaceModifier.faceDehomo(mat);
			facesZoom.add(r);
		}
		m.setFaces(facesZoom);

	}
	/**
	 * Permet de faire pivoter le modèle de 90° sur la droite
	 * @param m le Modele sur lequel appliquer la transformation
	 */
	public static void rotat90D(Modele m){
		ArrayList<Face> faces = (ArrayList<Face>) m.getFaces();
		ArrayList<Face> facesRotat90D = new ArrayList<>();
		for(Face f : faces){
			Matrice mat = FaceModifier.faceHomogene(f);
			mat = RemarquableMatrices.rot90D.multiply(mat);
			mat = FaceModifier.dehomogene(mat);
			Face r = FaceModifier.faceDehomo(mat);
			facesRotat90D.add(r);
		}
		m.setFaces(m.sortByZBuff(facesRotat90D));
	}
	
	/**
	 * Permet de faire pivoter le modèle de 90° sur la gauche
	 * @param m le Modele sur lequel appliquer la transformation
	 */
	public static void rotat90G(Modele m){
		ArrayList<Face> faces = (ArrayList<Face>) m.getFaces();
		ArrayList<Face> facesRotat90G = new ArrayList<>();
		for(Face f : faces){
			Matrice mat = FaceModifier.faceHomogene(f);
			mat = RemarquableMatrices.rot90G.multiply(mat);
			mat = FaceModifier.dehomogene(mat);
			Face r = FaceModifier.faceDehomo(mat);
			facesRotat90G.add(r);
		}
		m.setFaces(m.sortByZBuff(facesRotat90G));
	}
	/**
	 * Permet de faire pivoter le modèle de 10° sur la gauche
	 * @param m le Modele sur lequel appliquer la transformation
	 */
	public static void rotat10G(Modele m){
		ArrayList<Face> faces = (ArrayList<Face>) m.getFaces();
		ArrayList<Face> facesRotat10G = new ArrayList<>();
		for(Face f : faces){
			Matrice mat = FaceModifier.faceHomogene(f);
			mat = RemarquableMatrices.rot10G.multiply(mat);
			mat = FaceModifier.dehomogene(mat);
			Face r = FaceModifier.faceDehomo(mat);
			facesRotat10G.add(r);
		}
		m.setFaces(m.sortByZBuff(facesRotat10G));
	}
	
	/**
	 * Permet de faire pivoter le modèle de 10° sur la droite
	 * @param m le Modele sur lequel appliquer la transformation
	 */
	public static void rotat10D(Modele m){
		ArrayList<Face> faces = (ArrayList<Face>) m.getFaces();
		ArrayList<Face> facesRotat10D = new ArrayList<>();
		for(Face f : faces){
			Matrice mat = FaceModifier.faceHomogene(f);
			mat = RemarquableMatrices.rot10D.multiply(mat);
			mat = FaceModifier.dehomogene(mat);
			Face r = FaceModifier.faceDehomo(mat);
			facesRotat10D.add(r);
		}
		m.setFaces(m.sortByZBuff(facesRotat10D));
	}
	
	/**
	 * Permet de faire pivoter le modèle de 10° sur le haut
	 * @param m le Modele sur lequel appliquer la transformation
	 */
	public static void rotat10H(Modele m){
		ArrayList<Face> faces = (ArrayList<Face>) m.getFaces();
		ArrayList<Face> facesRotat10H = new ArrayList<>();
		for(Face f : faces){
			Matrice mat = FaceModifier.faceHomogene(f);
			mat = RemarquableMatrices.rot10H.multiply(mat);
			mat = FaceModifier.dehomogene(mat);
			Face r = FaceModifier.faceDehomo(mat);
			facesRotat10H.add(r);
		}
		m.setFaces(m.sortByZBuff(facesRotat10H));
	}
	
	/**
	 * Permet de faire pivoter le modèle de 10° sur le bas
	 * @param m le Modele sur lequel appliquer la transformation
	 */
	public static void rotat10B(Modele m){
		ArrayList<Face> faces = (ArrayList<Face>) m.getFaces();
		ArrayList<Face> facesRotat10B = new ArrayList<>();
		for(Face f : faces){
			Matrice mat = FaceModifier.faceHomogene(f);
			mat = RemarquableMatrices.rot10B.multiply(mat);
			mat = FaceModifier.dehomogene(mat);
			Face r = FaceModifier.faceDehomo(mat);
			facesRotat10B.add(r);
		}
		m.setFaces(m.sortByZBuff(facesRotat10B));
	}
	
	/**
	 * Permet de deplacer le modèle sur la droite
	 * @param m le Modele sur lequel appliquer la transformation
	 */
	public static void transD(Modele m){
		ArrayList<Face> faces = (ArrayList<Face>) m.getFaces();
		ArrayList<Face> trans = new ArrayList<>();
		for(Face f : faces){
			Matrice mat = FaceModifier.faceHomogeneTranslat(f);
			mat = RemarquableMatrices.transD.multiply(mat);
			mat = FaceModifier.dehomogene(mat);
			Face r = FaceModifier.faceDehomo(mat);
			trans.add(r);
		}
		m.setFaces(trans);
	}
	
	/**
	 * Permet de deplacer le modèle sur la gauche
	 * @param m le Modele sur lequel appliquer la transformation
	 */
	public static void transG(Modele m){
		ArrayList<Face> faces = (ArrayList<Face>) m.getFaces();
		ArrayList<Face> trans = new ArrayList<>();
		for(Face f : faces){
			Matrice mat = FaceModifier.faceHomogeneTranslat(f);
			mat = RemarquableMatrices.transG.multiply(mat);
			mat = FaceModifier.dehomogene(mat);
			Face r = FaceModifier.faceDehomo(mat);
			trans.add(r);
		}
		m.setFaces(trans);
	}
	/**
	 * Permet de deplacer le modèle sur le haut
	 * @param m le Modele sur lequel appliquer la transformation
	 */
	public static void transH(Modele m){
		ArrayList<Face> faces = (ArrayList<Face>) m.getFaces();
		ArrayList<Face> trans = new ArrayList<>();
		for(Face f : faces){
			Matrice mat = FaceModifier.faceHomogeneTranslat(f);
			mat = RemarquableMatrices.transH.multiply(mat);
			mat = FaceModifier.dehomogene(mat);
			Face r = FaceModifier.faceDehomo(mat);
			trans.add(r);
		}
		m.setFaces(trans);
	}
	/**
	 * Permet de deplacer le modèle sur le bas
	 * @param m le Modele sur lequel appliquer la transformation
	 */
	public static void transB(Modele m){
		ArrayList<Face> faces = (ArrayList<Face>) m.getFaces();
		ArrayList<Face> trans = new ArrayList<>();
		for(Face f : faces){
			Matrice mat = FaceModifier.faceHomogeneTranslat(f);
			mat = RemarquableMatrices.transB.multiply(mat);
			mat = FaceModifier.dehomogene(mat);
			Face r = FaceModifier.faceDehomo(mat);
			trans.add(r);
		}
		m.setFaces(trans);
	}
		
	
	/**
	 * Retourne une matrice déhomogénéisée (attention, à n'utiliser que lorsque l'on est 
	 * certains d'avoir une matrice en coordonnées homogènes en paramètre
	 * @param mH la matrice aux coordonnées homogènes
	 * @return la matrice non homogène
	 */
	
	public static Matrice dehomogene(Matrice mH){
		
		Double[][] resA = new Double[mH.getLignes()-1][mH.getColonnes()-1];
		Double[][] m = mH.getMatrice();
		for(int i = 0; i<mH.getColonnes()-1;i++){
			for(int c = 0; c<mH.getLignes()-1; c++){
				resA[c][i] = m[i][c];
			}
		}
		ArrayList<Double[]> res = new ArrayList<>();
		for(int l = 0; l<resA.length;l++){
			res.add(resA[l]);
		}
		Matrice resM = new Matrice(mH.getLignes()-1, mH.getColonnes()-1, res);
		return resM;
	}
	
	
	/**
	 * Cette fonction calcule une face en fonction d'une matrice aux coordonnées non homogènes
	 * @param m la matrice aux coordonnées non homogènes
	 * @return la face calculée
	 */
	public static Face faceDehomo(Matrice m){
		Face res = new Face();
		Double x = null, y = null, z = null;
		Double[][] matrice = m.getMatrice();
		for(int c = 0; c<m.getColonnes(); c++){
			for(int l = 0; l<3; l++){
				if(l == 0) x = matrice[l][c];
				else if(l == 1) y = matrice[l][c];
				else z = matrice[l][c];
			}
			res.addPoint(new Point(x, y, z));
		}
		
		return res;
	}

		
}

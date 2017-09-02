package matrice;

import java.util.Collection;
/**
 * Classe représentant une matrice ainsi que ses différentes propriétées
 * mathématiques
 * @author Beuns Vianney
 *
 */
public class Matrice {

	private int lignes;
	private int colonnes;
	private Double[][] matrice;

	/**
	 * Crée une matrice de taille (l, c) vide.
	 * @param l Le nombre de lignes
	 * @param c Le nombre de colonnes.
	 */
	public Matrice(int l, int c){
		this.lignes = l;
		this.colonnes = c;
		matrice = new Double[lignes][colonnes];
	}

	/**
	 * Crée une matrice de taille (l, c) remplie par les points contenus 
	 * dans une collection de tableaux 
	 * @param l le nombre de lignes
	 * @param c le nombre de colonnes
	 * @param d la collection contenant des tableaux de doubles correspondant aux coordonnées des points à ajouter
	 */
	public Matrice(int l, int c, Collection<Double[]> d){
		this(l, c);
		addAllValues(d);
	}

	/**
	 * Remplie une colonne avec les coordonées d'un point.
	 * @param point Coordonées d'un point données sous forme d'un tableau de nombres flotants.
	 * @return vrai si l'ajout s'est bien déroulé
	 */
	public boolean addValues(Double[] point){
		int cpt = 0;
		if(point.length != lignes) {
			//System.out.println("Erreur : Plus de points que de lignes dans la matrice !");
			return false;
		}
		while(cpt<lignes && matrice[0][cpt] != null){
			cpt++;
		}
		if(cpt == lignes){
			//System.out.println("La matrice est déjà remplie");
			return false;
		}
		for(int i = 0; i<lignes; i++){
			matrice[i][cpt] = point[i];
		}
		return true;
	}

	/**
	 * Ajoute les points contenus dans une collection dans la matrice de gauche à droite
	 * @param c une collection contenant des tableaux de doubles correspondant aux coordonnées des points à ajouter
	 */
	public void addAllValues(Collection<Double[]> c){
		for(Double[] d : c){
			addValues(d);
		}
	}


	/**
	 * Multiplie la matrice A sur laquelle cette fonction a été invoquée
	 * par la matrice M ;
	 * A.M
	 * @param m la matrice multipliée 
	 * @return le résultat de la multiplication matricielle
	 */
	public Matrice multiply(Matrice m){
		Matrice res = new Matrice(lignes, m.getColonnes());
		Double[] pointMulti = new Double[lignes];
		Double calc = 0.0;
		if(colonnes != m.getLignes()) return null;
		Double[][] mat = m.getMatrice();
		for(int v = 0; v<m.getColonnes(); v++){		//Parcours des colonnes de la matrice m
			for(int i = 0; i<lignes; i++){					//Parcours des lignes de la matrice sur laquelle on multiplie m
				for(int c = 0; c<colonnes; c++){		//Parcours des colonnes de la matrice sur laquelle on multiplie m
					calc += matrice[i][c]*mat[c][v]; 
				}
				pointMulti[i] = calc;
				calc =0.0;
			}
			res.addValues(pointMulti);
			calc = 0.0;
		}

		return res;
	}



	/**
	 * Vérifie si les deux matrices sont égales (si elles ont les même valeurs)
	 * @param m la matrice que l'on veut comparer
	 * @return vrai si les deux matrices ont les mêmes valeurs
	 */
	public boolean equals(Matrice m){

		if(lignes != m.getLignes() || colonnes != m.getColonnes()) return false;
		Double[][] mat = m.getMatrice();
		for(int i=0; i<lignes;i++){
			for(int c = 0; c<colonnes ; c++){
				if(!matrice[i][c].equals(mat[i][c])) return false;
			}
		}

		return true;
	}

	public Double[][] getMatrice() {
		return matrice;
	}

	public int getLignes() {
		return lignes;
	}

	public int getColonnes() {
		return colonnes;
	}

	/**
	 * Affichage sous forme de string d'une matrice
	 */
	public String toString(){
		String res = "";
		String compt ="";
		int t = tailleMax();
		int nbEspaces;
		for(int i=0; i<lignes;i++){
			res+="| ";
			for(int v = 0; v<colonnes; v++){
				compt = Double.toString(matrice[i][v]);
				nbEspaces = t-compt.length();
				res+=compt;
				for(int e = 0; e<nbEspaces; e++){
					res+=" ";
				}
			}
			res+=" |\n";
		}
		//System.out.println(res);
		return res;
	}


	/**
	 * Calcule la taille maximale des valeurs afin d'avoir un affichage harmonieux
	 * @return la taille maximale des valeurs
	 */
	private int tailleMax(){
		int taille = -1;
		String cpt = "";
		for(int i=0; i<lignes;i++){
			for(int v = 0; v<colonnes; v++){
				cpt = Double.toString(matrice[i][v]);
				if(cpt.length() > taille){
					taille = cpt.length();
				}
			}
		}
		return taille+1;
	}

	/**
	 * Calcule le produit vectoriel de deux matrice
	 * @param m matrice a multiplier
	 * @return la matrice resultat
	 */
	public Matrice produitVectoriel(Matrice m){
		
		Double[] vect = new Double[3];
		Double[] v1 = new Double[3];
		v1[0] = this.getMatrice()[0][0];
		v1[1] = getMatrice()[1][0];
		v1[2] = getMatrice()[2][0];
		Double[] v2 = new Double[3];
		v2[0] = m.getMatrice()[0][0];
		v2[1] = m.getMatrice()[1][0];
		v2[2] = m.getMatrice()[2][0];
		
		vect[0] = v1[1]*v2[2] - v2[1]*v1[2];
		vect[1] = v1[2]*v2[0] - v2[2]*v1[0];
		vect[2] = v1[0]*v2[1] - v2[0]*v1[1];
		Matrice res = new Matrice(3, 1);
		res.addValues(vect);
		return res;
	}
	
	/**
	 * Transforme deux points en vecteur
	 * @param p1 point 1
	 * @param p2 point 2
	 * @return vecteur
	 */
	public static Matrice fromPointstoVecteur(Double[] p1, Double[] p2){

		Double[] vect = new Double[3];
		vect[0] = p2[0] - p1[0];
		vect[1] = p2[1] - p1[1];
		vect[2] = p2[2] - p1[2];
		Matrice res = new Matrice(3, 1);
		res.addValues(vect);
		return res;
	}
}
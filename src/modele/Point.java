package modele;

/**
 * Classe représentant un point dans l'espace 3D.
 * @author Beuns Vianney
 *
 */

public class Point {
	private double x;
	private double y;
	private double z;

	public Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Double[] toArray(){
		
		Double[] res = new Double[]{x, y, z};
		
		return res;
	}
	
	public String toString(){
		return "( "+this.x+" , "+this.y+" , "+this.z+" )";
	}
	public double getX(){
		return this.x;
	}
	public double getY(){
		return this.y;
	}
	public double getZ(){
		return this.z;
	}
	
	/**
	 * Vérifie si deux points sont égaux
	 * @param p le point comparé
	 * @return vrai si les deux points ont les même coordonnées
	 */
	public boolean equals(Point p){					
		if(x != p.getX()) return false;
		if(y != p.getY()) return false;
		if(z != p.getZ()) return false;
		return true;
	}
}

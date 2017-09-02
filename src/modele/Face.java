package modele;

import java.util.ArrayList;
import java.util.List;
/**
 * Classe représentant une face avec sa liste de points la composant
 * @author Beuns Vianney
 *
 */
public class Face {


	List<Point> ensemble;
	float zbuff;

	/**
	 * Le constructeur initialise une arrayList d'un ensemble de point
	 */
	public Face() {
		ensemble = new ArrayList<>();
	}
	
	public void addPoint(Point p){
		ensemble.add(p);
		zbuff = baricentre();
	}

	public int size(){
		return ensemble.size();
	}

	public List<Point> getEnsemble(){
		return ensemble;
	}

	public List<Double[]> toArrayAll(){

		ArrayList<Double[]> res = new ArrayList<>();
		for(Point p : ensemble){
			res.add(p.toArray());
		}

		return res;
	}

	public String toString(){
		String res = "[";
		for(Point p : ensemble){
			res += p + ", ";
		}
		res +="]";
		return res;
	}

	/**
	 * Calcule le baricentre d'une face donnée
	 * @return le baricentre de la face
	 */
	public float baricentre(){			
		float res = 0;
		for(int i=0;i<ensemble.size();i++){
			res+=ensemble.get(i).getZ();
		}
		return res/ensemble.size();
	}
	
	
	public int[] getXCoord(int width){
		//double maxX = FichierReader.widthMax;
		//double echelle = width/maxX;
		int[] res = new int[ensemble.size()];
		double width2 =  (FichierReader.widthMax - FichierReader.widthMin);
		List<Point> pts = this.getEnsemble();
		int v = 0;
		while(v<pts.size()){
			res[v] = (int)(width/2+pts.get(v).getX()*400/width2);//originale
			//res[v] = (int)(pts.get(v).getX()*echelle); // test centrage
			v++;
		}

		return res;


	}
	
	public int[] getYCoord(int height){
		int[] res = new int[ensemble.size()];
		//double maxX = FichierReader.heightMax;
		//double echelle = height/maxX;
		double height2 =  (FichierReader.heightMax - FichierReader.heightMin);
		List<Point> pts = this.getEnsemble();
		int v = 0;
		while(v<pts.size()){
			res[v] = (int)(height/2+(-1)*(10+pts.get(v).getY()*400/height2)); //originale
			//res[v] = (int)(pts.get(v).getY()*echelle); // test centrage
			v++;
		}
		return res;
	}

	/**
	 * Vérifie si une face est la même qu'une autre
	 * @param f la face vérifiée
	 * @return vrai si la face est la même
	 */
	public boolean equals(Face f){									
		if(zbuff != f.baricentre()) return false;
		ArrayList<Point> fList = (ArrayList<Point>) f.getEnsemble();
		if(ensemble.size()!=fList.size()) return false;
		for(int i = 0; i<ensemble.size();i++){
			if(!ensemble.get(i).equals(fList.get(i))) return false;
		}
		return true;

	}
}

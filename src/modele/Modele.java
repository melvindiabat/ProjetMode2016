package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Classe représentant le modèle dessiné en 3 Dimensions
 * @author Beuns Vianney
 *
 */

public class Modele extends Observable{

	List<Point> points;
	List<Face> faces;
	public Modele(){}
	public Modele(List<Point> points, List<Face> faces) {
		this.points = points;
		//this.faces = faces;
		this.faces = sortByZBuff(faces);
	}

	public List<Face> sortByZBuff(List<Face> f) {
		int max = f.size();
		List<Face> res = new ArrayList<Face>();
		for (int j = 0; j < max; j++) {
			Face min = f.get(0);
			for (Face i : f) {
				if (i.zbuff <= min.zbuff) {
					min = i;
				}
			}
			res.add(min);
			f.remove(min);
		}
		return res;
	}

	public List<Face> getFaces() {
		return faces;
	}

	public void setFaces(List<Face> faces) {
		this.faces = faces;
		setChanged();
		notifyObservers();
	}

	public List<Point> getPoints() {
		return points;
	}
	
}

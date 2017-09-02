package modele;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant de lire un fichier .PLY afin d'en extraire les informations
 * nécessaires à la conception d'un modèle 3D.
 * @author Beuns Vianney
 *
 */

public class FichierReader {

	final static String FICHIER = Main.fichier; 			//"data/cube.ply"; (pour les tests de la classe)
	public static double widthMax;
	public static double widthMin;
	public static double heightMax;
	public static double heightMin;


	public static List<Point> loadPoint() throws FileNotFoundException {
		List<Point> points = new ArrayList<Point>();
		try {
			int nbPoints = 0;
			boolean flag = false;
			String line ="";
			String sep = " ";
			int i=0;
			BufferedReader read = null;
			try{
				read = new BufferedReader(new FileReader(FICHIER));
			}catch(FileNotFoundException e){
				System.out.println("Vous n'avez pas entre un nom de fichier valide.");
				throw e;
			}
			while ((line = read.readLine()) != null) {
				if(flag){
					i++;
				}
				String[] laLigne = line.split(sep);
				if(i == 1){
					widthMin = Double.parseDouble(laLigne[0]);
					widthMax = widthMin;
					heightMin = Double.parseDouble(laLigne[1]);
					heightMax = heightMin;
				}
				if(laLigne[0].equals("end_header")){
					flag = true;
				}
				else if(laLigne[0].equals("element") && laLigne[1].equals("vertex")){
					nbPoints = Integer.parseInt(laLigne[2]);
				}
				else if(flag && i<=nbPoints){
					points.add(new Point(Double.parseDouble(laLigne[0]),Double.parseDouble(laLigne[1]),Double.parseDouble(laLigne[2])));
					if(widthMin > Double.parseDouble(laLigne[0])) widthMin = Double.parseDouble(laLigne[0]);
					if(widthMax < Double.parseDouble(laLigne[0])) widthMax = Double.parseDouble(laLigne[0]);
					if(heightMin > Double.parseDouble(laLigne[1])) heightMin = Double.parseDouble(laLigne[1]);
					if(heightMax < Double.parseDouble(laLigne[1])) heightMax = Double.parseDouble(laLigne[1]);
				}

			}
			read.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return points;
	}

	public static List<Face> loadFaces() {

		List<Face> listeFace = new ArrayList<Face>();
		List<Point> listePoint=null;
		try {
			listePoint = FichierReader.loadPoint();
		} catch (FileNotFoundException e1) {
			System.exit(0);
		}

		@SuppressWarnings("unused")
		int nbFace = 0;
		int i =0;
		boolean flag= false;
		String line="";
		String sep = " ";
		try {

			BufferedReader read = new BufferedReader(new FileReader(FICHIER));

			while((line = read.readLine()) != null) {	
				if(flag) {
					i++;
				}
				String[] ligneCourante = line.split(sep);
				if (ligneCourante[0].equals("element") && ligneCourante[1].equals("face")) {
					nbFace = Integer.parseInt(ligneCourante[2]);
				}
				else if(ligneCourante[0].equals("end_header")){
					flag = true;
				}
				else if(flag && i>listePoint.size()) {
					Face face = new Face();
					for (int j = 1; j < ligneCourante.length; j++) {
						face.addPoint(listePoint.get(Integer.parseInt(ligneCourante[j])));
					}
					listeFace.add(face);
				}
			}

			read.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return listeFace;

	}

}

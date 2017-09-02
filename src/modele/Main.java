package modele;

import vue.Fenetre;

public class Main {
	public static String fichier = "";
	public static void main(String[] args) {
		try{
			fichier = args[0];
		}catch(Exception e){
			System.out.println("Vous n'avez pas indique un fichier valide");
			e.getMessage();
			System.exit(0);
		}
		Fenetre f = Fenetre.getInstance();
		f.repaint();
	}
}


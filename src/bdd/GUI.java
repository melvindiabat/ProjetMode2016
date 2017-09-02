package bdd;

import java.util.ArrayList;
import java.util.Arrays;

import bdd.gui.FindInterface;
import bdd.gui.InsertInterface;
import bdd.gui.SelectAllInterface;
import bdd.gui.SelectOneInterface;
import bdd.gui.UpdateInterface;
import bdd.sql.Delete;

/**
 * Classe permettant de gérer toutes les options de l'application
 * @author Siocbo
 *
 */
public class GUI {

	
	public static void main(String[] args) {
		if(args.length <= 0){
			System.out.println("Erreur : entrez des arguments");
			return;
		}
		if(args[0].equals("--name")){
			if(args.length >= 2) new SelectOneInterface(args[1]);
			else System.out.println("Entrez un nom de modèle à afficher");
		}else if(args[0].equals("--all")){
			new SelectAllInterface();
		}else if(args[0].equals("--find")){
			if(args.length >= 2){
				ArrayList<String> arguments = new ArrayList<>(Arrays.asList(args));
				arguments.remove(0);
				new FindInterface(arguments.toArray());
			}else System.out.println("Entrez des mots-clés !");
		}else if(args[0].equals("--add")){
			new InsertInterface();
		}else if(args[0].equals("--delete")){
			if(args.length >= 2)	new Delete(args[1]);
			else System.out.println("Entrez un nom à supprimer");
		}else if(args[0].equals("--edit")){
			if(args.length >= 2)	new UpdateInterface(args[1]);
			else System.out.println("Entrez le nom du modèle à modifier");
		}else{
			System.out.println("Erreur : entrez une option valide\n \"--name <Nom modele>\" \t\"--all\"\t\"--find <liste mots clé>\"\n\"--add\"\t\"--delete <nom modele>\""
					+ "\t\"--edit <nom modele>\"");
		}
		return;
	}
	
	
}

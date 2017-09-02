package bdd.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import bdd.sql.SelectOne;

/**
 * Classe qui creer une interface pour afficher un modele
 * @author melvin
 *
 */
public class SelectOneInterface extends JFrame{

	private static final long serialVersionUID = -5393717426956807507L;
	private Container contenu;
	private JLabel name;
	private JLabel pwd;
	private JLabel date;
	private JLabel keywords;
	
	public SelectOneInterface(String nom) {
		contenu = getContentPane();
		contenu.setLayout(new GridLayout(2, 3));
		SelectOne slc = new SelectOne(nom);
		if(slc.isError()){
			name = new JLabel("Error : " + nom + " does not exist.");
			contenu.add(name);
			setPreferredSize(new Dimension(200, 100));
		}else{
			name = new JLabel("Nom : " + slc.getName());
			pwd = new JLabel("Chemin : " + slc.getPwd());
			date = new JLabel("Date de création : " + slc.getDate());
			keywords = new JLabel("Mots-clés : " + slc.getKeywords());
			
			contenu.add(name);
			contenu.add(pwd);
			contenu.add(date);
			contenu.add(keywords);
			setPreferredSize(new Dimension(800, 100));
		}
		setTitle("Base de Donnée - "+ nom);
        setLocation(100,100);
        pack();
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		
	}
}

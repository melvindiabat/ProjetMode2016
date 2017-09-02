package bdd.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bdd.sql.Insert;

/**
 * Classe qui affiche un formulaire permettant l'ajout d'un modele dans la BDD
 * @author melvin
 *
 */
public class InsertInterface extends JFrame{

	private static final long serialVersionUID = -7279755579743542998L;
	private Container contenu;
	private JLabel nom;
	private JTextField nomS;
	private JLabel chemin;
	private JTextField cheminS;
	private JLabel keyword;
	private JTextField keywordS;
	private JLabel error;
	private JButton finished;
	
	public InsertInterface() {
		contenu = getContentPane();
		contenu.setLayout(new GridLayout(3, 3, 5, 2));
		
		nom = new JLabel("Insérez le nom du modèle");
		chemin = new JLabel("Insérez le chemin du modèle");
		keyword = new JLabel("Insérez les mots-clés séparés par des ','");
		
		
		nomS = new JTextField();
		cheminS = new JTextField();
		keywordS = new JTextField();
		
		error = new JLabel();
		
		finished = new JButton("Envoyer");
		
		add(nom);
		add(chemin);
		add(keyword);
		add(nomS);
		add(cheminS);
		add(keywordS);
		add(error);
		add(finished);
		
		finished.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!nomS.getText().equals("") && !cheminS.getText().equals("") && !keywordS.getText().equals("")){
					error.setText("");
					new Insert(nomS.getText(), cheminS.getText(), keywordS.getText());
					error.setText("Modèle ajouté");
					nomS.setText("");
					cheminS.setText("");
					keywordS.setText("");
				}else{
					error.setText("Remplissez tous les champs !");
				}
			}
		});
		
		setTitle("Base de Donnée");
        setPreferredSize(new Dimension(800, 400));
        setLocation(100,100);
        pack();
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
}

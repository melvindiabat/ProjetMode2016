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

import bdd.sql.Update;
/**
 * Classe qui creer une interface pour modifier les details d'un modele
 * @author melvin
 *
 */
public class UpdateInterface extends JFrame {

	private static final long serialVersionUID = -2525450056303979362L;
	private Container contenu;
	private JLabel nom;
	private JTextField nomS;
	private JLabel chemin;
	private JTextField cheminS;
	private JLabel keyword;
	private JTextField keywordS;
	private JLabel error;
	private JButton finished;

	public UpdateInterface(String src) {
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
				new Update(nomS.getText(), cheminS.getText(), keywordS.getText(), src);
				error.setText("Modèle modifié");
				nomS.setText("");
				cheminS.setText("");
				keywordS.setText("");
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



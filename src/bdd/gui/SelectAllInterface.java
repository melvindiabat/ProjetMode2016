package bdd.gui;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bdd.sql.SelectAll;
/**
 * Classe qui creer une interface pour afficher tous les modeles de la BDD
 * @author melvin
 *
 */
public class SelectAllInterface extends JFrame {


	private static final long serialVersionUID = -8851589174266364631L;
	private Container contenu;
	private DefaultListModel<String> dlm;
	private JList<String> list;
	
	
	public SelectAllInterface() {
		contenu = getContentPane();
		
		dlm = new DefaultListModel<>();
		
		list = new JList<>(dlm);
		
		new SelectAll(dlm);
		
		contenu.add(list);
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting()){
					new SelectOneInterface(list.getSelectedValue()).setLocationRelativeTo(contenu);
				}
			}
		});
		
		setTitle("Base de Donnée");
		setPreferredSize(new Dimension(100, 500));
        setLocation(200,200);
        pack();
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}

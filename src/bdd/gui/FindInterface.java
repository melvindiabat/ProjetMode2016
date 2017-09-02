package bdd.gui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bdd.sql.Find;

/**
 * Classe qui creer une interface pour afficher les modeles donnés en parametre
 * @author melvin
 *
 */
public class FindInterface extends JFrame{


	private static final long serialVersionUID = 2470662865913728017L;
	private Container contenu;
	private DefaultListModel<String> dlm;
	private JList<String> list;
	
	public FindInterface(Object[] objects) {
		contenu = getContentPane();
		
		dlm = new DefaultListModel<>();
		
		list = new JList<>(dlm);
		
		new Find(dlm, objects);
		
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

package bdd;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bdd.gui.FindInterface;
import bdd.gui.InsertInterface;
import bdd.gui.SelectAllInterface;
import bdd.gui.SelectOneInterface;
import bdd.gui.UpdateInterface;
import bdd.sql.Delete;

/**
 * Classe permettant de gerer l'interface de la partie BDD
 * @author melvin
 *
 */
public class GUIFrame extends JFrame {

	private Container container;
	private JList<String> functions;
	private DefaultListModel<String> dlm;
	
	public GUIFrame(){
		container = getContentPane();
		dlm = new DefaultListModel<>();
		dlm.addElement("Find");
		dlm.addElement("Ajouter Modèle");
		dlm.addElement("Selectionner tout");
		dlm.addElement("Selectionner modèle");
		dlm.addElement("Modifier modèle");
		dlm.addElement("Supprimer modèle");
		functions = new JList<>(dlm);
		
		container.add(functions);
		
		functions.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting() && functions.getSelectedValue() != null){
					String value = functions.getSelectedValue();
					switch(value){
					case "Find":
						new FindInterface(getArgs());
						break;
					case "Ajouter Modèle":
						new InsertInterface();
						break;
					case "Selectionner tout":
						new SelectAllInterface();
						break;
					case "Selectionner modèle":	
						new SelectOneInterface(getArg());
						break;
					case "Modifier modèle":
						new UpdateInterface(getArg());
						break;
					case "Supprimer modèle":
						new Delete(getArg());
						break;
					default:
							System.out.println("Something went wrong.");
					}
				}
				functions.clearSelection();
			}
		});
		
		setPreferredSize(new Dimension(200, 200));
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
				
	}
	
	private String[] getArgs(){
		String res = JOptionPane.showInputDialog("Liste d'arguments à donner séparés par une virgule \",\" sans espaces");
		return res.split(",");
	}
	
	private String getArg(){
		return JOptionPane.showInputDialog("Le nom du modèle sur lequel vous voulez interagir");
	
	}
	
}

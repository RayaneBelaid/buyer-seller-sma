
package venteLivre;

import jade.core.AID;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class VeudeurLivreGui extends JFrame {	
	private AgentVendeurLivre myAgent;
	
	private JTextField titleField, priceField, etatField, livraisonFiled;
	JComboBox listechoix;
	
	VeudeurLivreGui(AgentVendeurLivre a) {
		super(a.getLocalName());
		//création de l'objet agent
		myAgent = a;
		// céation du panel
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 2));
		//Ajout du titre qu panel
		p.add(new JLabel("Titre du Livre:"));
		titleField = new JTextField(15);
		p.setBounds(380, 0, 800, 50);
		p.add(titleField);
		
		p.add(new JLabel("Prix:"));
		priceField = new JTextField(15);
		p.setBounds(380, 0, 800, 50);
		p.add(priceField);
		
		p.add(new JLabel("Etat"));
		Object[] elements = new Object[]{"Neuf", "Bon état", "Utilisé"};
		listechoix = new JComboBox(elements);
	    listechoix.setForeground(Color.black);
		p.add(listechoix);
		
				
		p.add(new JLabel("Prix de livraison"));
		livraisonFiled = new JTextField(15);
		p.add(livraisonFiled);
		p.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Vente de livres"));
		getContentPane().add(p, BorderLayout.CENTER);
	    //Création du bouton
		JButton addButton = new JButton("Ajouter");
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					String title = titleField.getText().trim();
					String price = priceField.getText().trim();
					String status = listechoix.getSelectedItem().toString().trim();
					String priceL =  livraisonFiled.getText().trim();
					
					myAgent.updateCatalogue(title, Integer.parseInt(price),Integer.parseInt(priceL), status);
					titleField.setText("");
					priceField.setText("");
					livraisonFiled.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(VeudeurLivreGui.this, "Valeur invalide. "+e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );
		p = new JPanel();
		p.add(addButton);
		getContentPane().add(p, BorderLayout.SOUTH);
		
		// Faire terminer l'agent lorsque l'utilisateur ferme l'interface graphique à l'aide du bouton dans le coin supérieur droit	
		addWindowListener(new	WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				myAgent.doDelete();
			}
		} );
		
		setResizable(false);
	}
	
	public void showGui() {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		super.setVisible(true);
	}	
}

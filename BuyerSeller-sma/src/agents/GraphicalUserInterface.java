package agents;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;


import java.awt.*;
import javax.swing.*;



/*#####################################################################################################*/
/*                                                                                                     */
/*                                      INTERFACE GRAPHIQUE                                            */
/*                                                                                                     */
/*#####################################################################################################*/
@SuppressWarnings({ "unused", "serial" })

public class GraphicalUserInterface extends JFrame implements ActionListener {
JFrame fenetre;
JPanel containerPane;
JPanel panMoteur;
JPanel panResultat;
JLabel lab1;
JTextField requete;
JLabel lab2;
JTable table;
@SuppressWarnings("rawtypes")
JComboBox listechoix;
JButton BoutonRecherche;
@SuppressWarnings("rawtypes")
JComboBox EnglishListChoice;
@SuppressWarnings("rawtypes")
static DefaultListModel dlm ;
@SuppressWarnings("rawtypes")
JList listeresultat ;
JScrollPane scroll ;
// Création d'une liste de critères en Français
Object[] elements = new Object[]{"Auteur", "Date de publication", "Contenu", "Mot-clé", "Date de saisie", "Titre", "Résumé ", "Référence"};
@SuppressWarnings({ "unchecked", "rawtypes" })

private AgentVendeur1 myAgent;
public  GraphicalUserInterface(AgentVendeur1 a ) {
	super(a.getLocalName());
	
	myAgent = a;
	
	/*************************************** CREATION DE LA FENETRE *********************************************/
	//création de la fenêtre 
	fenetre = new JFrame("Fenêtre d'enchere");

	
	/*************************************** CREATION DU PANEL GLOBAL *********************************************/
	// Créer le panel global
  containerPane = new JPanel();
  // Déviser le panel en 2 Layout 
  containerPane.setLayout(new GridLayout(1, 2));

  
  /*************************************** CREATION DU PANEL MOTEUR *********************************************/
  // Créer le panel « panMoteur » à gauche de la fenêtre qui doit contenir un champ pour de saisie de la recherche et un bouton
  panMoteur = new JPanel();
  panMoteur.setLayout(null);
  // Créer des bordures bleue autour du « panMoteur » 
  panMoteur.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Vente enchere"));
  // L'ajout du panMoteur au containerPane (le panel global)
  containerPane.add(panMoteur);

  
  /*************************************** CREATION DU PANEL RESULTAT ********************************************/
  // Créer le panel « panResultat » à droite de la fenêtre qui doit contenir un champ pour afficher les résultats
  panResultat = new JPanel();
  panResultat.setLayout(new GridLayout());
  // Créer des bordures bleue autour du « panMoteur »
  panResultat.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Acheteurs"));
  // L'ajout du panResultat au containerPane (le panel global)
  containerPane.add(panResultat);

  
  /*************************************** CREATION DE LA LISTE DES RESULTATS ***************************************/
  // créer une liste vertical pour l'affichge des résultats 
  dlm = new DefaultListModel();
  listeresultat = new JList(dlm);
  listeresultat.setLayoutOrientation(JList.VERTICAL);
  // Créer un Scroll pour pouvoir défiler dans la liste 
  scroll = new JScrollPane(listeresultat);
  scroll.setViewportView(listeresultat);
  //L'ajout du Scroll au PanResultat
	panResultat.add(scroll);
  //Définir un police et un style 
  Font font1 = new Font ("Garamond", Font.BOLD, 20); 

   table = new JTable();
   panResultat.add(table);
  
  /*************************************** CREATION DE LABEL RECHERCHE *********************************************/
  // Créer un laber " Recherche "
	lab1 = new JLabel ("Vendeur :");
	//Lui Associer des dimensions
	lab1.setBounds(10, 90, 300, 15);
	//Lui Associer le Font
	lab1.setFont(font1); 
	//Lui Associer une couleur
	lab1.setForeground(Color.black);
	// L'ajout du label "Recherche" au « panMoteur »
  panMoteur.add(lab1);
  
//Créer un laber " Recherche "
	lab1 = new JLabel ("Designation :");
	//Lui Associer des dimensions
	lab1.setBounds(10, 90, 300, 100);
	//Lui Associer le Font
	lab1.setFont(font1); 
	//Lui Associer une couleur
	lab1.setForeground(Color.black);
	// L'ajout du label "Recherche" au « panMoteur »
    panMoteur.add(lab1);

 // Créer un laber " Recherche "
 	lab1 = new JLabel ("Prix :");
 	//Lui Associer des dimensions
 	lab1.setBounds(10, 90, 300, 200);
 	//Lui Associer le Font
 	lab1.setFont(font1); 
 	//Lui Associer une couleur
 	lab1.setForeground(Color.black);
 	// L'ajout du label "Recherche" au « panMoteur »
   panMoteur.add(lab1);
   
   // Créer un laber " Recherche "
	lab1 = new JLabel ("Etat :");
	//Lui Associer des dimensions
	lab1.setBounds(10, 90, 300, 300);
	//Lui Associer le Font
	lab1.setFont(font1); 
	//Lui Associer une couleur
	lab1.setForeground(Color.black);
	// L'ajout du label "Recherche" au « panMoteur »
  panMoteur.add(lab1);
  
   
   
  /*************************************** CREATION DE CHAMP DE SAISIE *********************************************/
 
  // Créer un chmap de saisie "JTextField"
    requete= new JTextField("saisir");
  //Lui Associer des dimensions
    requete.setBounds(120, 80, 370, 35);
    requete.setFont(font1);
  //Ajout du place holder au champ de saisie "JTextField"
  requete.addFocusListener(new FocusListener(){
      @Override
      public void focusGained(FocusEvent e){
          if (requete.getText().equals("Saisir une requete")){
          	requete.setText("");
          	requete.setForeground(Color.black);
          	requete.setFont(font1);
        }
      }
      @Override
      public void focusLost(FocusEvent e) {
          if (requete.getText().isEmpty()) {
          	requete.setForeground(Color.gray);
          	requete.setFont(font1);
          	requete.setText("Saisir une requete");
          }
        }
     });

  
  // L'ajout de "requete" le champ de saisie au « panMoteur »
	panMoteur.add(requete);

	
	/*************************************** CREATION DE LABEL CRITERE *********************************************/

	
	/*************************************** CREATION DE LA LISTE DEROULANTE ******************************************/

	
	/*************************************** CREATION DE BOUTON RECHERCHER *********************************************/
	//Créer un boutton "JButton"(Rechercher)
	BoutonRecherche = new JButton("Encherer");
	BoutonRecherche.setActionCommand("rechercher");
	//Lui Associer des dimmensions
	BoutonRecherche.setBounds(215, 280, 150, 40);
	//Lui Associer le Font
	BoutonRecherche.setFont(font1);
	BoutonRecherche.addActionListener(this);
	//Lui ajouter au « panMoteur »
	panMoteur.add(BoutonRecherche);

	
	
	BoutonRecherche.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			myAgent.setup();
			
		}
	});
	

	 
	// Récupérer l'icone 
	ImageIcon iconSearchFrame = new ImageIcon("F:\\Informa\\L3-ISIL\\RI\\outils\\icons-chercher-frame.PNG");
	// L'ajout de l'icone à gauche de la barre de la fenêtre 
  fenetre.setIconImage(iconSearchFrame.getImage());
	// Récupérer l'icone
	ImageIcon iconSearch = new ImageIcon("F:\\Informa\\L3-ISIL\\RI\\outils\\icons-chercher-searchBar.PNG");
	//Ajout de l'icone à droite de la barre de recherche
	JLabel label = new JLabel(iconSearch);
	label.setBounds(355, 25, 300, 150);
	// L'ajout du label1 au panMoteur
  panMoteur.add(label);
  // L'ajout du panel global "containerPane" à la fenêtre  
  fenetre.add(containerPane);
  //Définir la dimension de la fenêtre 
  fenetre.setSize(1200, 400);
  fenetre.setVisible(true);
  fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  // Définir au Milieu de l'ecran
  fenetre.setLocationRelativeTo(null); 
	fenetre.setResizable(false);
}


/*#####################################################################################################*/
/*                                                                                                     */
/* actionPerformed :                                                                                   */
/* Cette méthode définit une action sur le bouton "Rechercher", pour affectuer une recherche.          */
/*                                                                                                     */
/*  @inputs:                                                                                           */
/*          ActionEvent e : "e" est un évènement d'un click de bouton.                                 */
/* 		                                                                                            */
/*                                                                                                     */
/*#####################################################################################################*/

public void showGui() {
	
	pack();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	super.setVisible(true);
}


@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
	
/*
public static void main (String[] args) throws IOException{    

GraphicalUserInterface projet = new GraphicalUserInterface();
}
*/
}

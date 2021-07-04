package agents;

import java.io.IOException;

import javax.swing.JOptionPane;


import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import produit.Produit;

public class AgentVendeur1 extends Agent {
	Produit product = new Produit();
	ACLMessage message;
	long end, start;
	int AgentNumber;
	private GraphicalUserInterface myGui;
	
	protected void setup() {
		// Create and show the GUI 
		//myGui = new GraphicalUserInterface(this);
		//myGui.showGui();
		
		//System.out.println("Deploiment de l'agent "+getAID().getLocalName());
		//assigner un prix pour le produit
		product.prix = 10000;
		//int prixFirst=10000;
		product.designation="Television";
		
		message = new ACLMessage(ACLMessage.INFORM);
		//recuperer l'argument de vendeur (le nombre total des acheteurs)
		Object[] args = getArguments();
		AgentNumber = Integer.valueOf((String)args[0]).intValue(); // recuperer le nombre des agents acheteurs 4
		System.out.println(" nombre d'agent acheteurs = : "+AgentNumber);
		
		// envoyer le prix initiale a tous les agents acheteurs
		for(int i=1; i<=AgentNumber; i++) message.addReceiver(new AID("Acheteur" + i, AID.ISLOCALNAME)); 
			
		try {
			message.setContentObject(product);
			message.setLanguage("JavaSerialization");
			send(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		start= System.currentTimeMillis();
		end = System.currentTimeMillis();
		
	
		ACLMessage message1;
		String winner="";
		//dowait 300
	
		while((end-start) < 3000) {
			message1 = blockingReceive();
			//message1 = receive();
			//System.out.println(" message1 "+message1);
			while(message1 == null && (end-start)<3000) {
				message1 = receive();
				//calculer la duree
				end = System.currentTimeMillis();
			}
			if(message1 != null) {
				start = System.currentTimeMillis();	
				//System.out.println(" start"+start);
			try {
				//recuperer les prix proposer par l'acheteur
				Produit product1 = (Produit) message1.getContentObject();
		
				//verifier si le prix proposer par l'acheteur > prix du vendeur
				if(product1.prix > product.prix) {
					// donc c'est le gangnant
					//recuperer son nom
					winner = message1.getSender().getLocalName();
					System.out.println("\n Agent Vendeur : le prix de l'article est ==> "+product1.prix+" DA");
					
					try {
						product = product1;
						message.setContentObject(product);
						message.setLanguage("JavaSerialization");
						doWait(300);
						send(message);
					    } catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					    }
			      }
				
			    } catch (UnreadableException e) {
				// TODO Auto-generated catch block
				System.out.println(getLocalName() +"Catched exception"+ e.getMessage());
			    }	
		    }
			if((end-start)>= 3000) {
				
				//JOptionPane.showMessageDialog("\nFin d'enchere, \n Temps : "+ (end-start)+ "\n l'article vendu : " +winner +" avec le prix " +product.prix+" DA");
					System.out.println("\nFin d'enchere, \n Temps : "+ (end-start)+ "\n l'article vendu : " +winner +" avec le prix " +product.prix+" DA");
				}
			
		}	
		
	}	
}

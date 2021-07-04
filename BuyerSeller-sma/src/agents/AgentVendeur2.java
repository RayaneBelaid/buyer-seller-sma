package agents;

import java.io.IOException;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import produit.Produit;

public class AgentVendeur2 extends Agent {
	/*
	Produit product = new Produit();
	ACLMessage message;
	String etat;
	double prix;
	double livraison;
	String winner="";
	protected void setup() {

		Object[] args = getArguments();
		//recuperer le prix de livraison
		livraison = Float.valueOf((String) args[0]).floatValue();
		//recuperer le prix du produit
		prix = Double.valueOf((String) args[1]).doubleValue();
		//recuperer l'etat du produit
		etat = (String) args[3];
		
		message = new ACLMessage(ACLMessage.INFORM);
		
		// envoyer les info du produit a l'acheteur
		try {
			message.setContentObject(args);
			message.setLanguage("JavaSerialization");
			send(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		ACLMessage message1;
			message1 = blockingReceive();
			//message1 = receive();
			//System.out.println(" message1 "+message1);
			while(message1 == null) {
				message1 = receive();
				
			}
			if(message1 != null) {
			try {
				//recuperer les prix proposer par l'acheteur
				Produit product1 = (Produit) message1.getContentObject();
		
				//verifier si le prix proposer par l'acheteur > prix du vendeur
				if(product1.prix == prix) {
					
					winner = message1.getSender().getLocalName();
					System.out.println("\n Agent Vendeur : L'article est vendu a "+winner+" avec le prix ==> " +prix);
					
			      }
				
			    } catch (UnreadableException e) {
				// TODO Auto-generated catch block
				System.out.println(getLocalName() +"Catched exception"+ e.getMessage());
			    }	
		   
		}
		
		
		
		
	}	
	*/
}

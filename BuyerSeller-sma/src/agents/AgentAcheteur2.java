package agents;

import java.io.IOException;
import java.util.Random;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import produit.Produit;

public class AgentAcheteur2 extends Agent {
/*	
	double prix= 0;
	String etat;
	double livraison;
	Produit product = new Produit();
	ACLMessage message;
	int AgentNumber;
	String winner="";
	 
	protected void setup() {
		
		message = new ACLMessage(ACLMessage.INFORM);
		//recuperer l'argument de vendeur (le nombre total des acheteurs)
		Object[] args = getArguments();
		AgentNumber = Integer.valueOf((String)args[0]).intValue(); // recuperer le nombre des agents vendeurs 4
		System.out.println(" nombre d'agent vendeurs = : "+AgentNumber);
		
		// envoyer le prix initiale a tous les agents acheteurs
		for(int i=1; i<=AgentNumber; i++) message.addReceiver(new AID("Vendeur" + i, AID.ISLOCALNAME)); 
	
		try {
			message.setContentObject(product);
			message.setLanguage("JavaSerialization");
			send(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ACLMessage message1;
		message1 = blockingReceive();
		while(message1 == null ) {
			message1 = receive();
		}
		if(message1 != null) {
			
		try {
			//recuperer les prix proposer par les vendeurs
			Produit product1 = (Produit) message1.getContentObject();
	         
			//verifier si le prix proposer par l'acheteur > prix du vendeur
			if((product1.prix + product1.livraison) < 20000) {
				//donc c'est le gangnant
				//recuperer son nom
				winner = message1.getSender().getLocalName();
				System.out.println("\n Agent Acheteur : J'achete l'article de "+winner+" son  prix est ==>"+product.prix);
				
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

	}
*/
}

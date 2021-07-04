
package venteLivre;

import java.util.Hashtable;
import java.util.Random;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class AgentVendeurLivre extends Agent {
	String title;
	Integer price;
	Integer priceL;
	String status;

	//Le catalogue de livres à vendre (fait correspondre le titre d'un livre à son prix)
	private Hashtable catalogue;
	//L'interface graphique sur laquelle l'utilisateur peut ajouter des livres dans le catalogue
	private VeudeurLivreGui myGui;
	Random rand = new Random();
	private int sold = rand.nextInt(100) + 1;;
	
	
	// Initialisation de l'agent
	protected void setup() {
		// creation du catalogue
		catalogue = new Hashtable();

		// creation et affichage de l'interface graphique
		myGui = new VeudeurLivreGui(this);
		myGui.showGui();

		// Register the book-selling service in the yellow pages
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("book-selling");
		sd.setName("JADE-book-trading");
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		}
		catch (FIPAException fe) {
			fe.printStackTrace();
		}

		//Ajout du comportement servant les requêtes des agents acheteurs
		addBehaviour(new OfferRequestsServer());

		//Ajouter le comportement servant les bons de commande des agents acheteurs
		addBehaviour(new PurchaseOrdersServer());
	}

	// fin de processus de l'agent
	protected void takeDown() {
		// Deregister from the yellow pages
		try {
			DFService.deregister(this);
		}
		catch (FIPAException fe) {
			fe.printStackTrace();
		}
		// fermer l'interface graphique
		myGui.dispose();
		// affichage de message que a terminé
		System.out.println("Agent-Vendeur "+getAID().getName().split("@")[0]+" est Terminé.");
	}

	/**
      Ceci est invoqué par l'interface graphique lorsque l'utilisateur ajoute un nouveau livre à vendre
	 */
	public void updateCatalogue(final String title, final int price, final int priceL, final String status) {
		
		addBehaviour(new OneShotBehaviour() {
			public void action() {
				catalogue.put(title, new Integer(price));
				catalogue.put(priceL, new Integer(priceL));
				catalogue.put(status, new String(status));
			
				
				System.out.println("\n"+getAID().getName().split("@")[0]+" : L'article "+title+" est ajouté au catalogue. \n=> Prix = "+price+" DA"+" \n=> Prix de livraison = "+priceL+" DA"+" \n=> Etat "+status);
			}
		} );
		
     
	}

	
	private class OfferRequestsServer extends CyclicBehaviour {
		public void action() {
			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CFP);
			ACLMessage msg = myAgent.receive(mt);
			//System.out.println("getContent::00-"+msg);
			if (msg != null) {
				//System.out.println("getContent::"+msg.getContent());
				if(msg.getContent().equals("sold")){
					int p = (price.intValue()- sold);
					ACLMessage soldreply = msg.createReply();
					System.out.println("\n"+getAID().getName().split("@")[0]+": Oui, nous avons une première promo client sur le livre :"+title+" le prix final sera "+p);
					soldreply.setPerformative(ACLMessage.PROPOSE);
					soldreply.setContent(String.valueOf(p));	
					//System.out.println("sent:"+soldreply);
					myAgent.send(soldreply);
					//System.out.println("donnnne");
					
				}
				// CFP Message recu. Process it
				ACLMessage reply = msg.createReply();
				title = msg.getContent();
				price = (Integer) catalogue.get(title);
				//System.out.println("get price"+price);
				//priceL = (Integer) catalogue.get(title);
				//status = (String) catalogue.get(title);
				
				if (price != null) {
					reply.setPerformative(ACLMessage.PROPOSE);
					reply.setContent(String.valueOf(price.intValue()));
					System.out.println("\n"+getAID().getName().split("@")[0]+": Bonjour, le prix du livre "+title+" est= "+price+" Livraison = "+priceL+" Etat "+status);
}
				
				else {
					
					// The requested book is NOT available for sale.
					reply.setPerformative(ACLMessage.REFUSE);
					reply.setContent("not-available");
				}
				myAgent.send(reply);
			}
			else {
				block();
			}
		}
	}  // fin de la classe OfferRequestsServer

	/**************************************************************************************************
	   Classe interne PurchaseOrdersServer. Il s'agit du comportement utilisé par les agents 
	   libraires pour servir les acceptations d'offres entrantes (c'est-à-dire les commandes d'achat) 
	   des agents acheteurs. L'agent vendeur supprime le livre acheté de son catalogue et répond 
	   par un message INFORM pour informer l'acheteur que l'achat a été achevé avec succès.
	   
	 **************************************************************************************************/
	private class PurchaseOrdersServer extends CyclicBehaviour {
		public void action() {
			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL);
			ACLMessage msg = myAgent.receive(mt);
			if (msg != null) {
				// ACCEPT_PROPOSAL Message recu. Process it
				String title = msg.getContent();
				ACLMessage reply = msg.createReply();

				Integer price = (Integer) catalogue.remove(title);
				if (price != null) {
					reply.setPerformative(ACLMessage.INFORM);
					System.out.println("\n**********************************************************************");
					System.out.println("\n"+title+" vendu à l'agent "+msg.getSender().getLocalName());
				}
				else {
					//Entre-temps, le livre demandé a été vendu à un autre acheteur.
					reply.setPerformative(ACLMessage.FAILURE);
					reply.setContent("not-available");
				}
				myAgent.send(reply);
			}
			else {
				block();
			}
		}
	}  // fin de la classe OfferRequestsServer
}

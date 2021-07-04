

package venteLivre;

import jade.core.Agent;

import javax.swing.JOptionPane;

import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class AgentAcheteurLivre extends Agent {
	private String targetBookTitle;
	private AID[] sellerAgents;
	private AID[] BestSellerAgents;
	protected void setup() {
		System.out.println("\n**********************************************************************");
		System.out.println(getAID().getLocalName()+" Bonjour! Je suis un client.");
		Object[] args = getArguments();
		if (args != null && args.length > 0) {
			targetBookTitle = (String) args[0];
			System.out.println("Je souhaite acheter le livre : "+targetBookTitle);

			//Ajout de TickerBehaviour qui prepare une req a l'agent acheteur chaque minute 
			addBehaviour(new TickerBehaviour(this, 15000) {
				protected void onTick() {
					//System.out.println("Entrain d'acheter "+targetBookTitle);
					// mettre a jour la liste des agents acheteurs
					DFAgentDescription template = new DFAgentDescription();
					ServiceDescription sd = new ServiceDescription();
					sd.setType("book-selling");
					template.addServices(sd);
					try {
						DFAgentDescription[] result = DFService.search(myAgent, template); 
						System.out.println("\n**********************************************************************");
						System.out.println("Les agents Vendeurs trouvés :");
						sellerAgents = new AID[result.length];
						 BestSellerAgents = new AID[3];

						for (int i = 0; i < result.length; ++i) {
							sellerAgents[i] = result[i].getName();
							System.out.println("=> "+sellerAgents[i].getLocalName());
						}
					}
					catch (FIPAException fe) {
						fe.printStackTrace();
					}

					// effectuer la demande 
					myAgent.addBehaviour(new RequestPerformer());
				}
			} );
		}
		else {
			// arreter le processu de l'agent
			System.out.println("Le livre demandé n'est pas disponible");
			doDelete();
		}
	}

	//terminer le processu
	protected void takeDown() {
		// afficher le message d'arret
		System.out.println("Agent-Acheteur "+getAID().getName()+" terminé.");
	}

	
	private class RequestPerformer extends Behaviour {
		private AID bestSeller; // L'gent qui propose la meilleure offre
		private int bestPrice;  // meilleur prix
		private ACLMessage reply2;
		private int nbestPrice; // The best offered price
		private int repliesCnt = 0; //le conpteur pour les reponces de l'agent acheteur
		private MessageTemplate mt;
		private MessageTemplate mt1;// The template to receive replies
		private int step = 0;
		int i=0;
		public void action() {
			switch (step) {
			case 0:
				// envoyer cfp a tous les agents acheteurs
				ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
				for (int i = 0; i < sellerAgents.length; ++i) {
					cfp.addReceiver(sellerAgents[i]);
				} 
				cfp.setContent(targetBookTitle);
				cfp.setConversationId("book-trade");
				cfp.setReplyWith("cfp"+System.currentTimeMillis()); // Unique value
				System.out.println("\n**********************************************************************");
				System.out.println("Acheteur: Bonjour, Je veux acheter le livre intitulé "+targetBookTitle+" vous l'avez?");

				myAgent.send(cfp);
				// preparer le template pour recevoir les propositions
				mt = MessageTemplate.and(MessageTemplate.MatchConversationId("book-trade"),
						MessageTemplate.MatchInReplyTo(cfp.getReplyWith()));
				step = 1;
				break;
			case 1:
				//Recevoir toutes les proposition/refus par les agents acheteurs
				ACLMessage reply = myAgent.receive(mt);
				if (reply != null) {
					// reponse recu
					
					
					if (reply.getPerformative() == ACLMessage.PROPOSE) {
						// This is an offer, recuperer le prix
						int price = Integer.parseInt(reply.getContent());
						BestSellerAgents[i]=reply.getSender();
						//System.out.println("---------------"+i+"----"+BestSellerAgents[i]);
						i++;
						if (bestSeller == null || price < bestPrice) {
							//donc c'est la meilleur offre
							bestPrice = price;
							bestSeller = reply.getSender();
						}
					}
					repliesCnt++;
					if (repliesCnt >= sellerAgents.length) {
						// We received all replies
						step = 2; 
					}
				}
				else {
					block();
				}
				break;
			case 2:			
				//Envoyer le cfp a tous les agents acheteur
				ACLMessage cfp1 = new ACLMessage(ACLMessage.CFP);
				for (int i = 0; i < BestSellerAgents.length; ++i) {
					cfp1.addReceiver(BestSellerAgents[i]);
					//System.out.println("++++++++"+i+"+++++"+BestSellerAgents[i]);

				} 
				//cfp1.addReceiver(bestSeller);
				cfp1.setContent("sold");
				cfp1.setConversationId("book-trade");
				cfp1.setReplyWith("cfp"+System.currentTimeMillis()); // Unique value
				System.out.println("\n**********************************************************************");
				System.out.println("Acheteur: puis-je avoir une réduction pour le livre "+targetBookTitle+"?");
				bestSeller= null;
				repliesCnt=0;
				myAgent.send(cfp1);
				// Prepare the template to get proposals
				mt1 = MessageTemplate.and(MessageTemplate.MatchConversationId("book-trade"),
						MessageTemplate.MatchInReplyTo(cfp1.getReplyWith()));
				step = 3;
				break;
			case 3:
				//reccevoir tous les propositions/refus par les agents acheteurs
				reply2 = myAgent.receive(mt1);
				//System.out.println("+++++++reply2 buyer++"+reply2);
				if (reply2 != null) {
					// Reply received
					//System.out.println("+++++++reply2 != null++"+reply2.getSender());

					if (reply2.getPerformative() == ACLMessage.PROPOSE) {
						// This is an offer 
						int price = Integer.parseInt(reply2.getContent());
						//System.out.println("++++++++price+++++"+price);
						
						if (bestSeller == null || price < nbestPrice) {
							//alors c'est le meilleur offre
							nbestPrice = price;
							bestSeller = reply2.getSender();
							//System.out.println("+++++++bestSeller+++"+bestSeller);

						}
					}
					repliesCnt++;
					if (repliesCnt >= sellerAgents.length) {
						// nous avons recu tous les offres
						step = 4; 
					}
				}
				else {
					block();
				}
				break;
				
			case 4:
				//envoyer la bonne commande aux acheteurs qui propose la bonne offre
				ACLMessage order = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
				order.addReceiver(bestSeller);
				order.setContent(targetBookTitle);
				order.setConversationId("book-trade");
				order.setReplyWith("order"+System.currentTimeMillis());
				myAgent.send(order);
	
				//preparer le template pour recevoir la bonne commande
				mt = MessageTemplate.and(MessageTemplate.MatchConversationId("book-trade"),
						MessageTemplate.MatchInReplyTo(order.getReplyWith()));
				step = 5;
				break;
			case 5:      
				// recevoir la reponse qui contient la bonne commande
				reply = myAgent.receive(mt);
				if (reply != null) {
					// la bonne commande a ete recu
					if (reply.getPerformative() == ACLMessage. INFORM) {
						//Achat reussi, nous pouvons terminer
						System.out.println("\n**********************************************************************");
						System.out.println("=> "+targetBookTitle+" acheté avec succès auprès de l'agent "+reply.getSender().getLocalName());
						System.out.println("Prix = "+nbestPrice);
						JOptionPane.showMessageDialog(null, "acheté avec succès auprès de l'agent: "+reply.getSender().getLocalName().split("@")[0]+" avec un prix de  "+nbestPrice);
						myAgent.doDelete();
					}
					else {
						System.out.println("c: livre demandé déjà vendu.");
					}

					step = 6;
				}
				else {
					block();
					
				}
				break;
			}        
		}

		public boolean done() {
			if (step == 2 && bestSeller == null) {
				System.out.println("\n**********************************************************************");
				System.out.println("Échec de la tentative: "+targetBookTitle+" non disponible à la vente");
			}
			return ((step == 2 && bestSeller == null) || step == 6);
		}
	}  // fin de la classe RequestPerformer
}

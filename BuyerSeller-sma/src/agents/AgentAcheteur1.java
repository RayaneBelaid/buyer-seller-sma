package agents;

import java.io.IOException;
import java.util.Random;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import produit.Produit;

public class AgentAcheteur1 extends Agent {
	
	float mise;
	double prixMax, prix =0;
	Random v= new Random();
	int mis1=v.nextInt(50);
	 
	protected void setup() {
		//System.out.println("Deploiment de l'agent "+getAID().getLocalName());
		Object[] args = getArguments();
		//recuperer la mise
		mise = Float.valueOf((String) args[0]).floatValue();
		//recuperer le prix max
		prixMax = Double.valueOf((String) args[1]).doubleValue();
		
		addBehaviour(new CyclicBehaviour(this) {
			
			@Override
			public void action() {
				ACLMessage message;
				Produit product;
				message = receive();
				if(message != null) {
					try {
						//recuperer le prix recu par le vendeur
						product = (Produit) message.getContentObject();
						if(product.prix > prix) {
							//rajouter la remise
							product.prix = product.prix + mise;
							prix = product.prix;
							//verifiant si le prix apres l'ajout de le remise est <= a prix max 
							if(product.prix <= prixMax) {
								System.out.println("********************************************\n Agent " +getLocalName()+ " propose le prix " +prix);
								
								try {
									
									// renvoyer le prix proposer par l'acheteur au vendeur
									ACLMessage reply = message.createReply();
									reply.setPerformative(ACLMessage.INFORM);
									reply.setContentObject(product);
									reply.setLanguage("JavaSerialization");
									send(reply);
									doWait(300);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							}
					} catch (UnreadableException e) {
						System.err.println(getLocalName() + "Catched exception"+ e.getMessage());
					}
					block();
				}
				
				
			}
		});

	}

}

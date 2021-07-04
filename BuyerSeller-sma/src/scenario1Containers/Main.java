package scenario1Containers;


import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;


public class Main {

	public static void main(String[] args) throws ControllerException {
		
		Runtime runtime=Runtime.instance();
		ProfileImpl configuration=new ProfileImpl();
		//ContainerController containerController=runtime.createAgentContainer(configuration);
		AgentContainer containerController=(AgentContainer) runtime.createAgentContainer(configuration);
		AgentController ac;
		try {
			ac=containerController.createNewAgent("Vendeur 1", "venteLivre.AgentVendeurLivre", null);
			ac.start();
			ac=containerController.createNewAgent("Vendeur 2", "venteLivre.AgentVendeurLivre", null);
			ac.start();
			ac=containerController.createNewAgent("Vendeur 3", "venteLivre.AgentVendeurLivre", null);
			ac.start();
			containerController.start();
		} catch (StaleProxyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		//}
		}
	
	}

}

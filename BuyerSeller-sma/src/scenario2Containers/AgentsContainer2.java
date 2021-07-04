package scenario2Containers;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;

public class AgentsContainer2 {
	
	public static void main(String[] args) throws ControllerException{
		
		
		Object[] arg1 =new Object[3];
		Object[] arg2 =new Object[3];
		Object[] arg3 =new Object[3];
		Object[] arg4 =new Object[3];
		Object[] argv =new Object[1];
		
		Runtime runtime=Runtime.instance();
		ProfileImpl configuration=new ProfileImpl();
		//ContainerController containerController=runtime.createAgentContainer(configuration);
		AgentContainer containerController=(AgentContainer) runtime.createAgentContainer(configuration);
		arg1[0] = "600"; // livraison
		arg1[1] = "20000"; // prix
		arg1[2] = "Neuf"; // qualite
		AgentController agentAcheteur1=containerController.createNewAgent("Vendeur1", "agents.AgentVendeur2", arg1);
	
		arg2[0] = "500"; // livraison
		arg2[1] = "15000"; // prix
		arg2[2] = "Bon état"; // qualite
		AgentController agentAcheteur2=containerController.createNewAgent("Vendeur2", "agents.AgentVendeur2", arg2);
		

		arg3[0] = "600"; // livraison
		arg3[1] = "15000"; // prix
		arg3[2] = "Utilisé"; // qualite
		AgentController agentAcheteur3=containerController.createNewAgent("Vendeur3", "agents.AgentVendeur2", arg3);
		
		arg4[0] = "700"; // livraison
		arg4[1] = "25000"; // prix
		arg4[2] = "Neuf"; // qualite
		AgentController agentAcheteur4=containerController.createNewAgent("Vendeur4", "agents.AgentVendeur2", arg4);
		
	
		argv[0] = "4"; // on lui transmet le nombre de vendeurs
		AgentController agentVendeur=containerController.createNewAgent("Acheteur", "agents.AgentVendeur2", argv);
		
		containerController.start();
		agentVendeur.start();
		agentAcheteur1.start();
		agentAcheteur2.start();
		agentAcheteur3.start();
		agentAcheteur4.start();
		
	}

}

package scenario2Containers;

import jade.core.ProfileImpl;

import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;

public class AgentsContainer {
	
	public static void main(String[] args) throws ControllerException{
		
		
		Object[] arg1 =new Object[2];
		Object[] arg2 =new Object[2];
		Object[] arg3 =new Object[2];
		Object[] arg4 =new Object[2];
		Object[] argv =new Object[1];
		
		Runtime runtime=Runtime.instance();
		ProfileImpl configuration=new ProfileImpl();
		//ContainerController containerController=runtime.createAgentContainer(configuration);
		AgentContainer containerController=(AgentContainer) runtime.createAgentContainer(configuration);
		arg1[0] = "600"; // la remise
		arg1[1] = "20000"; // son prix max
		AgentController agentAcheteur1=containerController.createNewAgent("Acheteur1", "agents.AgentAcheteur1", arg1);
	
		arg2[0] = "500";
		arg2[1] = "22000";
		AgentController agentAcheteur2=containerController.createNewAgent("Acheteur2", "agents.AgentAcheteur1", arg2);
		

		arg3[0] = "500";
		arg3[1] = "72000";
		AgentController agentAcheteur3=containerController.createNewAgent("Acheteur3", "agents.AgentAcheteur1", arg3);
		
		arg4[0] = "520";
		arg4[1] = "21000";
		AgentController agentAcheteur4=containerController.createNewAgent("Acheteur4", "agents.AgentAcheteur1", arg4);
		
	
		argv[0] = "4"; // on lui transmet le nombre d'acheteur
		AgentController agentVendeur=containerController.createNewAgent("Vendeur", "agents.AgentVendeur1", argv);
		
		containerController.start();
		agentVendeur.start();
		agentAcheteur1.start();
		agentAcheteur2.start();
		agentAcheteur3.start();
		agentAcheteur4.start();
		
	}

}

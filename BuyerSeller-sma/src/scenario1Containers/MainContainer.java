package scenario1Containers;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;


public class MainContainer {
	public static void main(String[] args) throws Exception {
		Runtime runtime=Runtime.instance();
		ProfileImpl configuration=new ProfileImpl();
		configuration.setParameter(ProfileImpl.GUI,"true");
		AgentContainer mainContainer= runtime.createMainContainer(configuration);
		mainContainer.start();
	}
}

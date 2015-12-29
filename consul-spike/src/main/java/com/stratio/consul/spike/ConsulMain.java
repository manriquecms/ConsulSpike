package com.stratio.consul.spike;

import com.orbitz.consul.Consul;
import com.stratio.consul.spike.logic.ConsulManager;

public class ConsulMain {

	public static void main(String[] args) {
		try {
			
			//Thread.sleep(20000);
			ConsulManager cm = new ConsulManager();
			Consul consul = cm.getConsulInstance();
			
			if (!consul.agentClient().getAgent().getConfig().getServer()) {
				//if is agent
				//If has parameter (manager's ip) then we try to join the cluster 
				if(args!=null && args[0] != null){
					consul.agentClient().join(args[0]);
				}
			}
			
			String[] services = {"zookeeper","kafka"};
			
			for(String serviceName : services){
				if (!consul.agentClient().getAgent().getConfig().getServer()) {
					//if is agent
					// Register health service
					System.out.print("Registering "+serviceName+"... ");
					cm.registerService(serviceName);
					System.out.println("OK");
				}
				// First health on demand
				cm.getServiceInfo(serviceName);
				
				// Second monitoring health
				cm.subscribeService(serviceName);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
	
}

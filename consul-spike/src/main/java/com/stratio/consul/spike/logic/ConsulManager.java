package com.stratio.consul.spike.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.Consul;
import com.orbitz.consul.HealthClient;
import com.orbitz.consul.cache.ConsulCache;
import com.orbitz.consul.cache.ServiceHealthCache;
import com.orbitz.consul.model.health.HealthCheck;
import com.orbitz.consul.model.health.ServiceHealth;
import com.stratio.consul.spike.model.ServiceConsulConfiguration;
import com.stratio.consul.spike.service.ReadServiceConfiguration;

public class ConsulManager {

	private static final Logger LOG = LoggerFactory
			.getLogger(ConsulManager.class);

	private Consul consul;

	public ConsulManager() {
		getConsulInstance();
	}
	
	public void registerService(String serviceName) throws Exception{
		ServiceConsulConfiguration config = ReadServiceConfiguration.readJson(serviceName);
		try{
		consul.agentClient().register(
				config.getPort(), 
				config.getCheck().getScript(), 
				config.getCheck().getInterval(), 
				config.getName(), 
				//config.getName()+System.currentTimeMillis(), 
				config.getName(),
				config.getTags());
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	public void getServiceInfo(String serviceName) throws Exception{
		try {
			Map<String, String> healthMap = new HashMap<String, String>();
			HealthClient healthClient = consul.healthClient();
			
			List<ServiceHealth> 
					totalNodes = healthClient.getAllServiceInstances(serviceName).getResponse(),
					healthyNodes = healthClient.getHealthyServiceInstances(serviceName).getResponse();
			
			for(ServiceHealth node : totalNodes){
				healthMap.put(node.getNode().getAddress(), "KO");
			}
			
			for (ServiceHealth node : healthyNodes) {
				healthMap.put(node.getNode().getAddress(), "OK");
			}

			System.out.println(serviceName+":");
			
			for(String address : healthMap.keySet()){
				System.out.println(address+" "+healthMap.get(address));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void subscribeService(String serviceName) {
		final String serviceNameSuscribed = serviceName; 
		try {
			// Second monitoring health
			ServiceHealthCache svHealth = ServiceHealthCache.newCache(
					consul.healthClient(), serviceName);
	
			svHealth.addListener(new ConsulCache.Listener<HostAndPort, ServiceHealth>() {
	
				@Override
				public void notify(Map<HostAndPort, ServiceHealth> newValues) {
					System.out.println("NOTIFY!");
					for(ServiceHealth se : newValues.values()){
						System.out.println("ServiceHealth");
						System.out.println(serviceNameSuscribed+" "+se.getNode().getAddress());
						for(HealthCheck hc : se.getChecks()){
							System.out.println(hc.getNode()+": "+hc.getStatus());
						}
					}
				}
			});
			svHealth.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	public Consul getConsulInstance() {
		if (consul == null) {
			consul = Consul.builder().build();
		}
		return consul;
	}

}

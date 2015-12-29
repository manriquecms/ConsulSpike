package com.stratio.consul.spike.service;

import java.io.File;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stratio.consul.spike.model.ServiceConsulConfiguration;

public class ReadServiceConfiguration {
	private static final String PATH = "services/";
	private static final String EXTENSION = ".json";

	@Test
	public void test() {
		System.out.println(readJson("zookeeper"));
		System.out.println(readJson("kafka"));
		System.out.println(readJson("zookeepers"));

	}

	public static ServiceConsulConfiguration readJson(String serviceName) {
		ServiceConsulConfiguration conf = null;
		
		try {

			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(Feature.ALLOW_COMMENTS);

			JsonNode jNode = mapper.readTree(new File(
					ReadServiceConfiguration.class
							.getClassLoader()
							.getResource(
									new StringBuilder(PATH).append(serviceName)
											.append(EXTENSION).toString())
							.getFile()));
			conf = mapper.convertValue(jNode.get("service"),
					ServiceConsulConfiguration.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conf;
	}

}

package com.example.demo.drivers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.azure.data.cosmos.ConnectionPolicy;
import com.azure.data.cosmos.ConsistencyLevel;
import com.azure.data.cosmos.CosmosKeyCredential;
import com.microsoft.azure.spring.data.cosmosdb.config.AbstractCosmosConfiguration;
import com.microsoft.azure.spring.data.cosmosdb.config.CosmosDBConfig;
import com.microsoft.azure.spring.data.cosmosdb.repository.config.EnableCosmosRepositories;

@Configuration
@EnableCosmosRepositories(basePackages = "com.example.demo.repositories")
public class CosmosDriver extends AbstractCosmosConfiguration{

	
	@Value("${azurecosmosdb.uriconnection}")
	private String URIConnection;
	
	@Value("${azurecosmosdb.connectionkey}")
	private String connectionKey;
	
	@Value("${azurecosmosdb.database}")
	private String connectionDBName;
	
	private CosmosKeyCredential keyCredential;
	
	@Bean
	public CosmosDBConfig getConfig()
	{
		this.keyCredential = new CosmosKeyCredential(connectionKey);
		return CosmosDBConfig.builder(URIConnection, this.keyCredential, connectionDBName)
				.consistencyLevel(ConsistencyLevel.SESSION)
                .connectionPolicy(ConnectionPolicy.defaultPolicy()
                .usingMultipleWriteLocations(true))
                .build();
	}
}

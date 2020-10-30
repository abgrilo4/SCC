package com.example.demo.drivers;

import java.awt.Container;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;

@Component
public class BlobDriver {

	@Autowired
	Environment environment;
	
	@Bean
	public CloudBlobClient getCloudBlobClient() throws InvalidKeyException, URISyntaxException
	{
		CloudStorageAccount account = CloudStorageAccount.parse(environment.getProperty("blobstore.connectionstream"));
		return account.createCloudBlobClient();
	}
	
	@Bean
	public CloudBlobContainer getBlobContainer() throws InvalidKeyException, URISyntaxException, StorageException
	{
		CloudBlobContainer container = getCloudBlobClient().getContainerReference(environment.getProperty("mediacontainer"));		
		return container;
	}
	
}



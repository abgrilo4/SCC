package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

@Service
public class MediaService {
	
	private CloudBlobContainer blobDriver;
	
	@Autowired
	public MediaService(CloudBlobContainer blobDriver)
	{
		this.blobDriver = blobDriver;
	}
	
	public String upload(byte[] bytes)
	{
		CloudBlockBlob blob = null;
		String name = Hash.of(bytes);
		
		try {
			blob = blobDriver.getBlockBlobReference(name);
			blob.uploadFromByteArray(bytes, 0, bytes.length);
		}
		catch(Exception i)
		{
			return null;
		}
		
		return name;
	}
	
	public byte[] download(String resourceName)
	{
		CloudBlockBlob blob = null;
		byte[] bytes;

		try {
			blob = blobDriver.getBlockBlobReference(resourceName);
			bytes = new byte[blob.getStreamWriteSizeInBytes()];
			blob.downloadToByteArray(bytes, 0);
		}
		catch(Exception i)
		{
			return null;
		}
		
		return bytes;
	}

}

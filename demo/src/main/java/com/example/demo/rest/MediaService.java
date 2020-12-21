package com.example.demo.rest;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaService {

	private static final String PATH =  "/scc/data";

	@Autowired
	public MediaService()
	{

	}

	public String upload(byte[] bytes)
	{
		String name = Hash.of(bytes);

		try {
			Files.write(Paths.get(PATH + "/" + name), bytes);
		}
		catch(Exception i)
		{
			return null;
		}

		return name;
	}

	public byte[] download(String resourceName)
	{
		byte[] bytes;

		try {
			bytes = Files.readAllBytes(Paths.get(PATH + "/" + resourceName));
		}
		catch(Exception i)
		{
			return null;
		}

		return bytes;
	}

}

package com.revature.main;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.revature.model.ZipCodes;

public class ClientDriver {

	public static void main(String[] args) {
	Client client = ClientBuilder.newClient();	
	WebTarget webTarget= client.target(
			"http://localhost:8080/ZipCodeRestService/rest/zipcodes");
	//GET Request
	Builder getZipCodeBuilder = webTarget.path("33559").request();
	Response getZipCodeResponse = 
			getZipCodeBuilder.accept(MediaType.APPLICATION_JSON).get();
	int statusCode = getZipCodeResponse.getStatus();
	System.out.println("Returned with status code: " + statusCode);
	ZipCodes z=getZipCodeResponse.readEntity(ZipCodes.class);
	System.out.println(z);
	System.out.println("************************************");
	}

}

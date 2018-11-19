package com.os.calc2;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Calc2Application {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		registerServer();
		SpringApplication.run(Calc2Application.class, args);
	}
	private static void registerServer() throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost("http://127.0.0.1:3000/registerServer");
		StringEntity input = new StringEntity("{\"Server2\":{\"IP\":\"127.0.0.1\",\"services\":[\"GetDifferenceRequest\",\"GetSquareRequest\"],\"port\":8081}}");
		input.setContentType("application/json");
		postRequest.setEntity(input);
		
		HttpResponse response = httpClient.execute(postRequest);
		
		if(response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code:"+response.getStatusLine().getStatusCode());
		}
	}
}

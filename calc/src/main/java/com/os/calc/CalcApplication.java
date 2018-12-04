package com.os.calc;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.util.concurrent.TimeUnit;

import javax.management.RuntimeErrorException;
import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CalcApplication {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		SpringApplication.run(CalcApplication.class, args);
		while(true) {
			registerServer();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void registerServer() throws ClientProtocolException, IOException {
		
		// Check which registry server is available to handle the registration
		// Prefer registry at port 3000. If not then the one at 3001
		int port = 3000;
		if(!works(port)) { port = 3001;}
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost postRequest = null;
		postRequest = new HttpPost("http://127.0.0.1:"+Integer.toString(port)+"/registerServer");
		StringEntity input = new StringEntity("{\"Server1\":{\"URL\":\"http://127.0.0.1:8080/soapWS/calc.wsdl\",\"services\":[\"GetProductRequest\",\"GetSquareRequest\",\"GetSumRequest\"], \"load\":"+Integer.toString(calcEndpoint.load)+"}}");
		input.setContentType("application/json");
		postRequest.setEntity(input);
		
		HttpResponse response = httpClient.execute(postRequest);
		if(response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code:"+response.getStatusLine().getStatusCode());
		}
	}
	
	private static boolean works(int port) {
		Socket s = null;
		try {
			s = new Socket("127.0.0.1", port);
			return true;
		}
		catch(Exception e) {
			return false;
		}
		finally
		{
			if(s!=null)
				try {s.close();}
				catch(Exception e) {}
		}
	}
}
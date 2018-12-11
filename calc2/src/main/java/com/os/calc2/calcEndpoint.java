package com.os.calc2;


import java.util.concurrent.TimeUnit;

import org.example.calculatordescription.GetDifferenceRequest;
import org.example.calculatordescription.GetDifferenceResponse;
import org.example.calculatordescription.GetSquareRequest;
import org.example.calculatordescription.GetSquareResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class calcEndpoint {
	static volatile int load = 0; 
	
	@PayloadRoot(namespace = "http://www.example.org/calculatorDescription", localPart = "getSquareRequest")
	@ResponsePayload
	public GetSquareResponse getSquareRequest(@RequestPayload GetSquareRequest request){
		
		GetSquareResponse response = new GetSquareResponse();
		int a = request.getVar1();
		response.setSquare(a*a);
		load+=10;
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		load-=10;
		return response;
	}
	
	@PayloadRoot(namespace = "http://www.example.org/calculatorDescription", localPart = "getDifferenceRequest")
	@ResponsePayload
	public GetDifferenceResponse getDifferenceRequest(@RequestPayload GetDifferenceRequest request){
		
		GetDifferenceResponse response = new GetDifferenceResponse();
		int a = request.getVar1();
		int b = request.getVar2();
		response.setDifference(a-b);
		load+=5;
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		load-=5;
		return response;
	}
	
}

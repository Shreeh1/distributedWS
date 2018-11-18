package com.os.calc4;


import org.example.calculatordescription.GetCubeRequest;
import org.example.calculatordescription.GetCubeResponse;
import org.example.calculatordescription.GetDivideRequest;
import org.example.calculatordescription.GetDivideResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class calcEndpoint {
	
	@PayloadRoot(namespace = "http://www.example.org/calculatorDescription", localPart = "getSquareRequest")
	@ResponsePayload
	public GetCubeResponse getSquareRequest(@RequestPayload GetCubeRequest request){
		
		GetCubeResponse response = new GetCubeResponse();
		int a = request.getVar1();
		response.setCube(a*a*a);
		return response;
	}
	
	@PayloadRoot(namespace = "http://www.example.org/calculatorDescription", localPart = "getDifferenceRequest")
	@ResponsePayload
	public GetDivideResponse getSumRequest(@RequestPayload GetDivideRequest request){
		
		GetDivideResponse response = new GetDivideResponse();
		int a = request.getVar1();
		int b = request.getVar2();
		response.setQuotient(a/b);
		return response;
	}
	
}

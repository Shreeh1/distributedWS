package com.os.calc;


import java.util.concurrent.TimeUnit;

import org.example.calculatordescription.GetProductRequest;
import org.example.calculatordescription.GetProductResponse;
import org.example.calculatordescription.GetSquareRequest;
import org.example.calculatordescription.GetSquareResponse;
import org.example.calculatordescription.GetSumRequest;
import org.example.calculatordescription.GetSumResponse;
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
	
	@PayloadRoot(namespace = "http://www.example.org/calculatorDescription", localPart = "getSumRequest")
	@ResponsePayload
	public GetSumResponse getSumRequest(@RequestPayload GetSumRequest request){
		
		GetSumResponse response = new GetSumResponse();
		int a = request.getVar1();
		int b = request.getVar2();
		response.setSum(a+b);
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
	
	@PayloadRoot(namespace = "http://www.example.org/calculatorDescription", localPart = "getProductRequest")
	@ResponsePayload
	public GetProductResponse getProductRequest(@RequestPayload GetProductRequest request){
		
		GetProductResponse response = new GetProductResponse();
		int a = request.getVar1();
		int b = request.getVar2();
		response.setProduct(a*b);
		load+=7;
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		load-=7;
		return response;
	}
	
}

package com.ImageComparison.Api;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Compare {

	
	public static int Iterations = 0;
	static int MaxIterations = 5;
	public static String getUID(String endpoint, String projectKey, String testName, String mode) {
		RestAssured.useRelaxedHTTPSValidation();
	RequestSpecification request = RestAssured.given();

			request.header("content-type","application/json");	
			JSONObject json = new JSONObject();
			json.put("TestName", testName);
			json.put("ProjectKey", projectKey);
			json.put("Mode", mode);
			request.body(json.toJSONString());
			Response response = request.when().post(endpoint+"/api/GetUID");
			int code = response.getStatusCode();
			String response_id = response.getBody().asString();
			System.out.println("Response id:" + response_id);
			return response_id;
	}

	public static void makeComparison( String uid, String stepName, String imagebase64, String endpoint) throws IOException, InterruptedException 
	{
		Iterations += 1;
		RestAssured.useRelaxedHTTPSValidation();
		 RequestSpecification request1 =   RestAssured.given();
		request1.header("content-type","application/json");	
		JSONObject jo = new JSONObject();
		jo.put("TestRunID",uid.replace("\"", "") );
		jo.put("StepName", stepName);
		//writeFile(imagebase64);
		jo.put("ImageBase64",imagebase64 );
		//System.out.println("imagebase64:"+ imagebase64);
		request1.body(jo.toJSONString());
		Response response1 = request1.when().post(endpoint+"/api/Validate");
		String response_id1 = response1.getBody().asString();
		if (response_id1.contains("Image is processed successfully"))
		{
			System.out.println( "Step: "+stepName +"   "+response_id1);
		}
		else
		{
			if(Iterations <= MaxIterations )
			{
			
				System.out.println( "Error on step " + stepName+". Trying again Iteration:" + Iterations );
				Thread.sleep(5000);
				makeComparison( uid, stepName,  imagebase64,  endpoint);
			}else
			{
				System.out.println( "Step: "+stepName +" cannot be processed");
				System.out.println( "=========Error=========");
			}
			
		}
		
		//request1 = null;
	}
	

}

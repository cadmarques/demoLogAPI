package com.example.demoLogAPI.log;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import org.springframework.http.ResponseEntity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Assertions;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LogControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	@Autowired
	private LogController logController;

	@Test
	public void contextLoads() throws Exception {
		
		Assertions.assertTrue(!logController.testLog().isEmpty());
	}
	
	// tests inserting the first entry, this one with a predefined format
	@Test
	public void testInsertLog1() throws Exception{
		
		final String baseUrl = "http://localhost:8080/api/log/text";
        URI uri = new URI(baseUrl);
        
        String body = "2021-10-29 01:43:44.994  INFO 17680 --- [n(12)-127.0.0.1] com.zaxxer.hikari.HikariDataSource     : test 1 - HikariPool-1 - Shutdown initiated...";
		
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "text/plain");
        
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        
        ResponseEntity<String> result = testRestTemplate.postForEntity(uri, request, String.class);
        
        Assertions.assertEquals(200, result.getStatusCodeValue());
        
	}
	
	// tests inserting the second entry, this one without a predefined format. The format is informed in the request
	@Test
	public void testInsertLog2() throws Exception{
		
		final String baseUrl = "http://localhost:8080/api/log/param?levelPosition=0&classPosition=4";
        URI uri = new URI(baseUrl);
        
        String body = "INFO 17680 --- [n(12)-127.0.0.1] com.zaxxer.hikari.HikariDataSource     : test 2 - HikariPool-1 - Shutdown completed.";
		
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "text/plain");
        
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        
        ResponseEntity<String> result = testRestTemplate.postForEntity(uri, request, String.class);
        
        Assertions.assertEquals(200, result.getStatusCodeValue());
        
	}
	
	// tests inserting the third entry, this one without a predefined format. The format is informed in the request
	@Test
	public void testInsertLog3() throws Exception{
		
		final String baseUrl = "http://localhost:8080/api/log/param?levelPosition=1&classPosition=5";
        URI uri = new URI(baseUrl);
        
        String body = "test3 INFO 17680 --- [n(12)-127.0.0.1] com.zaxxer.hikari.HikariDataSource     : test 3 - HikariPool-1 - Shutdown completed.";
		
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "text/plain");
        
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        
        ResponseEntity<String> result = testRestTemplate.postForEntity(uri, request, String.class);
        
        Assertions.assertEquals(200, result.getStatusCodeValue());
        
	}
	
	// tests requesting the first page of logs in JSON format
	@Test
	public void testGetResultsJsonPage0() throws Exception{
		
		String firstElementLevel, firstElementClass, secondElementLevel, secondElementClass, resultValues;
		BufferedReader in;
		StringBuffer response = new StringBuffer();
		String expectedValues = "TEST#Log API#INFO#com.zaxxer.hikari.HikariDataSource";
				
		final String getUrl = "http://localhost:8080/api/log/logs?startDate=2021-10-01&pageSize=2&pageNumber=0";
		
		URL obj = new URL(getUrl);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Accept", "application/json");
		int responseCode = con.getResponseCode();
		
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

		} else {
			Assertions.assertEquals("1", "2");
		}

		JSONParser parse = new JSONParser(); 
	
		JSONObject jObj = (JSONObject) parse.parse(response.toString()); 
		
		JSONArray jsonArr_1 = (JSONArray) jObj.get("content");
		
		JSONObject firstElement = (JSONObject) jsonArr_1.get(0);
		firstElementLevel = (String) firstElement.get("level");
		firstElementClass = (String) firstElement.get("className");
		
		JSONObject SecondElement = (JSONObject) jsonArr_1.get(1);
		secondElementLevel = (String) SecondElement.get("level");
		secondElementClass = (String) SecondElement.get("className");
		
		resultValues = firstElementLevel + "#" + firstElementClass + "#" + secondElementLevel + "#" + secondElementClass;
		
		Assertions.assertEquals(expectedValues, resultValues);
		
	}
	
	// tests requesting the second page of logs in JSON format
	@Test
	public void testGetResultsJsonPage1() throws Exception{
		
		String firstElementContent, secondElementContent, resultValues;
		BufferedReader in;
		StringBuffer response = new StringBuffer();
		String expectedValues = "INFO 17680 --- [n(12)-127.0.0.1] com.zaxxer.hikari.HikariDataSource     : test 2 - HikariPool-1 - Shutdown completed.";
		expectedValues += "###" + "test3 INFO 17680 --- [n(12)-127.0.0.1] com.zaxxer.hikari.HikariDataSource     : test 3 - HikariPool-1 - Shutdown completed.";
			
		final String getUrl = "http://localhost:8080/api/log/logs?startDate=2021-10-01&pageSize=2&pageNumber=1";
		
		URL obj = new URL(getUrl);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Accept", "application/json");
		int responseCode = con.getResponseCode();
		
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

		} else {
			Assertions.assertEquals("1", "2");
		}
		
		JSONParser parse = new JSONParser(); 
	
		JSONObject jObj = (JSONObject) parse.parse(response.toString()); 
		
		JSONArray jsonArr_1 = (JSONArray) jObj.get("content");
		
		JSONObject firstElement = (JSONObject) jsonArr_1.get(0);
		firstElementContent = (String) firstElement.get("content");
		
		JSONObject SecondElement = (JSONObject) jsonArr_1.get(1);
		secondElementContent = (String) SecondElement.get("content");
		
		resultValues = firstElementContent + "###" + secondElementContent;
		
		Assertions.assertEquals(expectedValues, resultValues);
		
	}
	
	// tests the request of logs ( second page ) with the header to receive XMl instead of JSON
	@Test
	public void testGetResultsXmlPage0() throws Exception{
		
		final String getUrl = "http://localhost:8080/api/log/logs?startDate=2021-10-01&pageSize=2&pageNumber=1";
		
		URL obj = new URL(getUrl);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Accept", "application/xml");
		int responseCode = con.getResponseCode();
		
		if (responseCode == HttpURLConnection.HTTP_OK) { // success

			Assertions.assertEquals("1", "1");
		} else {
			Assertions.assertEquals("1", "2");
		}

		
		
	}
	


	
	
}

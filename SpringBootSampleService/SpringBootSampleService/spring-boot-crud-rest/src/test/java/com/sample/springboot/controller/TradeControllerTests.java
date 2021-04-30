package com.sample.springboot.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.sample.springboot.SpringBootCrudRestApplication;
import com.sample.springboot.entity.Trade;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootCrudRestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TradeControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port+ "/spring-boot-example";
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllUsers() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/users",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetTradeById() {
		Trade user = restTemplate.getForObject(getRootUrl() + "/users/1", Trade.class);
		System.out.println(user.getclearingMember());
		assertNotNull(user);
	}

	@Test
	public void testCreateTrade() {
		Trade user = new Trade();
		user.setclearingMember("admin");
		user.settradingMember("admin");
		user.setaccountNumber("admin");

		ResponseEntity<Trade> postResponse = restTemplate.postForEntity(getRootUrl() + "/users", user, Trade.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdatePost() {
		int id = 1;
		Trade user = restTemplate.getForObject(getRootUrl() + "/users/" + id, Trade.class);
		user.setclearingMember("admin1");
		user.settradingMember("admin2");

		restTemplate.put(getRootUrl() + "/users/" + id, user);

		Trade updatedUser = restTemplate.getForObject(getRootUrl() + "/users/" + id, Trade.class);
		assertNotNull(updatedUser);
	}

	@Test
	public void testDeletePost() {
		int id = 2;
		Trade user = restTemplate.getForObject(getRootUrl() + "/users/" + id, Trade.class);
		assertNotNull(user);

		restTemplate.delete(getRootUrl() + "/users/" + id);

		try {
			user = restTemplate.getForObject(getRootUrl() + "/users/" + id, Trade.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

}

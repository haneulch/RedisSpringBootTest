package com.test;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.main.MainService;

import model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTestApplicationTests {
	
	protected MockMvc mockMvc;
	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	WebApplicationContext context;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	MainService mainService;

	@Rule
	public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
				.apply(documentationConfiguration(this.restDocumentation))
				.alwaysDo(document("{method-name}"))
				.build();
	}

	@Test
	public void testSaveUser( ) throws Exception {
		
		User user = new User();
		user.setUserId("2323");
		user.setMph("010-1234-1245");
		user.setUserName("test");
		
		mockMvc.perform(post("/saveUser")
				.content(objectMapper.writeValueAsString(user))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testFindUser( ) throws Exception {
		
		mockMvc.perform(get("/findUser")
				.param("userId", "2323"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteUser( ) throws Exception {
		
		mockMvc.perform(get("/deleteUser")
				.param("userId", "2323"))
		.andExpect(status().isOk());
	}
}

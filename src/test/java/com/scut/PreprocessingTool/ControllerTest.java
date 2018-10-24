package com.scut.PreprocessingTool;

import java.util.HashMap;
import java.util.Map;

import org.bson.codecs.pojo.annotations.BsonId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import net.minidev.json.JSONObject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTest {
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private MockHttpSession session;
	
	@Autowired
	private MockHttpServletRequest request;
	
	@Before
	public void setup(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void testLogin()throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("username", "Hong Yin");
		map.put("password", "123456");
		
		MvcResult result = 
		mockMvc.perform(post("/login")
				 .param("username", "Hong Yin")
				 .param("password", "123456"))
		        .andExpect(status().isOk())
		        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		        .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void testRegister()throws Exception{
		Map<String, Object> map = new HashMap<>();
		map.put("username", "hongyin");
		map.put("password", "123456");
		map.put("telephone", "18826074677");
		
		MvcResult result =
		mockMvc.perform(post("/register")
				.param("username", "hongyin").param("password", "123456")
				.param("telephone", "18826074677"))
		        .andExpect(status().isOk())
		        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		        .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
}

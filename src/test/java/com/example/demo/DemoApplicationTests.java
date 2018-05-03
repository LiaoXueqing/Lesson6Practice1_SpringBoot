package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class DemoApplicationTests {
	private MockMvc mvc;
	@Before
	public void setup() throws Exception{
		mvc = MockMvcBuilders.standaloneSetup(new EmployeeController()).build();
	}
	@Test
	public void contextLoads() {
	}
	@Test
	public void test_add_employee_by_id() throws Exception {
		RequestBuilder request = null;
		//post 新增employee
		request = post("/employees/")
				.param("id", "1")
				.param("name", "小李")
				.param("age", "26")
				.param("gender","女");
		mvc.perform(request)
				.andExpect(content().string(equalTo("success")));
	}
	@Test
	public void test_get_employees_not_have() throws Exception {
		RequestBuilder request = null;
		request = get("/employees/");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));
	}
}

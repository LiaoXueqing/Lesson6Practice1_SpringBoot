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
	public void setup() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new EmployeeController()).build();
	}

	@Test
	public void contextLoads() {
	}
	@Test
	public void test_EmployeeController() throws Exception {
		//get 查询employees 未添加employee时
		test_get_employees_not_have();
		//post 新增employee 并在新增后查询
		test_add_employee_then_get_employees();
	}
	private void test_get_employees_not_have() throws Exception {
		RequestBuilder request = null;
		request = get("/employees/");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));
	}
	private void test_add_employee_then_get_employees() throws Exception {
		RequestBuilder request = null;
		//post 新增employee
		request = post("/employees/")
				.param("id", "1")
				.param("name", "小李")
				.param("age", "26")
				.param("gender","女");
		mvc.perform(request)
				.andExpect(content().string(equalTo("success")));
		//get 查询employees 已添加employee时
		 request = get("/employees/");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"小李\",\"age\":26,\"gender\":\"女\"}]")));
		//post 新增employee
		request = post("/employees/")
				.param("id", "2")
				.param("name", "小王")
				.param("age", "32")
				.param("gender","男");
		mvc.perform(request)
				.andExpect(content().string(equalTo("success")));
		//get 查询employees 已添加employee时 添加两个
		request = get("/employees/");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"小李\",\"age\":26,\"gender\":\"女\"},{\"id\":2,\"name\":\"小王\",\"age\":32,\"gender\":\"男\"}]")));

	}
}

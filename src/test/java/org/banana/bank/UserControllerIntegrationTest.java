package org.banana.bank;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserControllerIntegrationTest {

	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void shouldGetBalance() throws Exception {
		mockMvc.perform(get("/balance/user/18ad5939-e928-4595-b190-371333322410"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.value", is(0.0)));
	}

	@Test
	public void shouldReturnNotFoundStatusWhenUserNotFound() throws Exception {
		mockMvc.perform(get("/balance/user/c1c709eb-3251-4bc7-8e05-66df28378373"))
				.andExpect(status().isNotFound());
	}

	@Test
	public void shouldIncreaseBalance() throws Exception {
		String body = "{\"value\": 20}";
		mockMvc.perform(post("/balance/increase/user/18ad5939-e928-4595-b190-371333322410")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
				.andExpect(status().isOk());

		mockMvc.perform(get("/balance/user/18ad5939-e928-4595-b190-371333322410"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.value", is(20.0)));
	}

	@Test
	public void shouldGetHistory() throws Exception {
		String body = "{\"value\": 20}";
		mockMvc.perform(post("/balance/increase/user/18ad5939-e928-4595-b190-371333322410")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
				.andExpect(status().isOk());

		body = "{\"value\": 10}";
		mockMvc.perform(post("/balance/increase/user/18ad5939-e928-4595-b190-371333322410")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
				.andExpect(status().isOk());

		mockMvc.perform(get("/history/user/18ad5939-e928-4595-b190-371333322410"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].value", is(20)))
				.andExpect(jsonPath("$[0].type", is("increase")))
				.andExpect(jsonPath("$[1].value", is(10)))
				.andExpect(jsonPath("$[1].type", is("increase")));
	}

	@Test
	public void shouldDecreaseBalance() throws Exception {
		MvcResult result = mockMvc.perform(post("/tokens/user/18ad5939-e928-4595-b190-371333322410")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();

		JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
		String body = "{\"value\": 10, \"token\": " + jsonObject.get("token") + " }";

		mockMvc.perform(post("/balance/decrease/user/18ad5939-e928-4595-b190-371333322410")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
				.andExpect(status().isOk());

		mockMvc.perform(get("/balance/user/18ad5939-e928-4595-b190-371333322410"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.value", is(-10.0)));
	}

	@Test
	public void shouldRejectWhenDtoIsNotValidate() throws Exception {
		String body = "{\"value\": -34 }";
		mockMvc.perform(post("/balance/increase/user/18ad5939-e928-4595-b190-371333322410")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
				.andExpect(status().isBadRequest());
	}
}

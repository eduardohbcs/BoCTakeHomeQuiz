package org.boc.takehomequiz;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {
	  
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldReturnWeatherInfo() throws Exception {
		this.mockMvc.perform(get("/weather?start=1511410524000&end=1542946524000")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("CHEMAINUS")));
	}
//	@Test
//	public void shouldFaultOnBadQueryParams() throws Exception {
//		this.mockMvc.perform(get("/weather?end=1511410524000&start=1542946524000")).andDo(print()).andExpect(status().is4xxClientError());
//	}
}
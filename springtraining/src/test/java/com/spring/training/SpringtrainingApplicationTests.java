package com.spring.training;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.spring.training.rest.MyRest;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebMvcTest(MyRest.class)
public class SpringtrainingApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MyBean myBean;

	@SpyBean
	private MyBean testBean;

	@Before
	public void init() {
		BDDMockito.given(this.myBean.getValue("osman"))
		          .willReturn(10);
	}

	@Test
	public void test_my_test() {
		Assertions.assertThat(this.myBean.getValue("osman"))
		          .isEqualTo(10);
	}

	@Test
	public void test_my_rest() throws Exception {
		MockHttpServletResponse response = this.mockMvc.perform(MockMvcRequestBuilders.get("/myrest/hello")
		                                                                              .accept(MediaType.APPLICATION_JSON_VALUE))
		                                               .andReturn()
		                                               .getResponse();
		Assertions.assertThat(response.getStatus())
		          .isEqualTo(200);

		Assertions.assertThat(response.getContentAsString())
		          .isEqualTo("Hello Spring");

		Assertions.assertThat(this.myBean.getValue("osman"))
		          .isEqualTo(10);
	}

}

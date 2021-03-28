package bdd.demo.springboottest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HomeController.class)
public class HomeControllerTest {
    private static final Logger LOG = LoggerFactory.getLogger(HomeControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    private final static String URL = "/home/greeting";
    private final static String URL_WITH_PARAM = "/home/greeting-with-param";

    @Test
    public void noParamGreetingShouldReturnDefaultMessage() throws Exception {
        // this.mockMvc.perform(get(URL)).andDo(print()).andExpect(status().isOk())
        // .andExpect(jsonPath("$.content").value("Hello, World!"));
        MockHttpServletResponse response = this.mockMvc.perform(get(URL)).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Hello, World!")).andReturn().getResponse();
        String responseText = response.getContentAsString();
        LOG.info("Greetings default response: " + responseText);
    }

    @Test
    public void paramGreetingShouldReturnTailoredMessage() throws Exception {
        MockHttpServletResponse response = this.mockMvc
                .perform(get(URL).param("name", "Spring Community")).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Hello, Spring Community!")).andReturn()
                .getResponse();
        String responseText = response.getContentAsString();
        LOG.info("Greetings default response: " + responseText);
    }

    @Test
    public void noParamGreetingWithParamShouldReturnError() throws Exception {
        MockHttpServletResponse response = this.mockMvc.perform(get(URL_WITH_PARAM))
                .andExpect(status().isBadRequest()).andReturn().getResponse();
        String responseText = response.getErrorMessage();
        LOG.info("GreetingsWithParam error response: " + responseText);
    }

    @Test
    public void paramGreetingWithParamShouldReturnTailoredMessage() throws Exception {
        MockHttpServletResponse response =
                this.mockMvc.perform(get(URL_WITH_PARAM).param("name", "Spring Community"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.content").value("Hello(param), Spring Community!"))
                        .andReturn().getResponse();
        String responseText = response.getContentAsString();
        LOG.info("GreetingsWithParam tailored response: " + responseText);
    }
}

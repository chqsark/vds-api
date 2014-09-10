package vds.tag.api;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import vds.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
abstract class AbstractResourceTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    protected void performGet(String uri, Object[][] expects) throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON);
        ResultActions result = mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        expect(result, expects);
    }

    protected void performPost(String uri, String data, Object[][] expects) throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .content(data)
                .contentType(MediaType.APPLICATION_JSON);
        ResultActions result = mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        expect(result, expects);
    }

    protected void performPut(String uri, String data) throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(uri)
                .accept(MediaType.APPLICATION_JSON)
                .content(data)
                .contentType(MediaType.APPLICATION_JSON);
        ResultActions result = mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    protected void performDelete(String uri) throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(uri);
        ResultActions result = mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private void expect(ResultActions result, Object[][] expects) throws Exception {
        for (Object[] expect : expects) {
            result.andExpect(MockMvcResultMatchers.jsonPath(expect[0].toString()).value(expect[1]));
        }
    }
}

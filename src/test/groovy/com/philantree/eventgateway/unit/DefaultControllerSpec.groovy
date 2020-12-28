package com.philantree.eventgateway.unit

import com.philantree.eventgateway.controller.DefaultController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import spock.lang.Specification

@AutoConfigureMockMvc
@WebMvcTest(DefaultController.class)
class DefaultControllerSpec extends Specification{
    @Autowired
    private MockMvc mvc

    def "when get is performed then the response has status 200 and content is 'Hello world!'"() {
        expect: "Status is 200 and the response is 'Hello world!'"
        mvc.perform(get('/default'))
                .andExpect(status().isOk())
                .andReturn()
                .response
                .contentAsString == "hello"
    }
}
package io.github.cloudiator.rest.api;

import io.swagger.Swagger2SpringBoot;
import org.cloudiator.messaging.services.CloudService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CloudsApiController.class)
public class CloudsApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CloudService mockservice;

    @Test
    public void addCloud() throws Exception {
    }

    @Test
    public void deleteCloud() throws Exception {
    }

    @Test
    public void findCloud() throws Exception {
    }

    @Test
    public void findClouds() throws Exception {
    }

}
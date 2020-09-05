package com.hiring.football.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiring.football.controller.StandingController;
import com.hiring.football.model.Response;
import com.hiring.football.model.StandingResponse;
import com.hiring.football.service.StandingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@WebMvcTest(StandingController.class)
public class StandingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StandingService standingService;

    StandingResponse response;

    ClassLoader classLoader = getClass().getClassLoader();


    @Before
    public void beforeEachTestMethod() throws  IOException {
        System.out.println("Invoked before each test method");
        URL resource = classLoader.getResource("sample/standing.json");
        //File file = new File(resource.toURI());
        String content = new String(Files.readAllBytes(Paths.get(resource.getPath())));
        ObjectMapper objectMapper = new ObjectMapper();
        response = objectMapper.readValue(content, StandingResponse.class);
    }

    @Test
    public void testingHealthEndpoint()  throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/hello")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testingleagueStandingData()  throws Exception {

       // BDDMockito.given(standingService.getStandingPosition(ArgumentMatchers.anyString())).willReturn(response);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/get-standing").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        System.out.println(result);
    }

}

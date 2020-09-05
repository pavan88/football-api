package com.hiring.football.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiring.football.model.Response;
import com.hiring.football.service.StandingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@WebMvcTest(StandingController.class)
public class StandingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StandingService standingService;

    List<Response> response;


    @Before
    public void beforeEachTestMethod() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("sample/standing.json");
        String content = new String(Files.readAllBytes(Paths.get(resource.getPath())));
        ObjectMapper objectMapper = new ObjectMapper();
        response = Arrays.asList(objectMapper.readValue(content, Response[].class));
    }

    @Test
    public void testingHealthEndpoint() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/hello")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testingleagueStandingData() throws Exception {

        BDDMockito.given(standingService.getStandingPosition(ArgumentMatchers.anyString())).willReturn(response);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/get-standing/1").accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).
                andReturn();

        verify(standingService, times(1)).getStandingPosition(anyString());
        verifyNoMoreInteractions(standingService);
    }

    @Test
    public void testingleagueStandingData_Exception() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/get-standing/").accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isNotFound()).
                andReturn();

        verify(standingService, times(0)).getStandingPosition(anyString());

    }

}

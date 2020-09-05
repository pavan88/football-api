package com.hiring.football.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiring.football.config.AppConfigs;
import com.hiring.football.exception.InvalidLeagueException;
import com.hiring.football.model.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class StandingServiceTest {

    Response[] response;
    String content;
    String url = "https://apiv2.apifootball.com/?action=get_standings&league_id=14&APIkey=API-Dummy";
    @InjectMocks
    private StandingService standingService;
    @Mock
    private AppConfigs appConfigs;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private RestTemplate restTemplate;

    @Before
    public void beforeEachTestMethod() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("sample/standing.json");
        content = new String(Files.readAllBytes(Paths.get(resource.getPath())));
        ObjectMapper objectMapper = new ObjectMapper();
        response = objectMapper.readValue(content, Response[].class);
    }

    @Test
    public void whenValidLeagueIdPassed() throws InvalidLeagueException, IOException {
        ResponseEntity<String> responseEntity = new ResponseEntity(content, HttpStatus.OK);
        BDDMockito.given(appConfigs.getApikey()).willReturn("API-Dummy");
        BDDMockito.given(restTemplate.exchange(url, HttpMethod.GET, null, String.class)).
                willReturn(responseEntity);

        BDDMockito.given(objectMapper.readValue(content, Response[].class)).willReturn(response);

        List<Response> responseList = standingService.getStandingPosition("14");


        //Asserting
        Assert.assertEquals(1, responseList.size());
        Assert.assertTrue(responseList.stream().anyMatch(s ->
                s.getLeague_id().equalsIgnoreCase("14")
        ));
        Assert.assertTrue(responseList.stream().anyMatch(s ->
                s.getCountry_name().equalsIgnoreCase("Argentina") &&
                        s.getLeague_name().equalsIgnoreCase("Primera C")
        ));

        //Verification
        verify(restTemplate, times(1)).exchange(url, HttpMethod.GET, null, String.class);
        verify(objectMapper, times(1)).readValue(content, Response[].class);
        verify(appConfigs, times(1)).getApikey();
        verifyNoMoreInteractions(restTemplate, objectMapper, appConfigs);
    }

    @Test(expected = InvalidLeagueException.class)
    public void wheninvalidLeagueIdPassed() throws InvalidLeagueException, IOException {

        standingService.getStandingPosition("");

    }
}

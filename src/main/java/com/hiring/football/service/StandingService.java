package com.hiring.football.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiring.football.config.AppConfigs;
import com.hiring.football.exception.InvalidLeagueException;
import com.hiring.football.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class StandingService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppConfigs appConfigs;

    @Autowired
    private ObjectMapper objectMapper;

    private final static String URL = "https://apiv2.apifootball.com/";

    /**
     * This method wil return the List of the standing Position for the given league id
     *
     * @param league_id
     * @return List of standing position
     */
    public List<Response> getStandingPosition(String league_id) throws InvalidLeagueException, IOException {

        if (league_id == null || StringUtils.isEmpty(league_id)) {
            throw new InvalidLeagueException("Invalid or Empty id : " + league_id);
        }
        UriComponentsBuilder uriForStandingPosition = UriComponentsBuilder.fromUriString(URL)
                .queryParam("action", "get_standings")
                .queryParam("league_id", league_id)
                .queryParam("APIkey", appConfigs.getApikey());
        List<Response> standings = Collections.emptyList();
        String standingPositionStr = null;

        try {
            standingPositionStr = makeApiCall(uriForStandingPosition.toUriString());
            standings = Arrays.asList(objectMapper.readValue(standingPositionStr, Response[].class));
            log.debug("Response" + standings);
            if (standings.isEmpty()) {
                throw new InvalidLeagueException("Not a valid league ID");
            } else {
                return standings;
            }
        } catch (IOException ioException) {
            Map<String, String> map = objectMapper.readValue(standingPositionStr, Map.class);
            throw new InvalidLeagueException(map.get("message"));
        }

    }


    /**
     * This method is generic and can be used as utility to make any calls,
     *
     * @param uri -> dynamic URI to fetching the details
     * @return Response as String ,
     */
    private String makeApiCall(String uri) throws RestClientException {

        //This API is common to get standing position and getting country id
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
        return responseEntity.getBody();

    }


}

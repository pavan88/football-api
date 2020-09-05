package com.hiring.football.controller;

import com.hiring.football.exception.InvalidLeagueException;
import com.hiring.football.model.Response;
import com.hiring.football.service.StandingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
public class StandingController {

    @Autowired
    private StandingService standingService;

    //TODO handle exceptions and errors
    @GetMapping("/get-standing/{league_id}")
    public ResponseEntity<List> getStandingDetails(@PathVariable("league_id") String leagueId) throws InvalidLeagueException, IOException {
        log.info("Making the Service call to fetch the details");
        List<Response> responseList = standingService.getStandingPosition(leagueId);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String hello() {
        log.info("Hello Test Health Endpoint");
        return "I am Alive";
    }
}

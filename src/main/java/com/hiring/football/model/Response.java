package com.hiring.football.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {

    //private String country_id;
    private String country_name;

    private String league_id;
    private String league_name;

    private String team_id;
    private String team_name;

    private String overall_league_position;

}

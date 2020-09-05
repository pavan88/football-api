package com.hiring.football.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class StandingResponse{

	@JsonProperty("away_league_W")
	private String awayLeagueW;

	@JsonProperty("overall_league_D")
	private String overallLeagueD;

	@JsonProperty("home_league_PTS")
	private String homeLeaguePTS;

	@JsonProperty("overall_league_payed")
	private String overallLeaguePayed;

	@JsonProperty("overall_league_L")
	private String overallLeagueL;

	@JsonProperty("team_id")
	private String teamId;

	@JsonProperty("team_badge")
	private String teamBadge;

	@JsonProperty("home_league_GF")
	private String homeLeagueGF;

	@JsonProperty("home_league_position")
	private String homeLeaguePosition;

	@JsonProperty("away_league_L")
	private String awayLeagueL;

	@JsonProperty("away_league_payed")
	private String awayLeaguePayed;

	@JsonProperty("home_league_GA")
	private String homeLeagueGA;

	@JsonProperty("country_name")
	private String countryName;

	@JsonProperty("overall_promotion")
	private String overallPromotion;

	@JsonProperty("overall_league_GA")
	private String overallLeagueGA;

	@JsonProperty("overall_league_position")
	private String overallLeaguePosition;

	@JsonProperty("home_league_W")
	private String homeLeagueW;

	@JsonProperty("overall_league_GF")
	private String overallLeagueGF;

	@JsonProperty("away_league_D")
	private String awayLeagueD;

	@JsonProperty("overall_league_W")
	private String overallLeagueW;

	@JsonProperty("home_league_payed")
	private String homeLeaguePayed;

	@JsonProperty("home_league_L")
	private String homeLeagueL;

	@JsonProperty("league_round")
	private String leagueRound;

	@JsonProperty("away_promotion")
	private String awayPromotion;

	@JsonProperty("home_promotion")
	private String homePromotion;

	@JsonProperty("league_name")
	private String leagueName;

	@JsonProperty("home_league_D")
	private String homeLeagueD;

	@JsonProperty("team_name")
	private String teamName;

	@JsonProperty("overall_league_PTS")
	private String overallLeaguePTS;

	@JsonProperty("away_league_GF")
	private String awayLeagueGF;

	@JsonProperty("away_league_GA")
	private String awayLeagueGA;

	@JsonProperty("away_league_position")
	private String awayLeaguePosition;

	@JsonProperty("away_league_PTS")
	private String awayLeaguePTS;

	@JsonProperty("league_id")
	private String leagueId;
}
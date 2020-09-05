package com.hiring.football.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CountryResponse{

	@JsonProperty("country_name")
	private String countryName;

	@JsonProperty("country_id")
	private String countryId;

	@JsonProperty("country_logo")
	private String countryLogo;
}
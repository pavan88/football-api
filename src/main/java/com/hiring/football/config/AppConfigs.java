package com.hiring.football.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("football")
@Getter
@Setter
public class AppConfigs {

    private String apikey;

}

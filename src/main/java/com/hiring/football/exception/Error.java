package com.hiring.football.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Error {

    private String errorCode;
    private String errorMessage;
    private String localDateTime;
    private String urlpath;
}

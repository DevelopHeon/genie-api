package com.hh.study.genieapi.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArtistNotFoundException extends RuntimeException{
    private String message;
}

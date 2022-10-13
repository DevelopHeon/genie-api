package com.hh.study.genieapi.common.error;

import lombok.*;

import java.util.Map;

@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class ErrorResponse {
    private String code;
    private String message;
    private Map<String, String> errors;
}

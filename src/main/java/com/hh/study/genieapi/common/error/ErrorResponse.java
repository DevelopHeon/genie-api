package com.hh.study.genieapi.common.error;

import lombok.*;

import java.util.List;

@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class ErrorResponse {
    private String code;
    private String message;
    private List<FieldError> errors;
}

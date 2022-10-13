package com.hh.study.genieapi.common.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionController {

    @ExceptionHandler(ArtistNotFoundException.class)
    public ResponseEntity handleArtistNotFoundException(ArtistNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(AlbumNotFoundException.class)
    public ResponseEntity handleAlbumNotFoundException(AlbumNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(c -> {
                    errors.put("fieldName : ", ((FieldError) c).getField());
                    errors.put("message : ", c.getDefaultMessage());
                }
        );
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code("Error_400")
                .message("잘못된 요청입니다.")
                .errors(errors)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}

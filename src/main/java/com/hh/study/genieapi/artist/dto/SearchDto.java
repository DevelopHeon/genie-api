package com.hh.study.genieapi.artist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class SearchDto {
    private String searchParam;
    private int pageNumber;
    private int offset;
}

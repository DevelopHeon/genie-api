package com.hh.study.genieapi.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor @AllArgsConstructor
public class SearchDto {

    private String keyword;
    @NotNull
    private Integer pageNum = 1;
    @NotNull
    private Integer pageOption = 5;
}

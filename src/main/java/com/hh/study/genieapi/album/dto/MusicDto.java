package com.hh.study.genieapi.album.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MusicDto {

    private Integer order;
    @NotEmpty(message = "음원명은 필수 값입니다.")
    private String musicTitle;
    @NotEmpty(message = "재생시간은 필수 값입니다.")
    private String playTime;
    private Boolean status;
}

package com.hh.study.genieapi.album.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MusicForm {

    @NotNull(message = "orders는 null일 수 없습니다.")
    private Integer orders;
    @NotBlank(message = "음원명은 필수 값입니다.")
    private String musicTitle;
    @NotEmpty(message = "재생시간은 필수 값입니다.")
    @Pattern(regexp = "([0-5][0-9]):([0-5][0-9])")
    private String playTime;
    @NotNull
    private Boolean status;
}

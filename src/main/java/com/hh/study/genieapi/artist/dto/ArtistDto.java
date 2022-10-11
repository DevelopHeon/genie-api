package com.hh.study.genieapi.artist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data @NoArgsConstructor
@AllArgsConstructor @Builder
public class ArtistDto {
    @NotEmpty(message = "아티스트명은 필수 입력 값입니다.")
    private String artistName;
    @NotEmpty(message = "출생일은 필수 입력 값입니다.")
    private String artistBirth;
    private String agency;
    @NotEmpty(message = "국적은 필수 입력 값입니다.")
    private String country;
    private String artistExplanation;
    private LocalDateTime modifyDate;
}

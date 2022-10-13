package com.hh.study.genieapi.artist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data @NoArgsConstructor
@AllArgsConstructor @Builder
public class ArtistDto {
    @NotBlank(message = "아티스트명은 필수 입력 값입니다.")
    private String artistName;
    @NotNull
    private LocalDate artistBirth;
    private String agency;
    @NotBlank(message = "국적은 필수 입력 값입니다.")
    private String country;
    private String artistExplanation;
}

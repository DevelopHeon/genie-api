package com.hh.study.genieapi.artist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDetail {

    private Integer artistId;
    private String artistName;
    private String artistAuthor;
    private LocalDate artistBirth;
    private String agency;
    private String country;
    private String artistExplanation;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}

package com.hh.study.genieapi.artist.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Artist {

    private Integer artistId;
    private String artistAuthor;
    private String artistName;
    private LocalDate artistBirth;
    private String agency;
    private String country;
    private String artistExplanation;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}

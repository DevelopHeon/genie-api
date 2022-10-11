package com.hh.study.genieapi.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder @ToString
public class Artist {

    private Integer artistId;
    private String artistAuthor;
    private String artistName;
    private String artistBirth;
    private String agency;
    private String country;
    private String artistExplanation;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}

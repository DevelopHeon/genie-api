package com.hh.study.genieapi.artist.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder @ToString
public class Artist {

    private Integer rnum;
    private Integer artistId;
    private String artistAuthor;
    private String artistName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate artistBirth;
    private String agency;
    private String country;
    private String artistExplanation;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modifyDate;
}

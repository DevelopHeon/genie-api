package com.hh.study.genieapi.artist.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistList {

    private int rnum;
    private Integer artistId;
    private String artistName;
    private String artistAuthor;
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

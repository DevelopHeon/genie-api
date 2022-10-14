package com.hh.study.genieapi.album.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SerachArtistList {
    private int rnum;
    private Integer artistId;
    private String artistName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate artistBirth;
    private String agency;
    private String artistExplanation;
}

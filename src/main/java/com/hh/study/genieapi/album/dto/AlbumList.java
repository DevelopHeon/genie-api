package com.hh.study.genieapi.album.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumList {

    private Integer rnum;
    private Integer albumId;
    private String artistName;
    private String albumTitle;
    private LocalDate releaseDate;
    private String genre;
    private String albumExplanation;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private String albumAuthor;
}

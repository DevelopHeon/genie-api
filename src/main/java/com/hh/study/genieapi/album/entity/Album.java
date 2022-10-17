package com.hh.study.genieapi.album.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Album {

    private Integer albumId;
    private Integer artistId;
    private String artistName;
    private String albumTitle;
    private LocalDate releaseDate;
    private String genre;
    private String albumExplanation;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private String albumAuthor;
}

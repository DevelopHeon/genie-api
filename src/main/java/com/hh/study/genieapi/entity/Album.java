package com.hh.study.genieapi.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder @ToString
public class Album {

    private Integer albumId;
    private Integer artistId;
    private String albumTitle;
    private LocalDate releaseDate;
    private String genre;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private String albumAuthor;
}

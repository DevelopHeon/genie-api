package com.hh.study.genieapi.album.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder @ToString
public class Album {

    private Integer rnum;
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
    private List<Music> musicList;
}

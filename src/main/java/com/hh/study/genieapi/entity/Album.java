package com.hh.study.genieapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    private Integer albumId;
    private Integer artistId;
    private String artistName;
    private String albumTitle;
    private LocalDate releaseDate;
    private String genre;
    private String albumExplanation;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modifyDate;
    private String albumAuthor;
    private List<Music> musicList;
}

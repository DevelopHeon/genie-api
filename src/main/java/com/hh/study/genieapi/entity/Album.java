package com.hh.study.genieapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private String genre;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modifyDate;
    private String albumAuthor;
}

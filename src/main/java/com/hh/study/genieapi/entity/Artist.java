package com.hh.study.genieapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modifyDate;
}
